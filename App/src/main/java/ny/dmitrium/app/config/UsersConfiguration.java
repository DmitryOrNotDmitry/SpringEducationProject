package ny.dmitrium.app.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "app.users")
public class UsersConfiguration {

    private String userLogin;
    private String userPassword;
    private String adminLogin;
    private String adminPassword;

}
