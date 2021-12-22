package de.hda.tdpro.core.enemy;

public class EnemyWave {

    private final int ENEMIES_IN_WAVE;

    private final Enemy[] enemies;
    public EnemyWave(int ENEMIES_IN_WAVE) {
        this.ENEMIES_IN_WAVE = ENEMIES_IN_WAVE;
        enemies = new Enemy[ENEMIES_IN_WAVE];
    }
}
