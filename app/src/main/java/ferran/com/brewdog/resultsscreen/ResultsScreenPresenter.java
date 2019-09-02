package ferran.com.brewdog.resultsscreen;

import android.util.Log;

import java.util.List;

import javax.inject.Inject;

import ferran.com.brewdog.data.BeerAppDatabase;
import ferran.com.brewdog.model.Beer;
import ferran.com.brewdog.model.Food;
import retrofit2.Retrofit;
/**
 *
 * Created by Ferran Peña on 12-Jan-19.
 */
public class ResultsScreenPresenter implements ResultsScreenContract.Presenter {

    public Retrofit retrofit;
    BeerAppDatabase beerAppDatabase;
    ResultsScreenContract.View mView;

    @Inject
    public ResultsScreenPresenter(Retrofit retrofit, BeerAppDatabase beerAppDatabase, ResultsScreenContract.View mView) {
        this.retrofit = retrofit;
        this.mView = mView;
        this.beerAppDatabase = beerAppDatabase;
    }

    @Override
    public void loadBeers(String food,String sort){
        //Retrieve beers from food
        Food f = beerAppDatabase.beerDAO().selectBeerFromFood(food);
        Log.e("FOOD",f.getName());

        //Clean Beer Table
        beerAppDatabase.beerDAO().cleanBeerTable();

        //Fill Beer Table
        List<Beer> beers = f.getBeers();
        for (int i = 0; i < beers.size(); i++) {
            Beer b = beers.get(i);
            beerAppDatabase.beerDAO().addBeer(b);
            Log.e("Beer" + i, b.getName());
        }

        //Sort Beer Table
        switch (sort){
            case "Soft":
                mView.showBeers( beerAppDatabase.beerDAO().selectBeersOrderSoft());
                break;
            case "Strong":
                mView.showBeers( beerAppDatabase.beerDAO().selectBeersOrderStrong());
                break;
        }

    }

}