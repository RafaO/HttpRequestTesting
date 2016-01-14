package blog.rortega.httprequesttesting.di;

import javax.inject.Singleton;

import blog.rortega.httprequesttesting.MainActivityTest;
import blog.rortega.httprequesttesting.api.ApiService;
import dagger.Component;

/**
 * Created by ortega on 14.01.16.
 */
@Singleton
@Component(modules = TestApiModule.class)
public interface TestComponent extends AppComponent {
    ApiService api();

    void inject(MainActivityTest activity);
}
