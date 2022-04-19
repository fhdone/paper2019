package guice.app;

import com.google.inject.AbstractModule;

public class AppModule extends AbstractModule {
    
    @Override
    protected void configure() {
        bind(UserService.class).to(UserServiceImpl.class);
        bind(UserApp.class).to(UserAppImpl.class);
    }
}
