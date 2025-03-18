package ny.dmitrium.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("production")
public class ProdRunner {

    @Autowired
    public void run() {
        System.out.println("prod profile runs");
    }

}
