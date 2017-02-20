package JSONmodel;

import com.google.gson.annotations.SerializedName;
import java.util.List;

/**
 * Created by ASHL7 on 2/18/2017.
 */
public class Thumbnail {

    @SerializedName("groups")
    private List<PhotoGroup> groups = null;

    public List<PhotoGroup> getGroups() {
        return groups;
    }

    public void setGroups(List<PhotoGroup> groups) {
        this.groups = groups;
    }
}
