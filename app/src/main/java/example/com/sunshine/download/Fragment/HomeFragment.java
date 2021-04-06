package example.com.sunshine.download.Fragment;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.Animation;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import example.com.sunshine.R;
import example.com.sunshine.download.DownFragment;
import example.com.sunshine.util.Util;

/**
 * Created by qianxiangsen on 2017/5/3.
 */

public class HomeFragment extends BaseFragment{

    @BindView(R.id.ib_download)
    TextView ib_download;
    @BindView(R.id.viewpager)
    ViewPager viewPager;

    @BindView(R.id.tabs)
    TabLayout tabLayout;
    @Inject
    public HomeFragment() {
    }
    @Inject
    DownFragment downFragment;

    @Inject
    RecommendFragment recommendFragment;

    @Inject
    PopularFragment popularFragment;

    @Inject
    CategoryFragment categoryFragment;

    @Inject
    ListFragment listFragment;

    @Inject
    AnchorFragment anchorFragment;
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }


    @Override
    protected void initView(Bundle savedInstanceState, View rootView) {
        ButterKnife.bind(this,rootView);
        ib_download.setOnClickListener(this);

        setupViewPager(viewPager);

        tabLayout.setupWithViewPager(viewPager);
        viewPager.setOffscreenPageLimit(5);
        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                setIndicator(tabLayout,15,15);
            }
        });

    }
    public void setIndicator(TabLayout tabs, int leftDip, int rightDip) {
        Class<?> tabLayout = tabs.getClass();
        Field tabStrip = null;
        try {
//            tabStrip = tabLayout.getDeclaredField("mTabStrip");
            tabStrip = tabLayout.getDeclaredField("slidingTabIndicator");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
            return;
        }

        tabStrip.setAccessible(true);
        LinearLayout llTab = null;
        try {
            llTab = (LinearLayout) tabStrip.get(tabs);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return;
        }

        int left = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, leftDip, Resources.getSystem().getDisplayMetrics());
        int right = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, rightDip, Resources.getSystem().getDisplayMetrics());

        for (int i = 0; i < llTab.getChildCount(); i++) {
            View child = llTab.getChildAt(i);
            child.setPadding(0, 0, 0, 0);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1);
            params.leftMargin = left;
            params.rightMargin = right;
            child.setLayoutParams(params);
            child.invalidate();
        }
    }


    @Override
    protected void initData() {

    }

    @Override
    protected void OnClick(View v) {

        switch (v.getId()){
            case R.id.ib_download:

                addFragment(R.id.fragment_full,downFragment,
                       Util.DOWN_TAG_FRAGMENT);
                break;
        }

    }
    @Override
    public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
        return null;
    }

    private void setupViewPager(ViewPager viewPager) {

        ViewPagerAdapter adapter =
                new ViewPagerAdapter(getActivity().getSupportFragmentManager());
        adapter.addFragment(recommendFragment, "推荐");
        adapter.addFragment(popularFragment, "热门");
        adapter.addFragment(categoryFragment, "分类");
        adapter.addFragment(listFragment, "榜单");
        adapter.addFragment(anchorFragment, "主播");
        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}
