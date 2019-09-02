package ferran.com.brewdog.resultsscreen;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import ferran.com.brewdog.App;
import ferran.com.brewdog.R;
import ferran.com.brewdog.model.Beer;


public class ResultsActivity extends AppCompatActivity implements ResultsScreenContract.View {

    @BindView(R.id.list)
    RecyclerView recyclerView;

    LinearLayoutManager llm;

    ArrayList<Beer> beerArrayList;
    BeerListAdapter listAdapter;
    String food;

    @Inject
    ResultsScreenPresenter mainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        food = getIntent().getStringExtra("food");
        ButterKnife.bind(this);

        llm = new LinearLayoutManager(this);
        beerArrayList = new ArrayList<>();

        DaggerResultsScreenComponent.builder()
                .netComponent(((App) getApplicationContext()).getNetComponent())
                .resultsScreenModule(new ResultsScreenModule(this))
                .build().inject(this);

        mainPresenter.loadBeers(food,"Soft");
    }

    @Override
    public void showBeers(final List<Beer> beers){
        beerArrayList.addAll(beers);
        listAdapter = new BeerListAdapter(this, beerArrayList, mainPresenter);
        recyclerView.setLayoutManager(llm);
        recyclerView.setAdapter(listAdapter);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_result, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.sortStrong:
                beerArrayList.clear();
                mainPresenter.loadBeers(food,"Strong");
                return true;
            case R.id.sortSoft:
                beerArrayList.clear();
                mainPresenter.loadBeers(food,"Soft");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}