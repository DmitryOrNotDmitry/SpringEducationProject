package ny.dmitrium.app;

import ny.dmitrium.app.config.AppConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class ApplicationRunner {

	private static AppConfig appConfig;

	@Autowired
	public void setAppConfig(AppConfig appConfig) {
		this.appConfig = appConfig;
	}

	public static void main(String[] args) {
		SpringApplication.run(ApplicationRunner.class, args);

		System.out.println("App url: '" + appConfig.getUrl() + "'");
	}

}
