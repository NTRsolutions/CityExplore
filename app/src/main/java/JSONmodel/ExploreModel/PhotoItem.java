package JSONmodel.ExploreModel;

import android.content.res.Resources;

import com.google.gson.annotations.SerializedName;
import java.util.List;
/**
 * Created by ASHL7 on 2/18/2017.
 */
public class PhotoItem {

    @SerializedName("id")
    private String id;

    @SerializedName("prefix")
    private String prefix;

    @SerializedName("suffix")
    private String suffix;

    // how to request images: https://developer.foursquare.com/docs/responses/photo.html
    public String getURLforThumbnail() {
        return prefix + "width100" + suffix;
    }

    public String getURLforGrid() {
        return prefix + "width300" + suffix;
    }

    public String getURLforOriginal() {
        //int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
        return prefix + "original" + suffix;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }
}
