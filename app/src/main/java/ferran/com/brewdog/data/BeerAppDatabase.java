package ferran.com.brewdog.data;


import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;

import javax.inject.Singleton;

import ferran.com.brewdog.model.Beer;
import ferran.com.brewdog.model.Food;
import ferran.com.brewdog.model.BeerConverter;

@Singleton
@Database(entities = {Beer.class, Food.class}, version = 1, exportSchema = false)
@TypeConverters(BeerConverter.class)
public abstract class BeerAppDatabase extends RoomDatabase {

    public abstract BeerDAO beerDAO();
}