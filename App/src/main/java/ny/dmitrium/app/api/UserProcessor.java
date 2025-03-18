package ny.dmitrium.app.api;

import ny.dmitrium.app.config.AppConfig;
import ny.dmitrium.app.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;

@Component
public class UserProcessor implements RepresentationModelProcessor<EntityModel<User>> {

    private final String urlPrefix;

    @Autowired
    public UserProcessor(AppConfig appConfig) {
        urlPrefix = appConfig.getUrl();
    }

    @Override
    public EntityModel<User> process(EntityModel<User> model) {
        User user = model.getContent();

        if (user != null) {
            model.add(Link.of(urlPrefix + "api/users/" + user.getId()).withRel("update").withType("PUT"));
            model.add(Link.of(urlPrefix + "api/users/" + user.getId()).withRel("delete").withType("DELETE"));
        }

        model.add(Link.of(urlPrefix + "api/users").withRel("create").withType("POST"));

        model.add(Link.of(urlPrefix + "api/users", "users"));
        return model;
    }
}

