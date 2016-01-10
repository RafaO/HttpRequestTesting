package blog.rortega.httprequesttesting;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private TextView mTitleTextView;
    private TextView mYearTextView;
    private TextView mDirectorTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RequestQueue queue = Volley.newRequestQueue(this);

        String url = "http://www.omdbapi.com/?t=frozen&y=2015&plot=short&r=json";

        mTitleTextView = (TextView) findViewById(R.id.text_view_title);
        mYearTextView = (TextView) findViewById(R.id.text_view_year);
        mDirectorTextView = (TextView) findViewById(R.id.text_view_director);

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // if the parsing was too complex it should be done in a separate thread
                        try {
                            JSONObject jsonMovie = new JSONObject(response);
                            mTitleTextView.setText(jsonMovie.getString("Title"));
                            mYearTextView.setText(jsonMovie.getString("Year"));
                            mDirectorTextView.setText(jsonMovie.getString("Director"));

                        } catch (JSONException e) {
                            showErrorView();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                showErrorView();
            }
        });

        queue.add(stringRequest);
    }

    private void showErrorView() {
        mTitleTextView.setText(R.string.error);
        mYearTextView.setText("--");
        mDirectorTextView.setText("--");
    }
}
