package blog.rortega.httprequesttesting;

import android.app.Application;
import android.support.annotation.VisibleForTesting;

import blog.rortega.httprequesttesting.di.AppComponent;
import blog.rortega.httprequesttesting.di.AppModule;
import blog.rortega.httprequesttesting.di.DaggerAppComponent;

/**
 * Created by ortega on 14.01.16.
 */
public class App extends Application {
    private AppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        // we create the component which is the bridge between modules and classes that require the
        // structures these modules provide
        mAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule())
                .build();
    }

    public AppComponent getAppComponent() {
        return mAppComponent;
    }

    @VisibleForTesting
    public void setAppComponent(AppComponent appComponent) {
        mAppComponent = appComponent;
    }
}
