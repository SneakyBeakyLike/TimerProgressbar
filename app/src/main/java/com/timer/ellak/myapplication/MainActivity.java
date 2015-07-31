package com.timer.ellak.myapplication;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends Activity implements View.OnClickListener {

    Button Bt;
    TextView Tv;
    Timer myTimer = null;
    TimerTask myTask = null;
    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Bt = (Button) findViewById(R.id.incbt);
        Tv = (TextView) findViewById(R.id.tv);
        Bt.setOnClickListener(this);
        myTask = new TimerTask() {
            @Override
            public void run() {
                count ++;
                ShowMessage(count);
            }
        };
        if(myTimer==null){
            myTimer = new Timer();
        }
        myTimer.schedule(myTask,1000,10000);
        myTask = new TimerTask() {
            @Override
            public void run() {
                if(count>=100){
                    showToast("Finish");
                    myTimer.cancel();
                    myTimer.purge();
                    myTimer = null;

                }
            }
        };
        myTimer.schedule(myTask,0,1000);
    }




    @Override
    public void onClick(View v) {
        if(v == Bt){
            count += 10;
            Tv.setText(String.valueOf(count));
        }
    }

    public void ShowMessage (int Val)
    {
        Message msg = new Message ();
        Bundle bun = new Bundle ();
        bun.putInt ("Value", Val);
        msg.setData (bun);
        MyHandler.sendMessage (msg);
    }

    Handler MyHandler = new Handler ()
    {
        @Override
        public void handleMessage (Message Mess)
        {
            Bundle b = Mess.getData ();
            int tbp = b.getInt ("Value");
            Tv.setText (String.valueOf (tbp));
        }
    };

    public void showToast(String text){
        Message toastmsg = new Message();
        Bundle bu = new Bundle();
        bu.putString("Text",text);
        toastmsg.setData(bu);
        toastHandler.sendMessage(toastmsg);
    }

    Handler toastHandler  = new Handler()
    {
        @Override
        public void handleMessage (Message mess){
            Bundle b = mess.getData();
            String tr = b.getString("Text");
            Context context = getApplicationContext();
            Toast toast = Toast.makeText(context, tr, Toast.LENGTH_LONG);
            toast.show();
            Bt.setEnabled(false);
        }
    };

}
