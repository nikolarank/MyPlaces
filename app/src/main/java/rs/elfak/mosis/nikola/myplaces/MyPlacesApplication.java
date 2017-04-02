package rs.elfak.mosis.nikola.myplaces;

import android.app.Application;
import android.content.Context;

/**
 * Created by Nikola on 2017-04-02.
 */

public class MyPlacesApplication extends Application {
    private static MyPlacesApplication instance;

    public MyPlacesApplication() {
        instance = this;
    }

    public static Context getContext(){
        return instance;
    }
}
