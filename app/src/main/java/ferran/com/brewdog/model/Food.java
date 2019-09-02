package ferran.com.brewdog.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

@Entity(tableName = "food")
public class Food {

    @PrimaryKey
    @NonNull
    @SerializedName("name")
    @ColumnInfo(name = "name")
    private String name;

    @SerializedName("beers")
    @ColumnInfo(name = "beers")
    private List<Beer> beers = new ArrayList<>();

    public Food(@Nullable String name, @Nullable List<Beer> beers){
        this.name = name;
        this.beers = beers;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Beer> getBeers() {
        if(beers == null){
            beers = new ArrayList<>();
            return  beers;
        }
        return  beers;
    }

    public void setBeers(List<Beer> beers) {
        this.beers = beers;
    }
}