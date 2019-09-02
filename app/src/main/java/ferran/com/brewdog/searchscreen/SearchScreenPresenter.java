package ferran.com.brewdog.searchscreen;

import android.util.Log;

import java.util.List;

import javax.inject.Inject;

import ferran.com.brewdog.data.BeerAppDatabase;
import ferran.com.brewdog.model.Beer;
import ferran.com.brewdog.model.Food;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class SearchScreenPresenter implements SearchScreenContract.Presenter {
    public Retrofit retrofit;
    BeerAppDatabase beerAppDatabase;
    SearchScreenContract.View sView;
    String searchFood;


    @Inject
    public SearchScreenPresenter(Retrofit retrofit, BeerAppDatabase beerAppDatabase, SearchScreenContract.View sView) {
        this.retrofit = retrofit;
        this.sView = sView;
        this.beerAppDatabase = beerAppDatabase;
    }

    @Override
    public void loadBeers(String food){
        if(checkBeers(food)){
            sView.navigateResultsScreen();
        }
        else{
            searchFood = food;
                retrofit.create(SearchScreenPresenter.BeerService.class)
                        .getBeerList(food,"80").subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .unsubscribeOn(Schedulers.io())
                        .subscribe(new Observer<List<Beer>>() {
                            @Override
                            public void onCompleted() {
                                sView.showComplete();
                            }

                            @Override
                            public void onError(Throwable e) {
                                sView.showError(e.getMessage());
                            }

                            @Override
                            public void onNext(List<Beer> beers) {
                                Log.e("Beers founded", String.valueOf(beers.size()));
                                Log.e("Beers founded", String.valueOf(beers.get(1).getName()));
                                createBeers(beers);
                                sView.navigateResultsScreen();
                            }
                        });
        }
    }


    public boolean checkBeers(String food){
        String aux = beerAppDatabase.beerDAO().checkFoodinDB(food);
        if(aux != null)
            return true;
        else
            return  false;
    }

    public void createBeers(List<Beer> beers) {
        Food food = new Food(searchFood,beers);
        beerAppDatabase.beerDAO().addFood(food);
    }

    public interface BeerService{
        @GET("/v2/beers?")
        Observable<List<Beer>> getBeerList(@Query("food") String search,
                                           @Query("per_page") String maxNumResponse);
    }
}