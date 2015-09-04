package io.ckl.challenge.max.control;

import android.app.Application;

import com.raizlabs.android.dbflow.config.FlowManager;

/**
 *
 * Necessary to initialize DBFlow (open databases, creations, etc).
 *
 * Created by Max Jr on 04/09/2015.
 */
public class ChallengeApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        FlowManager.init(this);
    }

}