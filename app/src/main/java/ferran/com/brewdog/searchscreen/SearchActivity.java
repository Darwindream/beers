package ferran.com.brewdog.searchscreen;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ferran.com.brewdog.App;
import ferran.com.brewdog.R;
import ferran.com.brewdog.resultsscreen.ResultsActivity;
import ferran.com.brewdog.util.EspressoIdlingResource;

public class SearchActivity extends AppCompatActivity implements SearchScreenContract.View {

    @BindView(R.id.searchButton)
    Button seachButton;
    @BindView(R.id.inputText)
    TextInputEditText inputEditText;

    @Inject
    SearchScreenPresenter sPresenter;

    String food;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);

        DaggerSearchScreenComponent.builder()
                .netComponent(((App) getApplicationContext()).getNetComponent())
                .searchScreenModule(new SearchScreenModule(this))
                .build().inject(this);


        //Increment the counter before making a network request
        EspressoIdlingResource.increment();
    }


    @OnClick(R.id.searchButton)
    public void onSearchPressed(){
        food = inputEditText.getText().toString();
        if(food == null || food.isEmpty()){
            Toast.makeText(getApplicationContext(), getString(R.string.errorEmptyName), Toast.LENGTH_SHORT).show();
        }
        else {
            Log.e("SEARCH ACTIVITY", "ON SEARCH PRESSED = " + food);
            sPresenter.loadBeers(food);
            inputEditText.getText().clear();
        }
    }

    @Override
    public void navigateResultsScreen() {
        //Move to results activity
        Intent intent = new Intent(this, ResultsActivity.class);
        intent.putExtra("food",food);
        startActivity(intent);
    }

    @Override
    public void showError(String message) {
        //Show error message Toast
        if(message.contains("Index"))
            message = getString(R.string.errorNotFound);
        if(message.contains("Unable to resolve host"))
            message =  getString(R.string.errorNotConnection);
        Toast.makeText(getApplicationContext(), "Error" + message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showComplete() {
        //Show completed message Toast
        Toast.makeText(getApplicationContext(), "Complete", Toast.LENGTH_SHORT).show();
    }
}
