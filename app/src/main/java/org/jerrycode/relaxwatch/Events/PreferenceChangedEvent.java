package org.jerrycode.relaxwatch.Events;

/**
 * Created by jerry on 12/19/15.
 */

// Just a normal class
public class PreferenceChangedEvent  {
    public String getSortPreference() {
        return SortPreference;
    }

    public void setSortPreference(String sortPreference) {
        SortPreference = sortPreference;
    }

    private String SortPreference;
    public PreferenceChangedEvent(){

    }
}
