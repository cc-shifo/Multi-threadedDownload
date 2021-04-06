package example.com.sunshine.download;

import android.os.Bundle;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;
import example.com.sunshine.GridAdapter;
import example.com.sunshine.R;

/**
 * Created by qianxiangsen on 2017/3/27.
 */

public class DownloadFinishActivity extends AppCompatActivity {
    private DownloadMessage downlaod;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download_finish);
        ButterKnife.bind(this);
        downlaod = DownloadMessage.init(this);

        DownloadFinishAdapter downloadFinishAdapter = new DownloadFinishAdapter(this,downlaod.getmArrayFinish());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(downloadFinishAdapter);
        getSupportActionBar().setTitle("下载完成");

    }



    @Override
    protected void onPause() {
        super.onPause();
    }
}
