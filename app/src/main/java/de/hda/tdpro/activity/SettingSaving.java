package de.hda.tdpro.activity;


public final class SettingSaving {

    private static int textSize;
    private SettingSaving(){
        //20 dp
        textSize = 20;
    }
    public static void setTextSize(int size){
        textSize = size;
    }
    public static int getTextSize(){
        return textSize;
    }
}
