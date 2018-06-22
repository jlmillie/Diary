package com.on.diary.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.widget.TextView;

import com.on.diary.MainActivity;
import com.on.diary.R;

/**
 * Created by Administrator on 2018/2/3.
 */

public class Splash extends AppCompatActivity {
    public static String TAG = "Splash";
    // 声明控件对象
    private TextView start_skip_count_down;
    private MyCountDownTimer mCountDownTimer;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 下面的话就是去除标题的方法
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash);
        start_skip_count_down = (TextView) findViewById(R.id.start_skip_count_down);

        start_skip_count_down.setText("5s 跳过");
        //创建倒计时类
        mCountDownTimer = new MyCountDownTimer(5000, 1000);
        mCountDownTimer.start();
        //这是一个 Handler 里面的逻辑是从 Splash 界面跳转到 Main 界面，这里的逻辑每个公司基本上一致
        tmpHandler.postDelayed(runnable, 5000);
    }

    Handler tmpHandler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            startActivity(new Intent(Splash.this, MainActivity.class));
            finish();
        }
    };

    class MyCountDownTimer extends CountDownTimer {
        /**
         * @param millisInFuture    表示以「 毫秒 」为单位倒计时的总数
         *                          例如 millisInFuture = 1000 表示1秒
         * @param countDownInterval 表示 间隔 多少微秒 调用一次 onTick()
         *                          例如: countDownInterval = 1000 ; 表示每 1000 毫秒调用一次 onTick()
         */

        public MyCountDownTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }


        public void onFinish() {
            start_skip_count_down.setText("0s 跳过");
        }

        public void onTick(long millisUntilFinished) {
            start_skip_count_down.setText(millisUntilFinished / 1000 + "s 跳过");
        }

    }

    @Override
    protected void onDestroy() {
        if (mCountDownTimer != null) {
            mCountDownTimer.cancel();
        }
        super.onDestroy();
    }

}
