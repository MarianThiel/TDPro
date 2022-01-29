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
import android.widget.Toast;

import de.hda.tdpro.core.persistence.ConfigWriter;
import de.hda.tdpro.GameUpgrade;
import de.hda.tdpro.R;

public class UpgradeAbility extends AppCompatActivity {

    private ImageButton backButton;
    private TextView amountDiamond3;

    private Button upgradeLife;

    private ImageButton btnHeart;
    private ImageButton btnTower;
    private TextView text;
    private GameUpgrade game;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upgrade_ability);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        amountDiamond3= findViewById(R.id.amountOfDiamonds3);
        amountDiamond3.setText(Integer.toString(ConfigWriter.getInstance().readDiamonds()));
        game = new GameUpgrade("maxhealth");
        game.readFromConfig();


        upgradeLife= findViewById(R.id.upgradeHeart);
        text = findViewById(R.id.textView22);
        btnHeart = findViewById(R.id.buttonHeart);
        btnTower = findViewById(R.id.buttonTower);

        initComponents();
        initListeners();

        showHeartMessage();
    }

    private void initComponents(){
        backButton = findViewById(R.id.ua_back);
    }

    private void initListeners(){
        backButton.setOnClickListener(e->{
            Intent i = new Intent(UpgradeAbility.this, MainMenuActivity.class);
            startActivity(i);
        });

        btnHeart.setOnClickListener(e->{
            showHeartMessage();
        });
        btnTower.setOnClickListener(e->{
            showTowerMessage();
        });

        upgradeLife.setOnClickListener(e -> {
            game.readFromConfig();

            if(ConfigWriter.getInstance().readDiamonds()>=game.getCosts()) {

                if(game.getKey().equals("maxhealth")){
                    ConfigWriter.getInstance().writeInitHealth(ConfigWriter.getInstance().readHealth() + game.getValue());
                }else{
                    ConfigWriter.getInstance().writeMaxTowers(ConfigWriter.getInstance().readMaxTowers()+game.getValue());
                }

                ConfigWriter.getInstance().writeDiamonds(ConfigWriter.getInstance().readDiamonds() - game.getCosts());
                game.setCosts((int)(game.getCosts()*game.getMulti()));
                game.setCurrentLevel(game.getCurrentLevel()+1);
                game.writeToConfig();
                if(game.getKey().equals("maxhealth"))
                    showHeartMessage();
                else
                    showTowerMessage();

            }
            else{
                Toast t = Toast.makeText(getApplicationContext(),"can't afford Upgrade", Toast.LENGTH_SHORT);
                t.show();
            }
        });


    }




    private void showHeartMessage(){
        btnHeart.setActivated(true);
        btnTower.setActivated(false);
        game = new GameUpgrade("maxhealth");
        game.readFromConfig();
        text.setText("State of Attributes and Upgrade Information\n" +
                " You have now "+game.getValue()  +" lifes \n"+ "Price for the next Upgrade "+ game.getCosts() +" diamonds \n"
        );
        amountDiamond3.setText(Integer.toString(ConfigWriter.getInstance().readDiamonds()));
    }

    private void showTowerMessage(){
        btnHeart.setActivated(false);
        btnTower.setActivated(true);
        game = new GameUpgrade("maxtower");
        game.readFromConfig();
        text.setText("State of Attributes and Upgrade Information\n" +
                "You have now "+game.getValue() +" Towers"+"\nPrice for the next Upgrade "+ game.getCosts()+ " diamonds\n"
        );
        amountDiamond3.setText(Integer.toString(ConfigWriter.getInstance().readDiamonds()));
    }
}