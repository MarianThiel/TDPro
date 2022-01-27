package de.hda.tdpro.activity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.WindowManager;
import android.widget.ImageView;

import de.hda.tdpro.R;


public class StartUpActivity extends AppCompatActivity {

    private Thread timeThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_start_up);
        timeThread = new Thread(new Splashhandler());
        timeThread.start();
    }


    class Splashhandler implements Runnable{
        ImageView background = findViewById(R.id.background);

        public void run() {
            for (int i=0;i<10;i++){
                background.setX((background.getX()+1));
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            for (int i=0;i<10;i++){
                background.setX((background.getX()-1));
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            for (int i=0;i<100;i++){
                background.setImageAlpha( (background.getImageAlpha()-1) );
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            Intent fp = new Intent(getApplicationContext(), MainMenuActivity.class);
            startActivity(fp);
        }
    }
}
