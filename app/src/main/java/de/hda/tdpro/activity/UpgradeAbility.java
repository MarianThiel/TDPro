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

import de.hda.tdpro.core.persistence.ConfigWriter;
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
    GameUpgrade game;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upgrade_ability);
        amountDiamond3= findViewById(R.id.amountOfDiamonds3);
        amountDiamond3.setText(Integer.toString(ConfigWriter.getInstance().readDiamonds()));
        game = new GameUpgrade("maxtower");
        game.readFromConfig();

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
                game = new GameUpgrade("maxtower");
                game.readFromConfig();
                text.setText("State of Attributes and Upgrade Information\n" +
                        "You have now "+game.getValue() +" Towers"+"\nPrice for the next Upgrade "+ game.getCosts()+ " diamonds\n"
               );

            }
        });
        lifeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                game = new GameUpgrade("maxhealth");
                game.readFromConfig();
                text.setText("State of Attributes and Upgrade Information\n" +
                        " You have now "+game.getValue()  +" lifes \n"+ "Price for the next Upgrade "+ game.getCosts() +" diamonds \n"
               );
            }
        });
        upgradeTower.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                game = new GameUpgrade("maxtower");
                game.readFromConfig();
                if(ConfigWriter.getInstance().readDiamonds()>=game.getCosts()) {

                    ConfigWriter.getInstance().writeDiamonds(ConfigWriter.getInstance().readDiamonds() - game.getCosts());
                    ConfigWriter.getInstance().writeMaxTowers(ConfigWriter.getInstance().readMaxTowers()+game.getValue());
                    game.setCosts((int)(game.getCosts()*game.getMulti()));
                    game.setCurrentLevel(game.getCurrentLevel()+1);
                    game.writeToConfig();
                }
                else{
                    text.setText("You dont have enough Diamonds");
                }
            }
        });
        upgradeLife.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                game = new GameUpgrade("maxhealth");
                game.readFromConfig();
                if(ConfigWriter.getInstance().readDiamonds()>=game.getCosts()) {
                    ConfigWriter.getInstance().writeInitHealth(ConfigWriter.getInstance().readHealth() + game.getValue());
                    ConfigWriter.getInstance().writeDiamonds(ConfigWriter.getInstance().readDiamonds() - game.getCosts());
                    game.setCosts((int)(game.getCosts()*game.getMulti()));
                    game.setCurrentLevel(game.getCurrentLevel()+1);
                    game.writeToConfig();
                }
                else{
                    text.setText("You dont have enough Diamonds");
                }
            }
        });


}
    }