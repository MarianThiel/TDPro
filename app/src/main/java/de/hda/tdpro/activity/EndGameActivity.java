package de.hda.tdpro.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import de.hda.tdpro.R;

public class EndGameActivity extends AppCompatActivity {

    private TextView endGameMessage;
    private ImageButton backButton;
    private TextView earnedMoney;
    private TextView enemiesKilled;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_game);

        endGameMessage = findViewById(R.id.EndGameMessage);
        backButton = findViewById(R.id.ReturnToLevelSelect);
        earnedMoney = findViewById(R.id.EarnedMoney);
        enemiesKilled = findViewById(R.id.EnemiesKilled);


        //Intent intentMoney = getIntent();
        //int money = intentMoney.getIntExtra("Gold: ", );

        //Intent intentEnemiesKilled = getIntent();
        //int eKilled = intentEnemiesKilled.getIntExtra("EnemiesKilled: ", );

        Intent intent = getIntent();
        boolean win = intent.getBooleanExtra("WIN", false);

        if(win)
        {
            endGameMessage.setText("Du hast gewonnen!");
        }
        else
        {
            endGameMessage.setText("Du hast verloren!");
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