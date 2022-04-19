package guice.app;

import javax.inject.Inject;

public class UserAppImpl implements UserApp{

    private UserService userService;

    @Inject
    public UserAppImpl(UserService userService) {
        this.userService = userService;
    }
    
    @Override
    public void process(){
        userService.process();
    }
    
}
