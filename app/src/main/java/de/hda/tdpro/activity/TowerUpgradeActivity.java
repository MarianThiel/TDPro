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
import de.hda.tdpro.core.config.TowerUpgradeSaving;

public class TowerUpgradeActivity extends AppCompatActivity {
    ImageButton damage1;
    ImageButton damage2;
    ImageButton damage3;
    ImageButton speed1;
    ImageButton speed2;
    ImageButton speed3;
    ImageButton weit1;
    ImageButton weit2;
    ImageButton weit3;
    TextView message;
    ImageButton goback;
    Button buy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_tower_upgrade);
        damage1= findViewById(R.id.damage1);
        damage2=findViewById(R.id.damage2);
        damage3=findViewById(R.id.damage3);
        speed1=findViewById(R.id.speed1);
        speed2=findViewById(R.id.speed2);
        speed3=findViewById(R.id.speed3);
        weit1=findViewById(R.id.weit1);
        weit2=findViewById(R.id.weit2);
        weit3=findViewById(R.id.weit3);
        goback= findViewById(R.id.returnButton);
        message= findViewById(R.id.message);
        buy=findViewById(R.id.buy);
        damage1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                message.setText("Tower 1 damage increased to 120%\nPreis: 100$");
                damage1.setSelected(true);
            }
        });
        damage2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                message.setText("Tower 2 damage increased to 120%\nPreis: 100$");
                damage2.setSelected(true);

            }
        });
        damage3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                message.setText("Tower 3 damage increased to 120%\nPreis: 100$");
                damage3.setSelected(true);

            }
        });
        speed1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                message.setText("Tower 1 speed increased to 120%\nPreis: 100$");
                speed1.setSelected(true);

            }
        });
        speed2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                message.setText("Tower 2 speed increased fto 120%\nPreis: 100$");
                speed2.setSelected(true);

            }
        });
        speed3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                message.setText("Tower 3 speed increased to 120%\nPreis: 100$");
                speed3.setSelected(true);

            }
        });
        weit1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                message.setText("Tower 1 range increased to 120%\nPreis: 100$");
                weit1.setSelected(true);
            }
        });
        weit2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                message.setText("Tower 2 range increased to 120%\nPreis: 100$");
                weit2.setSelected(true);
            }
        });
        weit3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                message.setText("Tower 3 range increased to 120%\nPreis: 100$");
                weit3.setSelected(true);
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
                if(damage1.isSelected()){
                    TowerUpgradeSaving.damage1++;
                    damage1.setClickable(false);
                    damage1.setSelected(false);

                }
                else if(damage2.isSelected()){
                    TowerUpgradeSaving.damage2++;
                    damage2.setClickable(false);
                    damage2.setSelected(false);



                }
                else if(damage3.isSelected()){
                    TowerUpgradeSaving.damage3++;
                    damage3.setClickable(false);
                    damage3.setSelected(false);



                }
                else if(speed1.isSelected()){
                    TowerUpgradeSaving.speed1++;
                    speed1.setClickable(false);
                    speed1.setSelected(false);

                }
                else if(speed2.isSelected()){
                    TowerUpgradeSaving.speed2++;
                    speed2.setClickable(false);
                    speed2.setSelected(false);

                }
                else if(speed3.isSelected()){
                    TowerUpgradeSaving.speed3++;
                    speed3.setClickable(false);
                    speed3.setSelected(false);

                }
                else if(weit1.isSelected()){
                    TowerUpgradeSaving.range1++;
                    weit1.setClickable(false);
                    weit1.setSelected(false);
                }
                else if(weit2.isSelected()){
                    TowerUpgradeSaving.range2++;
                    weit2.setClickable(false);
                    weit2.setSelected(false);

                }
                else if(weit3.isSelected()){
                    TowerUpgradeSaving.range3++;
                    weit3.setClickable(false);
                    weit3.setSelected(false);

                }

            }
        });


    }
    public void openMainMenu(){
        Intent intent = new Intent(this, MainMenuActivity.class);
        startActivity(intent);

    }
}