package blog.rortega.httprequesttesting.di;

import javax.inject.Singleton;

import blog.rortega.httprequesttesting.api.ApiService;
import dagger.Module;
import dagger.Provides;

import static org.mockito.Mockito.mock;

/**
 * Created by ortega on 14.01.16.
 */
@Singleton
@Module
public class TestApiModule {
    @Provides
    @Singleton
    public ApiService api() {
        return mock(ApiService.class);
    }
}
