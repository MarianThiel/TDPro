package de.hda.tdpro.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import de.hda.tdpro.R;
import de.hda.tdpro.SFXManager;
import de.hda.tdpro.StaticContext;
import de.hda.tdpro.core.GameStateSaver;
import de.hda.tdpro.core.factories.GameFactory;
import de.hda.tdpro.core.tower.TowerType;
import de.hda.tdpro.core.tower.upgrades.UpgradeType;
import de.hda.tdpro.view.DemoView;
import de.hda.tdpro.core.Game;
import de.hda.tdpro.core.GameListener;
import de.hda.tdpro.core.PointingMode;
import de.hda.tdpro.core.tower.Tower;
import de.hda.tdpro.view.TowerBuyView;
import de.hda.tdpro.view.TowerStatView;
import de.hda.tdpro.view.TowerUpgradeView;
import de.hda.tdpro.view.TowerView;
import de.hda.tdpro.view.dialog.MenuDialog;

public class InGameActivity extends AppCompatActivity implements GameListener {


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

    private ImageButton btnBuyAbort;

    private ImageButton btnHideShowContext;

    private FrameLayout contextMenuLayout;

    private Dialog menu;

    private TextView txt_health;

    private TextView txt_gold;

    private TextView txt_waves;
    /**
     * List of all upgradeTypes
     */
    private List<UpgradeType> upgrades;
    /**
     * List of all TowerTypes
     */
    private List<TowerType> towers;
    /**
     * width of screen
     */
    private int width;
    /**
     * height of screen
     */
    private int height;
    /**
     * running state
     */
    private boolean run;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.println(Log.ASSERT,"test", "CREATE");
        initActivity();

        gameView = findViewById(R.id.view);

        init();

        hideContextMenu();
        updateStats();
        initMenu();

        run = true;
        initDimensions();


    }

    private void initDimensions() {
        WindowManager wm = ((WindowManager) StaticContext.getContext().getSystemService(Context.WINDOW_SERVICE));
        DisplayMetrics metrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(metrics);

        height = metrics.heightPixels;
        width = metrics.widthPixels;
        if(height>width){
            int t = width;
            width = height;
            height = t;
        }
    }

    private void initActivity() {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        setContentView(R.layout.activity_in_game);
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }

    @Override
    protected void onStart() {
        try {
            gameModel = GameStateSaver.getInstance().loadGame();
            gameModel.resume();
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.onStart();
        Log.println(Log.ASSERT,"test", "START");
    }

    @Override
    protected void onStop() {
        gameView.pause();
        try {
            GameStateSaver.getInstance().saveGameInstance(gameModel);
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.onStop();
        Log.println(Log.ASSERT,"test", "STOP");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        GameStateSaver.getInstance().reset();
        Log.println(Log.ASSERT,"test", "DESTROY");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.println(Log.ASSERT,"test", "PAUSE");
        gameView.pause();
    }

    @Override
    protected void onResume() {

        super.onResume();
        Log.println(Log.ASSERT,"test", "RESUME");
        gameView.resume();
    }

    private void init(){

        initGame();

        initComponents();

        initButtonListeners();

    }

    public void setTowerPlacingMode(TowerType type){
        btnBuyAbort.setVisibility(View.VISIBLE);
        gameView.setMode(PointingMode.PLACE_TOWER_MODE);
        gameView.setPlacingTowerType(type);
        Toast.makeText(this,"Tab anywhere to place the Tower",Toast.LENGTH_SHORT).show();
    }

    /**
     * initialize buttonListeners
     */
    private void initButtonListeners() {
        btnNextWave.setOnClickListener(e->{
            if(gameModel.startNextWave()){
                if(btnFastForward.isActivated()){
                    gameModel.fastForward(false);
                    gameModel.fastForward(true);

                }
                SFXManager.getInstance().playNextWave();
            }
        });

        btnSettings.setOnClickListener(e->{
            gameView.pause();
            menu.show();
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
                gameModel.resume();
                btnPausePlay.setActivated(!btnPausePlay.isActivated());
                run = true;
            }
        });

        btnFastForward.setOnClickListener(e->{
            gameModel.fastForward(!btnFastForward.isActivated());
            btnFastForward.setActivated(!btnFastForward.isActivated());
        });

        btnBuyAbort.setOnClickListener(e->{
            gameView.setMode(PointingMode.SELECTION_MODE);
            btnBuyAbort.setVisibility(View.GONE);
        });

        btnHideShowContext.setOnClickListener(e->{
            if(btnHideShowContext.isActivated()){
                contextMenuLayout.animate().translationY(0);
            }else{
                contextMenuLayout.animate().translationY(height);
            }
            btnHideShowContext.setActivated(!btnHideShowContext.isActivated());
        });
    }

    /**
     * initialize the game
     * Decide if a running game was saved and loads it or creates new GameInstance
     *
     */
    private void initGame() {

        upgrades = new LinkedList<>(Arrays.asList(UpgradeType.values()));
        towers = new LinkedList<>(Arrays.asList(TowerType.values()));

        try{
            gameModel = GameStateSaver.getInstance().loadGame();
            gameModel.resume();
        } catch (Exception e) {
            Log.println(Log.ASSERT,"LOAD", e.getMessage());
            loadLevel(-1);
        }

        gameModel.addGameListener(this);
        gameModel.addGameListener(gameView);
        gameView.setGameModel(gameModel);
    }

    private void initComponents() {

        btnNextWave = findViewById(R.id.btnNextWave);
        btnFastForward = findViewById(R.id.btnFastForward);
        btnPausePlay = findViewById(R.id.btnPausePlay);
        btnTowerCreate = findViewById(R.id.btnCreateTower);
        btnSettings = findViewById(R.id.btnSettings);
        btnBuyAbort = findViewById(R.id.buyTowerAbort);
        btnHideShowContext = findViewById(R.id.btnHideShowContext);
        contextMenuLayout = findViewById(R.id.contextMenuLayout);
        txt_health = findViewById(R.id.txthealth);
        txt_gold = findViewById(R.id.txtgold);
        txt_waves = findViewById(R.id.txtwaves);


        btnBuyAbort.setVisibility(View.GONE);
        btnHideShowContext.setVisibility(View.GONE);
    }

    /**
     * shows the Contxtmenu for selected Tower hides it if t = null
     * @param t the tower which was selected
     */
    private void showSelectionContext(Tower t){
        if(t != null){
            showTowerSelection();
            btnHideShowContext.setVisibility(View.VISIBLE);
        }else{
            hideContextMenu();
            btnHideShowContext.setVisibility(View.GONE);
        }
    }
    private void showTowerBuyView() {

            showTowerBuy();

    }
    private void showTowerBuy(){
        contextMenuLayout.removeAllViews();
        TowerBuyView view = new TowerBuyView(this);
        //contextMenuLayout.addView(view);
       // contextMenu.setVisibility(View.VISIBLE);

        for(TowerType meta : towers){
            TowerView tv = new TowerView(this, meta);
            view.addTowerView(tv);
        }
        contextMenuLayout.addView(view);

        contextMenuLayout.animate().translationY(0);
    }

    /**
     * fills the context menu with views of upgrades
     */
    private void showTowerSelection(){
        contextMenuLayout.removeAllViews();
        TowerStatView view = new TowerStatView(this, gameModel);
        view.initTower(gameModel.getSelectedTower());

        for(UpgradeType meta : upgrades){
            TowerUpgradeView tuv = new TowerUpgradeView(this, gameModel);
            tuv.updateView(meta);
            view.AddUpgradeView(tuv);

        }

        contextMenuLayout.addView(view);
        //contextMenuLayout.setVisibility(View.VISIBLE);
        contextMenuLayout.animate().translationY(0);
    }

    /**
     * updates the game values displayed
     */
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

    /**
     * set the visibility of context menu
     */
    public void hideContextMenu(){
        contextMenuLayout.animate().translationY(height);
        //contextMenuLayout.setVisibility(View.GONE);
    }

    @Override
    public void updateOnSelection() {
        showSelectionContext(gameModel.getSelectedTower());

    }

    @Override
    public void updateOnGameOver() {
        gameView.pause();
        GameStateSaver.getInstance().reset();
        Intent intent = new Intent(InGameActivity.this, EndGameActivity.class);
        intent.putExtra("WIN",false);
        intent.putExtra("WON_DIAM",gameModel.getWonDiamonds());
        intent.putExtra("DIAM",gameModel.getDiamonds());
        intent.putExtra("CHECKPOINT",gameModel.getCheckpoint());
        startActivity(intent);

        finish();
    }

    @Override
    public void updateOnChange() {
        updateStats();
    }

    @Override
    public void updateOnGameWinning() {
        GameStateSaver.getInstance().reset();

        Intent intent = new Intent(InGameActivity.this, EndGameActivity.class);
        intent.putExtra("WIN",true);
        intent.putExtra("WON_DIAM",gameModel.getWonDiamonds());
        intent.putExtra("DIAM",gameModel.getDiamonds());
        intent.putExtra("CHECKPOINT",gameModel.getCheckpoint());
        startActivity(intent);
        finish();
    }

    @Override
    public void updateOnTowerPlacement() {
        btnBuyAbort.setVisibility(View.GONE);
    }

    @Override
    public void updateOnCheckpoint() {
        SFXManager.getInstance().playCheckpointReached();
        //Dialog d = new Dialog(this);
        //d.show();
    }

    private void loadLevel(int i){
        switch (i){
            case 0:

                break;
            default:
                gameModel = GameFactory.getInstance().createDemoLevel();
                break;
        }
    }

    private void initMenu(){
        menu = new MenuDialog(this,gameModel);
        menu.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                gameView.resume();
                gameModel.resume();
            }
        });
    }


}