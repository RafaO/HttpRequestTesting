package blog.rortega.httprequesttesting.datamodel;

/**
 * Created by rafa on 10/01/2016.
 *
 * Although we can get more info from the API, for our example we will be ok with these three fields
 */
public class Movie {
    private String mTitle;
    private int mYear;
    private String mDirector;

    public Movie(String title, int year, String director) {
        mTitle = title;
        mYear = year;
        mDirector = director;
    }
}
