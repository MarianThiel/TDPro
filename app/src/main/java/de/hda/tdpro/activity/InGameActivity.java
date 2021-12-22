package de.hda.tdpro.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import de.hda.tdpro.R;
import de.hda.tdpro.core.DemoView;

public class InGameActivity extends AppCompatActivity {
    private DemoView gameView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gameView = new DemoView(this);
        setContentView(gameView);
    }

    @Override
    protected void onPause() {
        super.onPause();
        gameView.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        gameView.resume();
    }
}