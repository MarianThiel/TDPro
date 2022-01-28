package de.hda.tdpro.view.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.Button;

import de.hda.tdpro.R;
import de.hda.tdpro.activity.EndGameActivity;
import de.hda.tdpro.activity.LexiconActivity;
import de.hda.tdpro.activity.SettingsActivity;
import de.hda.tdpro.core.Game;
import de.hda.tdpro.core.persistence.GameStateSaver;

public class MenuDialog extends Dialog {

    private Button btnSettings;

    private Button btnHelp;

    private Button btnExit;

    private Game game;

    private Context context;

    public MenuDialog(@NonNull Context context, Game game) {
        super(context);
        this.game = game;
        initDialog();
        initListeners();
        this.context = context;

    }

    public MenuDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected MenuDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    private void initDialog(){
        setContentView(R.layout.menu_dialog);
        btnSettings = findViewById(R.id.btnSettings);

        btnHelp = findViewById(R.id.btnHelp);

        btnExit = findViewById(R.id.btnExit);

    }


    private void initListeners(){
        btnSettings.setOnClickListener(e->{
            try {
                GameStateSaver.getInstance().saveGameInstance(game);

            } catch (Exception exception) {
                exception.printStackTrace();
            }
            Intent i = new Intent(getContext(), SettingsActivity.class);
            i.putExtra("INGAME", true);
            getContext().startActivity(i);
        });

        btnHelp.setOnClickListener(e->{
            try {
                GameStateSaver.getInstance().saveGameInstance(game);

            } catch (Exception exception) {
                exception.printStackTrace();
            }
            Intent i = new Intent(getContext(), LexiconActivity.class);
            i.putExtra("INGAME", true);
            getContext().startActivity(i);
        });

        btnExit.setOnClickListener(e->{

                GameStateSaver.getInstance().reset();
                Intent intent = new Intent(getContext(), EndGameActivity.class);
                intent.putExtra("WIN",false);
                intent.putExtra("WON_DIAM",game.getWonDiamonds());
                intent.putExtra("DIAM",game.getDiamonds());
                intent.putExtra("CHECKPOINT",game.getCheckpoint());
                getContext().startActivity(intent);
                ((Activity) context).finish();



        });
    }
}
