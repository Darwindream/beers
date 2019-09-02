package ferran.com.brewdog.resultsscreen;

import dagger.Module;
import dagger.Provides;
import ferran.com.brewdog.util.CustomScope;


@Module
public class ResultsScreenModule {
    private final ResultsScreenContract.View mView;

    public ResultsScreenModule(ResultsScreenContract.View mView) {this.mView = mView; }

    @Provides
    @CustomScope
    ResultsScreenContract.View providesMainScreenContractView() {
        return mView;
    }
}
