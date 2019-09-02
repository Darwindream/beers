package ferran.com.brewdog.searchscreen;

import dagger.Module;
import dagger.Provides;
import ferran.com.brewdog.util.CustomScope;

@Module
public class SearchScreenModule {
    private final SearchScreenContract.View sView;

    public SearchScreenModule(SearchScreenContract.View sView) {this.sView = sView; }

    @Provides
    @CustomScope
    SearchScreenContract.View providesSearchScreenContractView() {
        return sView;
    }
}
