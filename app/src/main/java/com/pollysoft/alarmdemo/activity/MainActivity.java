package com.pollysoft.alarmdemo.activity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.pollysoft.alarmdemo.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void setAlarm(View view) {
        Toast.makeText(this, "alarm", Toast.LENGTH_SHORT).show();

        AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);
        long curTime = System.currentTimeMillis();
        curTime += 5000;
        Intent i = new Intent("com.pollysoft.alarmdemo.service.MyService");
        PendingIntent sender = PendingIntent.getService(this, 0, i, PendingIntent.FLAG_UPDATE_CURRENT);
        am.set(AlarmManager.RTC_WAKEUP, curTime, sender);
    }

    public void setAlarms(View view){
        // 创建Intent对象，action指向广播接收类，附加信息为字符串“你该打酱油了”
        Intent intent = new Intent("com.dwtedx.MyReceiver");
        intent.putExtra("msg", "你该起床了");
        // 创建PendingIntent对象封装Intent，由于是使用广播，注意使用getBroadcast方法
        PendingIntent pi = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        // 获取AlarmManager对象
        AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);

        am.cancel(pi);
        // 设置闹钟从当前时间开始，每隔10分钟执行一次PendingIntent对象，注意第一个参数与第二个参数的关系
        am.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), 5 * 1000, pi);
    }
}
