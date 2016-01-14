package blog.rortega.httprequesttesting.api;

import blog.rortega.httprequesttesting.datamodel.Movie;
import retrofit.Call;
import retrofit.http.GET;

/**
 * Created by ortega on 13.01.16.
 */
public interface ApiService {

    @GET("http://www.omdbapi.com/?t=frozen&y=2015&plot=short&r=json")
    Call<Movie> movie();

}
