package guice.app;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.junit.BeforeClass;
import org.junit.Test;

public class UserAppTest {

    private static Injector injector;

    @BeforeClass
    public static void init() {
        injector = Guice.createInjector(new AppModule());
    }
    
    @Test
    public void test(){
        UserService userService = injector.getInstance(UserService.class);
        userService.process();
        
        UserApp userApp = injector.getInstance(UserApp.class);
        userApp.process();
    }
    
    
    
}
