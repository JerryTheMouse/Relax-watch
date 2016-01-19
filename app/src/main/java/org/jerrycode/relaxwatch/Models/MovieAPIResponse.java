package org.jerrycode.relaxwatch.Models;

import java.util.ArrayList;

/**
 * Created by jerry on 12/19/15.
 */
public class MovieAPIResponse<E> {

    public ArrayList<E> getResults() {
        return results;
    }

    ArrayList<E> results;
}
