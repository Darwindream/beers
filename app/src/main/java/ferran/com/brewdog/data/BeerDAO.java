package ferran.com.brewdog.data;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;


import ferran.com.brewdog.model.Beer;
import ferran.com.brewdog.model.Food;

import java.util.List;

@Dao
public interface BeerDAO {

    @Query("SELECT name From food Where name = :name")
    String checkFoodinDB(String name);

    @Query("SELECT name,beers FROM food WHERE name = :name")
    Food selectBeerFromFood(String name);

    @Query("SELECT * FROM beer ORDER BY abv ASC")
    List<Beer> selectBeersOrderSoft();

    @Query("SELECT * FROM beer ORDER BY abv DESC")
    List<Beer> selectBeersOrderStrong();


    /**
     * Insert a beer in the database. If the beer already exists, ignore it.
     *
     * @param beer the movie to be inserted.
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void addBeer(Beer beer);

    /**
     * Insert a beer in the database. If the beer already exists, ignore it.
     *
     * @param food the movie to be inserted.
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void addFood(Food food);

    /**
     * Delete all beers.
     *
     * Delete table Beers
     */
    @Query("DELETE FROM beer")
    void cleanBeerTable();
}
