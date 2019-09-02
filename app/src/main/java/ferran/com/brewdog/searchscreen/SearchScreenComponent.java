package ferran.com.brewdog.searchscreen;

import ferran.com.brewdog.di.component.NetComponent;
import ferran.com.brewdog.util.CustomScope;

import dagger.Component;

@CustomScope
@Component(dependencies = NetComponent.class, modules = SearchScreenModule.class)
public interface SearchScreenComponent {
    void inject(SearchActivity activity);
}