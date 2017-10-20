package repositories;

import library.Repository;
import models.User;
import org.mongodb.morphia.query.Query;

public class UserRepository extends Repository<User> {

    public UserRepository() {
        super(User.class);
    }

    public User findByUserName(String name) {
        return getByKey("username", name);
    }

    @Override
    protected Query<User> getUniqueQuery(User model) {
        return morphia.datastore().createQuery(User.class).field("username").equal(model.getUsername());
    }

}
