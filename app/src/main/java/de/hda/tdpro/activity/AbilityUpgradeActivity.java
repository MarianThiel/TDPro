package de.hda.tdpro.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import de.hda.tdpro.R;

public class AbilityUpgradeActivity extends AppCompatActivity {
    ImageButton death1;
    ImageButton death2;
    ImageButton death3;
    ImageButton forest1;
    ImageButton forest2;
    ImageButton forest3;
    ImageButton interference1;
    ImageButton interference2;
    ImageButton interference3;
    TextView message;
    ImageButton goback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ability_upgrade);

        death1 = findViewById(R.id.imageButton);
        death2 = findViewById(R.id.imageButton4);
        death3 = findViewById(R.id.imageButton7);
        forest1 = findViewById(R.id.imageButton2);
        forest2 = findViewById(R.id.imageButton5);
        forest3 = findViewById(R.id.imageButton8);
        interference1 = findViewById(R.id.imageButton3);
        interference2 = findViewById(R.id.imageButton6);
        interference3 = findViewById(R.id.imageButton9);
        goback = findViewById(R.id.returnFromAbility);
        message = findViewById(R.id.messageFromAbility);




        death1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                message.setText("Death increased from 10 to 11\nPreis: 100$");
            }
        });
        death2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                message.setText("Death increased from 11 to 13\nPreis: 200$");
            }
        });
        death3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                message.setText("Death increased from 13 to 16\nPreis: 300$");
            }
        });
        forest1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                message.setText("Forest increased from 10 to 11\nPreis: 100$");
            }
        });
        forest2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                message.setText("Forest incresed from 11 to 13\nPreis: 200$");
            }
        });
        forest3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                message.setText("Forest incresed from 13 to 16\nPreis: 300$");
            }
        });
        interference1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                message.setText("Interference increased from 10 to 11\nPreis: 100$");
            }
        });
        interference2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                message.setText("Interference increased from 11 to 13\nPreis: 200$");
            }
        });
        interference3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                message.setText("Interference increased from 13 to 16\nPreis: 300$");
            }
        });
        goback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMainMenu();
            }
        });


    }
    public void openMainMenu(){
        Intent intent = new Intent(this, MainMenuActivity.class);
        startActivity(intent);

    }
    }
