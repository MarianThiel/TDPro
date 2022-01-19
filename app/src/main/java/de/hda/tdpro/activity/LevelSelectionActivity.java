package de.hda.tdpro.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import de.hda.tdpro.MusicPlayer;
import de.hda.tdpro.R;
import pl.droidsonroids.gif.GifImageView;

public class LevelSelectionActivity extends AppCompatActivity implements View.OnClickListener {

    int level = 0;
    float GifPosX;
    float GifPosY;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_level_selection);



        // set onClick Buttons
        ImageButton back = (ImageButton) findViewById(R.id.imageButtonBack);
        back.setOnClickListener((View.OnClickListener) this);
        ImageButton next = (ImageButton) findViewById(R.id.imageButtonNext);
        next.setOnClickListener((View.OnClickListener) this);
        ImageButton previous = (ImageButton) findViewById(R.id.imageButtonPrevious);
        previous.setOnClickListener((View.OnClickListener) this);
        Button start = (Button) findViewById(R.id.buttonStart);
        start.setOnClickListener((View.OnClickListener) this);
    }

    public void onClick(View view) {
        ImageButton back = findViewById(R.id.imageButtonBack);
        ImageButton next = findViewById(R.id.imageButtonNext);
        ImageButton previous = findViewById(R.id.imageButtonPrevious);
        Button start = findViewById(R.id.buttonStart);
        if (view == back) {
            Intent fp = new Intent(getApplicationContext(), MainMenuActivity.class);
            startActivity(fp);
        }
        else if (view == next) {
            clickNext();
        }
        else if (view == previous){
            clickPrevious();
        }
        else if (view == start){
            if ( level == 0 ) {
                Intent fp = new Intent(getApplicationContext(), InGameActivity.class);
                startActivity(fp);
            }
        }
    }

    private void clickNext() {
        TextView leveltext = findViewById(R.id.textLevel);
        GifImageView demon = findViewById(R.id.demonGif);
        if (level == 2) {
            demon.setImageResource(R.drawable.demon_idle);
            demon.setX(GifPosX);
            demon.setY(GifPosY);
            demon.setScaleX(1);
            demon.setScaleY(1);
            leveltext.setText("Level 1");
            level = 0;
        }
        else if (level == 1) {
            demon.setImageResource(R.drawable.nightmare_idle);
            demon.setScaleX(1.2f);
            demon.setScaleY(1.2f);
            demon.setX(GifPosX);
            demon.setY(GifPosY);
            demon.setX((demon.getX() - (demon.getX() * 0.2f)));
            demon.setY((demon.getY() + (demon.getY()*0.2f)));
            demon.setScaleX(1.2f);
            demon.setScaleY(1.2f);

            leveltext.setText("Level 3");
            level = 2;
        }
        else if (level == 0) {
            demon.setImageResource(R.drawable.ghost_idle);
            GifPosX = demon.getX();
            GifPosY = demon.getY();
            demon.setX((demon.getX() - (demon.getX() * 0.1f)));
            demon.setY((demon.getY() + (demon.getY()*2.3f)));
            demon.setScaleX(1.6f);
            demon.setScaleY(1.6f);
            leveltext.setText("Level 2");
            level = 1;
        }
    }
    private void clickPrevious() {
        TextView leveltext = findViewById(R.id.textLevel);
        if (level == 0) {
            GifImageView demon = findViewById(R.id.demonGif);
            demon.setImageResource(R.drawable.nightmare_idle);
            GifPosX = demon.getX();
            GifPosY = demon.getY();
            demon.setX((demon.getX() - (demon.getX() * 0.2f)));
            demon.setY((demon.getY() + (demon.getY()*0.2f)));
            demon.setScaleX(1.2f);
            demon.setScaleY(1.2f);
            leveltext.setText("Level 3");
            level = 2;
        }
        else if (level == 1) {
            GifImageView demon = findViewById(R.id.demonGif);
            demon.setImageResource(R.drawable.demon_idle);
            demon.setX(GifPosX);
            demon.setY(GifPosY);
            demon.setScaleX(1);
            demon.setScaleY(1);
            leveltext.setText("Level 1");
            level--;
        }
        else if (level == 2) {
            GifImageView demon = findViewById(R.id.demonGif);
            demon.setImageResource(R.drawable.ghost_idle);
            demon.setX(GifPosX);
            demon.setY(GifPosY);
            demon.setX((demon.getX() - (demon.getX() * 0.1f)));
            demon.setY((demon.getY() + (demon.getY()*2.3f)));
            demon.setScaleX(1.6f);
            demon.setScaleY(1.6f);
            leveltext.setText("Level 2");
            level--;
        }
    }

}