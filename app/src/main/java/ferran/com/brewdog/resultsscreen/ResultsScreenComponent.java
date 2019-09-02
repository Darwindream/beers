package ferran.com.brewdog.resultsscreen;

import ferran.com.brewdog.di.component.NetComponent;
import ferran.com.brewdog.util.CustomScope;

import dagger.Component;


@CustomScope
@Component(dependencies = NetComponent.class, modules = ResultsScreenModule.class)
public interface ResultsScreenComponent {
    void inject(ResultsActivity activity);
}
