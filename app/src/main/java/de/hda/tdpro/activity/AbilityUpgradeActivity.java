package de.hda.tdpro.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import de.hda.tdpro.R;
import de.hda.tdpro.core.config.AbilityUpgradeSaving;

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
    Button buy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
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
        buy = findViewById(R.id.buttonbuy);



        death1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                message.setText("Death increased from 10 to 11\nPreis: 100$");


                death1.setSelected(true);
            }
        });
        death2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                message.setText("Death increased from 11 to 13\nPreis: 200$");
                death2.setSelected(true);


            }
        });
        death3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                message.setText("Death increased from 13 to 16\nPreis: 300$");
                death3.setSelected(true);


            }
        });
        forest1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                message.setText("Forest increased from 10 to 11\nPreis: 100$");
                forest1.setSelected(true);

            }
        });
        forest2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                message.setText("Forest incresed from 11 to 13\nPreis: 200$");
                forest2.setSelected(true);

            }
        });
        forest3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                message.setText("Forest incresed from 13 to 16\nPreis: 300$");
                forest3.setSelected(true);

            }
        });
        interference1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                message.setText("Interference increased from 10 to 11\nPreis: 100$");
                interference1.setSelected(true);

            }
        });
        interference2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                message.setText("Interference increased from 11 to 13\nPreis: 200$");
                interference2.setSelected(true);

            }
        });
        interference3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                message.setText("Interference increased from 13 to 16\nPreis: 300$");
                interference3.setSelected(true);
            }
        });
        goback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMainMenu();
            }
        });
        buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(forest1.isSelected()){
                    AbilityUpgradeSaving.forest++;
                    forest1.setClickable(false);
                    forest1.setSelected(false);


                }
                else if(forest2.isSelected()){
                    AbilityUpgradeSaving.forest++;
                    forest2.setClickable(false);
                    forest2.setSelected(false);



                }
                else if(forest3.isSelected()){
                    AbilityUpgradeSaving.forest++;
                    forest3.setClickable(false);
                    forest3.setSelected(false);



                }
                else if(death1.isSelected()){
                    AbilityUpgradeSaving.death++;
                    death1.setClickable(false);
                    death1.setSelected(false);



                }
                else if(death2.isSelected()){
                    AbilityUpgradeSaving.death++;
                    death2.setClickable(false);
                    death2.setSelected(false);


                }
                else if(death3.isSelected()){
                    AbilityUpgradeSaving.death++;
                    death3.setClickable(false);
                    death3.setSelected(false);


                }
                else if(interference1.isSelected()){
                    AbilityUpgradeSaving.interference++;
                    interference1.setClickable(false);
                    interference1.setSelected(false);


                }
                else if(interference2.isSelected()){
                    AbilityUpgradeSaving.interference++;
                    interference2.setClickable(false);
                    interference2.setSelected(false);


                }
                else if(interference3.isSelected()){
                    AbilityUpgradeSaving.interference++;
                    interference3.setClickable(false);
                    interference3.setSelected(false);



                }
            }
        });


    }
    public void openMainMenu(){
        Intent intent = new Intent(this, MainMenuActivity.class);
        startActivity(intent);

    }
    }
