package de.hda.tdpro;

import android.app.Application;
import android.content.Context;

public class StaticContext {

    private static Context mContext;

    public static void setContext(Context c){
        mContext = c;
    }

    public static Context getContext(){
        return mContext;
    }
}
