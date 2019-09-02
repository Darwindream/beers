package ferran.com.brewdog.resultsscreen;

import java.util.List;

import ferran.com.brewdog.model.Beer;

public interface ResultsScreenContract {
    interface View {

        void showBeers(List<Beer> beers);
    }

    interface Presenter {

        void loadBeers(String food,String sort);
    }
}
