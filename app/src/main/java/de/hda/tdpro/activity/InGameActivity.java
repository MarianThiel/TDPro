package de.hda.tdpro.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.widget.Button;

import de.hda.tdpro.R;
import de.hda.tdpro.core.DemoView;
import de.hda.tdpro.core.tower.TowerType;

public class InGameActivity extends AppCompatActivity {
    private DemoView gameView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //gameView = new DemoView(this);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_in_game);
        gameView = findViewById(R.id.view);

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