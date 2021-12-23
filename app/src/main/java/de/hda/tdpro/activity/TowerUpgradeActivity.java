package de.hda.tdpro.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import de.hda.tdpro.R;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        damage1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                message.setText("Damage increased from 10 to 11\nPreis: 100$");
            }
        });
        damage2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                message.setText("Damage increased from 11 to 13\nPreis: 200$");
            }
        });
        damage3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                message.setText("Damage increased from 13 to 16\nPreis: 300$");
            }
        });
        speed1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                message.setText("Speed increased from 10 to 11\nPreis: 100$");
            }
        });
        speed2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                message.setText("Speed incresed from 11 to 13\nPreis: 200$");
            }
        });
        speed3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                message.setText("Damage incresed from 13 to 16\nPreis: 300$");
            }
        });
        weit1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                message.setText("Range incresed from 10 to 11\nPreis: 100$");
            }
        });
        weit2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                message.setText("Range incresed from 11 to 13\nPreis: 200$");
            }
        });
        weit3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                message.setText("Range incresed from 13 to 16\nPreis: 300$");
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