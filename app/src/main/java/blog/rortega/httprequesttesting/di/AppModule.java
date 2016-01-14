package blog.rortega.httprequesttesting.di;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.inject.Singleton;

import blog.rortega.httprequesttesting.api.ApiService;
import dagger.Module;
import dagger.Provides;
import retrofit.JacksonConverterFactory;
import retrofit.Retrofit;

/**
 * Created by ortega on 14.01.16.
 *
 * The module is who creates the data that will be required by others
 * In this module we instantiate the api
 */

@Module
public class AppModule {
    @Provides
    @Singleton
    public ApiService api() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return new Retrofit.Builder()
                .baseUrl("http://www.omdbapi.com")
                .addConverterFactory(JacksonConverterFactory.create(mapper)).build()
                .create(ApiService.class);
    }
}
