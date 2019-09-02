package ferran.com.brewdog;

import android.app.Application;

import ferran.com.brewdog.di.component.DaggerNetComponent;
import ferran.com.brewdog.di.component.NetComponent;
import ferran.com.brewdog.di.module.AppModule;
import ferran.com.brewdog.di.module.NetModule;

public class App extends Application {
    private NetComponent mNetComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        mNetComponent = DaggerNetComponent.builder()
                .appModule(new AppModule(this))
                .netModule(new NetModule("https://api.punkapi.com"))
                .build();
    }

    public NetComponent getNetComponent() {
        return mNetComponent;
    }
}
