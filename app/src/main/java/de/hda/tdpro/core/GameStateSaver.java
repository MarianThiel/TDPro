package de.hda.tdpro.core;

public class GameStateSaver {

    private static GameStateSaver instance;

    private Game gameInstance;

    private boolean saveBlock;

    private boolean loadBlock;

    public GameStateSaver() {
        saveBlock = false;
        loadBlock = true;
    }

    public static GameStateSaver getInstance() {
        if(instance == null){
            instance = new GameStateSaver();
        }
        return instance;
    }

    public void saveGameInstance(Game game) throws Exception {
        if(!saveBlock){
            gameInstance = game;
            saveBlock = true;
            loadBlock = false;
        }else {
            throw new Exception("unloaded Game instance");
        }
    }

    public Game loadGame() throws Exception {
        if(!loadBlock){
            Game g = gameInstance;
            gameInstance = null;
            saveBlock = false;
            loadBlock = true;
            return g;
        }else{
            throw new Exception("nothing to load from");
        }
    }
}
