package example.com.sunshine.Main;

import android.os.Bundle;
import android.transition.Scene;
import android.transition.TransitionInflater;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import example.com.sunshine.R;

/**
 * Created by qianxiangsen on 2017/3/27.
 */

public class SceneActivity extends AppCompatActivity {

    @BindView(R.id.image_ib)
    ImageButton mImageButton;
    ViewGroup viewGroup;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scene1);
        ButterKnife.bind(this);
        viewGroup =   (ViewGroup)findViewById(R.id.root);

        mImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    TransitionManager.go(Scene.getSceneForLayout(viewGroup,
                            R.layout.scene2,SceneActivity.this));

                    TransitionInflater.from(SceneActivity.this).inflateTransition(R.transition.defaulttoinfo);



            }
        });
    }
}
