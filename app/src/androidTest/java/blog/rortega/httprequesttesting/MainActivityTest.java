package blog.rortega.httprequesttesting;

import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

import blog.rortega.httprequesttesting.api.ApiService;
import blog.rortega.httprequesttesting.datamodel.Movie;
import blog.rortega.httprequesttesting.di.DaggerTestComponent;
import blog.rortega.httprequesttesting.di.TestApiModule;
import blog.rortega.httprequesttesting.di.TestComponent;
import blog.rortega.httprequesttesting.view.MainActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static blog.rortega.httprequesttesting.util.TestUtil.createCall;
import static org.mockito.Mockito.when;

/**
 * Created by ortega on 11.01.16.
 */
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {
    TestComponent mComponent;

    @Inject
    ApiService mApi;

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(MainActivity.class, true, false);

    @Before
    public void setUp() {
        App app = (App) InstrumentationRegistry.getTargetContext().getApplicationContext();
        mComponent = DaggerTestComponent.builder()
                .testApiModule(new TestApiModule())
                .build();
        app.setAppComponent(mComponent);
        mComponent.inject(this);
    }

    @Test
    public void simpleTest() {
        Movie movie = new Movie("Willy fog", 2015, "Spielberg");
        when(mApi.movie()).thenReturn(createCall(movie));
        mActivityRule.launchActivity(new Intent());
        onView(withText("Willy fog")).check(matches(isDisplayed()));
    }

    @Test
    public void movieNotFoundTest() {
        when(mApi.movie()).thenReturn(createCall(404, new Movie()));
        mActivityRule.launchActivity(new Intent());
        onView(withText(R.string.error)).check(matches(isDisplayed()));
    }
}
