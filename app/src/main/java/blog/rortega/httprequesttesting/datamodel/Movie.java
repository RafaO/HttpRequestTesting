package blog.rortega.httprequesttesting.datamodel;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by rafa on 10/01/2016.
 *
 * Although we can get more info from the API, for our example we will be ok with these three fields
 */
public class Movie {
    // we need to add this annotation for the jackson converter to work
    @JsonProperty("Title")
    private String mTitle;

    @JsonProperty("Year")
    private int mYear;

    @JsonProperty("Director")
    private String mDirector;

    public Movie() {
        // needed empty constructor for the conversion from JSON to work
    }

    public Movie(String title, int year, String director) {
        mTitle = title;
        mYear = year;
        mDirector = director;
    }

    public String getTitle() {
        return mTitle;
    }

    public int getYear() {
        return mYear;
    }

    public String getDirector() {
        return mDirector;
    }
}
