package ferran.com.brewdog.di.module;

import android.app.Application;
import android.arch.persistence.room.Room;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ferran.com.brewdog.data.BeerAppDatabase;

@Module
public class AppModule {
    Application mApplication;

    public AppModule(Application mApplication) {
        this.mApplication = mApplication;
    }

    @Provides
    @Singleton
    Application provideApplication() {
        return mApplication;
    }

    @Singleton
    @Provides
    public BeerAppDatabase providesBeerAppDatabase() {
        return Room.databaseBuilder(mApplication, BeerAppDatabase.class, "beers_database")
                .allowMainThreadQueries()
                .build();
    }
}
