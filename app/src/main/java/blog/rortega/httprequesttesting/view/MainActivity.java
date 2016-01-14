package blog.rortega.httprequesttesting.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import javax.inject.Inject;

import blog.rortega.httprequesttesting.App;
import blog.rortega.httprequesttesting.R;
import blog.rortega.httprequesttesting.api.ApiService;
import blog.rortega.httprequesttesting.datamodel.Movie;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class MainActivity extends AppCompatActivity {
    private TextView mTitleTextView;
    private TextView mYearTextView;
    private TextView mDirectorTextView;

    @Inject
    ApiService mApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // we inject the dependencies, we must do it this way because we don't have a constructor
        // in an activity.
        ((App) getApplicationContext()).getAppComponent().inject(this);

        setContentView(R.layout.activity_main);

        mTitleTextView = (TextView) findViewById(R.id.text_view_title);
        mYearTextView = (TextView) findViewById(R.id.text_view_year);
        mDirectorTextView = (TextView) findViewById(R.id.text_view_director);

        // this is maybe not the best way of making requests in an scalable and robust app
        // but would do the thing for our purpose of explaining di and testing

        Call<Movie> apiCall = mApi.movie();
        apiCall.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Response<Movie> response, Retrofit retrofit) {
                Movie movie = response.body();
                if (response.code() == 200 && movie != null) {
                    mTitleTextView.setText(movie.getTitle());
                    mYearTextView.setText(String.valueOf(movie.getYear()));
                    mDirectorTextView.setText(movie.getDirector());
                } else
                    showErrorView();
            }

            @Override
            public void onFailure(Throwable t) {
                // Log error here since request failed
                showErrorView();
            }
        });
    }

    private void showErrorView() {
        mTitleTextView.setText(R.string.error);
        mYearTextView.setText("--");
        mDirectorTextView.setText("--");
    }
}
