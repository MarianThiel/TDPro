package de.hda.tdpro.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import de.hda.tdpro.ConfigWriter;
import de.hda.tdpro.R;
import de.hda.tdpro.core.GameStateSaver;

public class EndGameActivity extends AppCompatActivity {

    private TextView endGameMessage;
    private Button backButton;
    private TextView addDiam;
    private TextView diam;
    private TextView checkp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_game);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        GameStateSaver.getInstance().reset();

        endGameMessage = findViewById(R.id.EndGameMessage);
        backButton = findViewById(R.id.ReturnToLevelSelect);
        addDiam = findViewById(R.id.addDiamonds);
        diam = findViewById(R.id.amountOfDiamonds);
        checkp = findViewById(R.id.checkpointReached);

        Intent intent = getIntent();
        boolean win = intent.getBooleanExtra("WIN", false);
        int adiam = intent.getIntExtra("WON_DIAM",0);
        int d = intent.getIntExtra("DIAM",0);
        int checkpoint= intent.getIntExtra("CHECKPOINT",0);

        addDiam.setText("+" + adiam);
        diam.setText("" + ConfigWriter.getInstance().readDiamonds());
        checkp.setText("Wave " + checkpoint +1);

        if(win)
        {
            endGameMessage.setText("Level Completed");
            endGameMessage.setTextAppearance(R.style.endgame_won);
        }
        else
        {
            endGameMessage.setText("GAME OVER");
        }
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(EndGameActivity.this, MainMenuActivity.class);
                startActivity(intent1);
            }
        });
    }
}