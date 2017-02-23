package sb.hackernews.com;

import android.app.Application;
import android.content.Context;

import net.danlew.android.joda.JodaTimeAndroid;

/**
 * Created by Nathaniel James Lim on 8/12/2016.
 */

public class App extends Application {

    private static Context appContext;
    @Override
    public void onCreate() {
        super.onCreate();
        appContext = this;
        JodaTimeAndroid.init(this);
    }
    public static Context getAppContext() {
        return appContext;
    }
}
