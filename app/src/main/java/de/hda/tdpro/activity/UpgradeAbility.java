package de.hda.tdpro.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import de.hda.tdpro.ConfigWriter;
import de.hda.tdpro.GameUpgrade;
import de.hda.tdpro.R;

public class UpgradeAbility extends AppCompatActivity {

    private ImageButton backButton;
    private TextView amountDiamond3;
    private ImageView towerView;
    private ImageView lifeView;
    private Button upgradeTower;
    private Button upgradeLife;
    private TextView text;
    private int costTower =5;
    private int costLife=5;
    private int levelTower=0;
    private int levelHealth=0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upgrade_ability);
        amountDiamond3= findViewById(R.id.amountOfDiamonds3);
        amountDiamond3.setText(Integer.toString(ConfigWriter.getInstance().readDiamonds()));

        towerView = findViewById(R.id.imageView44);
        lifeView = findViewById(R.id.imageView45);
        upgradeTower= findViewById(R.id.upgradeTower);
        upgradeLife= findViewById(R.id.upgradeHeart);
        text = findViewById(R.id.textView22);
        informationAnzeigen();




        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        initComponents();
        initListeners();
    }

    private void initComponents(){
        backButton = findViewById(R.id.ua_back);
    }

    private void initListeners(){
        backButton.setOnClickListener(e->{
            Intent i = new Intent(UpgradeAbility.this, MainMenuActivity.class);
            startActivity(i);
        });
    }
    public void informationAnzeigen(){
        towerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                text.setText("State of Attributes and Upgrade Information\n" +
                        "Tower now have the Level of "+levelTower+ "\nPrice for the next Upgrade "+ costTower+ " diamonds\n"
                +"After Upgrade you will have "+ 5 +levelTower +" Tower");

            }
        });
        lifeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                text.setText("State of Attributes and Upgrade Information\n" +
                        " You have now at the level "+levelHealth  +" of life \n"+ "Price for the next Upgrade "+ costLife +" diamonds \n"
                +"After Upgrade you will have"+ 100+ levelHealth*15 +" life");
            }
        });
        upgradeTower.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ConfigWriter.getInstance().readDiamonds()>=costTower) {
                    GameUpgrade game = new GameUpgrade("maxtower");
                    //ConfigWriter.getInstance().writeInitHealth(ConfigWriter.getInstance().);
                    ConfigWriter.getInstance().writeDiamonds(ConfigWriter.getInstance().readDiamonds() - costTower);
                    costTower*=1.5;
                }
                else{
                    text.setText("You dont have enough Diamonds");
                }
            }
        });
        upgradeLife.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                if(ConfigWriter.getInstance().readDiamonds()>=costLife) {
                    ConfigWriter.getInstance().writeInitHealth(ConfigWriter.getInstance().readHealth() + costLife);
                    ConfigWriter.getInstance().writeDiamonds(ConfigWriter.getInstance().readDiamonds() - costLife);
                    costLife*=1.5;
                }
                else{
                    text.setText("You dont have enough Diamonds");
                }
            }
        });


}
    }