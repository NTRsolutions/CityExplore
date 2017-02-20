package JSONmodel.ExploreModel;

import com.google.gson.annotations.SerializedName;

import java.util.List;


/**
 * Created by ASHL7 on 2/15/2017.
 * Class representing a venue
 */
public class Venue {

    @SerializedName("id")
    private String id;

    @SerializedName("name")
    private String name;

    @SerializedName("location")
    private Location location;

    @SerializedName("categories")
    private List<Category> categories = null;

    @SerializedName("url")
    private String url;

    @SerializedName("photos")
    private Thumbnail photos = null;

    private boolean bookmarked;


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

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Thumbnail getPhotos() {
        return photos;
    }

    public void setPhotos(Thumbnail photos) {
        this.photos = photos;
    }

    public boolean isBookmarked() {
        return bookmarked;
    }

    public void setBookmarked(boolean bookmarked) {
        this.bookmarked = bookmarked;
    }
}
