package ferran.com.brewdog.model;

import android.arch.persistence.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;


public class BeerConverter {
    @TypeConverter // note this annotation
    public String fromBeersList(List<Beer> beers) {
        if (beers == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<Beer>>() {
        }.getType();
        String json = gson.toJson(beers, type);
        return json;
    }

    @TypeConverter // note this annotation
    public List<Beer> toBeerList(String BeerString) {
        if (BeerString == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<Beer>>() {
        }.getType();
        List<Beer> beerList = gson.fromJson(BeerString, type);
        return beerList;
    }
}
