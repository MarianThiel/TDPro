package de.hda.tdpro.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.io.InputStream;

import de.hda.tdpro.R;
import de.hda.tdpro.StaticContext;
import de.hda.tdpro.core.factories.GameFactory;
import de.hda.tdpro.core.tower.upgrades.MetaUpgrade;
import de.hda.tdpro.view.DemoView;
import de.hda.tdpro.core.Game;
import de.hda.tdpro.core.GameListener;
import de.hda.tdpro.core.PointingMode;
import de.hda.tdpro.core.tower.Tower;
import de.hda.tdpro.view.TowerBuyView;
import de.hda.tdpro.view.TowerStatView;
import de.hda.tdpro.view.TowerUpgradeView;

public class InGameActivity extends AppCompatActivity implements GameListener {

    private int showsBuyTowers = 0;
    /**
     * The demo view
     */
    private DemoView gameView;
    /**
     * the model of game
     */
    private Game gameModel;

    private ImageButton btnNextWave;

    private ImageButton btnPausePlay;

    private ImageButton btnFastForward;

    private ImageButton btnTowerCreate;

    private ImageButton btnSettings;

    private ScrollView contextMenu;

    private LinearLayout contextMenuLayout;

    private TextView txt_health;
    private TextView txt_gold;
    private TextView txt_waves;

    private boolean run;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        StaticContext.setContext(this);
        // gameView = new DemoView(this);
        // this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_in_game);
        gameView = findViewById(R.id.view);
        init();
        hideContextMenu();
        updateStats();

        run = true;

    }

    @Override
    protected void onPause() {
        super.onPause();
        gameView.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        gameView.resume();
    }

    private void init(){

        gameModel = GameFactory.getInstance().createLevelOne();
        gameModel.addGameListener(this);
        gameModel.addGameListener(gameView);
        gameView.setGameModel(gameModel);

        btnNextWave = findViewById(R.id.btnNextWave);
        btnFastForward = findViewById(R.id.btnFastForward);
        btnPausePlay = findViewById(R.id.btnPausePlay);
        btnTowerCreate = findViewById(R.id.btnCreateTower);
        btnSettings = findViewById(R.id.btnSettings);
        contextMenu = findViewById(R.id.contextMenu);
        contextMenuLayout = findViewById(R.id.contextMenuLayout);
        txt_health = findViewById(R.id.txthealth);
        txt_gold = findViewById(R.id.txtgold);
        txt_waves = findViewById(R.id.txtwaves);


        btnNextWave.setOnClickListener(e->{
            gameModel.startNextWave();
        });
        btnSettings.setOnClickListener(e->{
            
        });
        btnTowerCreate.setOnClickListener(e->{
            showTowerBuyView();
        });
        btnPausePlay.setOnClickListener(e->{
            if(run){
                gameView.pause();
                btnPausePlay.setActivated(!btnPausePlay.isActivated());
                run = false;
            }else{
                gameView.resume();
                btnPausePlay.setActivated(!btnPausePlay.isActivated());
                run = true;
            }
        });
        btnFastForward.setOnClickListener(e->{
            btnFastForward.setActivated(!btnFastForward.isActivated());
        });
    }

    private void validateContextMenu(PointingMode mode){
        switch (mode){
            case SELECTION_MODE:
                //TODO: write code for displaying tower selection context
                if(gameView.game.getSelectedTower() != null){
                    showSelectionContext(gameView.game.getSelectedTower());
                }else{
                    showSelectionContext(null);
                    hideContextMenu();
                }
                break;
            case PLACE_TOWER_MODE:
                //TODO: list al tower Types to select from
                break;
            case USE_ABILITY_MODE:
                //TODO: handle ability context
                break;
        }
    }
    private void showSelectionContext(Tower t){
        if(t != null){
            //TODO: present tower attributes and upgrade/sell options
            showTowerSelection();
        }else{
            hideContextMenu();
        }
    }
    private void showTowerBuyView() {
        if ( showsBuyTowers == 0 ) {
            showTowerBuy();
            showsBuyTowers = 1;
        }else if (showsBuyTowers == 1){
            hideContextMenu();
            showsBuyTowers = 0;
        }
    }
    private void showTowerBuy(){
        contextMenuLayout.removeAllViews();
        TowerBuyView view = new TowerBuyView(this);
        view.buyFireView();
        contextMenuLayout.addView(view);
        contextMenu.setVisibility(View.VISIBLE);
    }
    private void showTowerSelection(){
        contextMenuLayout.removeAllViews();
        TowerStatView view = new TowerStatView(this);
        view.initTower(gameModel.getSelectedTower());

        for(MetaUpgrade meta : gameModel.getUpgrades()){
            TowerUpgradeView tuv = new TowerUpgradeView(this);
            tuv.updateView(meta);
            view.AddUpgradeView(tuv);

        }

        contextMenuLayout.addView(view);
        contextMenu.setVisibility(View.VISIBLE);
    }

    private void updateStats(){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                txt_health.setText(Integer.toString(gameModel.getHealth()));
                txt_waves.setText(gameModel.getCurrentWave() + "/" + gameModel.getMaxWaves());
                txt_gold.setText(Integer.toString(gameModel.getGold()));
            }
        });

    }

    private void hideContextMenu(){
        contextMenu.setVisibility(View.GONE);
    }

    @Override
    public void updateOnSelection() {
        showSelectionContext(gameModel.getSelectedTower());
    }

    @Override
    public void updateOnGameOver() {
        //Intent intent = new Intent(InGameActivity.this, EndGameActivity.class);
        //startActivity(intent);
    }

    @Override
    public void updateOnChange() {
        updateStats();
    }
}