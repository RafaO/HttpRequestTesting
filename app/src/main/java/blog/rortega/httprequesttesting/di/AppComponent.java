package blog.rortega.httprequesttesting.di;

import javax.inject.Singleton;

import blog.rortega.httprequesttesting.view.MainActivity;
import dagger.Component;

/**
 * Created by ortega on 13.01.16.
 *
 * The components indicates where the dependencies will be injected.
 */
@Singleton
@Component(modules = AppModule.class) // here we declare the modules that will be creating our structures
public interface AppComponent {

    // we will be injecting the dependencies into our activity
    void inject(MainActivity activity);
}
