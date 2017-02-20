package JSONmodel.ExploreModel;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import JSONmodel.ExploreModel.Group;

/**
 * Created by ASHL7 on 2/16/2017.
 */

public class Response {

    @SerializedName("headerLocation")
    private String headerLocation;

    @SerializedName("groups")
    private List<Group> groups = null;

    public String getHeaderLocation() {
        return headerLocation;
    }

    public void setHeaderLocation(String headerLocation) {
        this.headerLocation = headerLocation;
    }

    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }
}
