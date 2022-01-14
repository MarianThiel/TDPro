package de.hda.tdpro.core;

/**
 * @author Marian Thiel
 * class for save and load a game instance
 */
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

    /**
     * saves game instance if not blocked
     * @param game the game instance
     * @throws Exception when saving blocked
     */
    public void saveGameInstance(Game game) throws Exception {
        if(!saveBlock){
            gameInstance = game;
            saveBlock = true;
            loadBlock = false;
        }else {
            throw new Exception("unloaded Game instance");
        }
    }

    /**
     * loads game instance if not blocked
     * resets blockers
     * @throws Exception when loading is blocked
     */
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
