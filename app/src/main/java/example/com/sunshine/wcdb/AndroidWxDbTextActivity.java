package example.com.sunshine.wcdb;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.tencent.wcdb.database.SQLiteDatabase;
import com.tencent.wcdb.database.SQLiteOpenHelper;

import butterknife.BindView;
import butterknife.ButterKnife;
import example.com.sunshine.R;

/**
 * Created by qianxiangsen on 2017/7/10.
 */

public class AndroidWxDbTextActivity extends AppCompatActivity {


    @BindView(R.id.recycler_view)
    RecyclerView mReclerView;
    private SQLiteOpenHelper mDBHelper = new DBHelper(this);

    private SQLiteDatabase mDB;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_main);
        ButterKnife.bind(this);




//        mDBHelper.setWriteAheadLoggingEnabled(true);
//        mDB = mDBHelper.getWritableDatabase();

    }


}
