package ny.dmitrium.app;

import ny.dmitrium.app.config.UsersConfiguration;
import ny.dmitrium.app.security.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("test")
public class TestRunner {

    @Autowired
    private UserService userService;

    @Autowired
    private UsersConfiguration userConfig;

    @Autowired
    public void run() {
        System.out.println("test profile runs");

        userService.addUser("default_user", "1", "ROLE_USER");
        userService.addUser(userConfig.getUserLogin(), userConfig.getUserPassword(), "ROLE_USER");
        userService.addUser(userConfig.getAdminLogin(), userConfig.getAdminPassword(), "ROLE_ADMIN");
    }

}
