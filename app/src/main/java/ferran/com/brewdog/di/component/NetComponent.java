package ferran.com.brewdog.di.component;

import javax.inject.Singleton;
import dagger.Component;
import ferran.com.brewdog.data.BeerAppDatabase;
import ferran.com.brewdog.di.module.AppModule;
import ferran.com.brewdog.di.module.NetModule;
import retrofit2.Retrofit;

@Singleton
@Component(modules = {AppModule.class, NetModule.class})
public interface NetComponent {
    Retrofit retrofit();

    BeerAppDatabase beerAppDatabase();
}
