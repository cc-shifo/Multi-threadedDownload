package example.com.sunshine.view;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.tabs.TabLayout;

import example.com.sunshine.R;

/**
 * Created by qianxiangsen on 2017/4/5.
 */

public class MyViewActivity extends AppCompatActivity {


    TabLayout tabLayout;
    private Handler handler;
    private Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actcivity_view);

        setupTablayout();

    }

    private void setupTablayout() {

        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.addTab(tabLayout.newTab().setText("Tab 1"));
        tabLayout.addTab(tabLayout.newTab().setText("Tab 2"));
        tabLayout.addTab(tabLayout.newTab().setText("Tab 3"));
        tabLayout.addTab(tabLayout.newTab().setText("Tab 4"));
        mButton = (Button) findViewById(R.id.button);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Message message = Message.obtain();
                message.obj = "test";
                message.what = 1;
                handler.sendMessage(message);
            }
        });

        new Thread(new mRunnable()).start();

    }


    class mRunnable implements Runnable{

        @Override
        public void run() {
            Looper.prepare();
            handler = new Handler(Looper.myLooper()){
                @Override
                public void handleMessage(Message msg) {
                    if (msg.what == 1){
                        Log.d("TAG", "handleMessage: "+msg.obj);
                    }
                }
            };
            Looper.loop();
        }
    }
}
//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        //??????
//        if (MotionEvent.ACTION_DOWN == event.ACTION_DOWN){
//
//            Log.d("TAG", "ACTION_DOWN: "+"---->??????");
//            return true;
//            //??????
//        }else if (MotionEvent.ACTION_MOVE == event.ACTION_MOVE){
//            Log.d("TAG", "ACTION_MOVE: "+"---->??????");
//            return true;
//            //??????
//        }else if (MotionEvent.ACTION_UP == event.ACTION_UP){
//            Log.d("TAG", "ACTION_UP: "+"---->??????");
//            return true;
//            //????????????
//        }else if (MotionEvent.ACTION_CANCEL == event.ACTION_CANCEL){
//            Log.d("TAG", "ACTION_CANCEL: "+"---->????????????");
//            return true;
//            //????????????
//        }else if (MotionEvent.ACTION_OUTSIDE == event.ACTION_OUTSIDE){
//            Log.d("TAG", "ACTION_OUTSIDE: "+"---->????????????");
//            return true;
//        }
//
//        return false;
//    }
//}
