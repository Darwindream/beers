package ferran.com.brewdog.model;
import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import javax.annotation.Nullable;

@Entity(tableName = "beer")
public class Beer {

    @PrimaryKey(autoGenerate = true)
    private int dbId;

    @SerializedName("id")
    @ColumnInfo(name = "id")
    private String id;

    @SerializedName("name")
    @ColumnInfo(name = "name")
    private String name;

    @SerializedName("tagline")
    @ColumnInfo(name = "tagline")
    private String tagline;

    @SerializedName("description")
    @ColumnInfo(name = "description")
    private String description;

    @SerializedName("image_url")
    @ColumnInfo(name = "image_url")
    private String image_url;

    @SerializedName("abv")
    @ColumnInfo(name = "abv")
    private float abv;


    public Beer (int dbId,@Nullable String id, @Nullable String name,
                 @Nullable String tagline, @Nullable String description,
                 @Nullable String image_url, @Nullable float abv){
        this.dbId = dbId;
        this.id = id;
        this.name = name;
        this.tagline = tagline;
        this.description = description;
        this.image_url = image_url;
        this.abv = abv;
    }

    public int getDbId() {
        return dbId;
    }

    public void setDbId(int dbId) {
        this.dbId = dbId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTagline() {
        return tagline;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public float getAbv() {
        return abv;
    }

    public void setAbv(float abv) {
        this.abv = abv;
    }

}