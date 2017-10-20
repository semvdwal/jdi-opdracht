package repositories;

import it.unifi.cerm.playmorphia.PlayMorphia;
import models.User;
import org.bson.types.ObjectId;
import org.mongodb.morphia.query.Query;

import javax.inject.Inject;
import java.util.List;

public class UserRepository {

    @Inject
    PlayMorphia morphia;

    public User findByUserName(String name) {
        return getByKey("username", name);
    }

    private Query<User> getUniqueQuery(User model) {
        return morphia.datastore().createQuery(User.class).field("username").equal(model.getUsername());
    }

    public List<User> findAll() {
        return morphia.datastore().createQuery(User.class).asList();
    }

    public User get(ObjectId id) {
        return morphia.datastore().createQuery(User.class).field("_id").equal(id).get();
    }

    public User getByKey(String key, Object value) {
        return morphia.datastore().createQuery(User.class).field(key).equal(value).get();
    }

    public List<User> findByKey(String key, Object value) {
        return morphia.datastore().createQuery(User.class).field(key).equal(value).asList();
    }

    public User save(User model) {
        User modelWithId = model.withId();
        morphia.datastore().save(modelWithId);
        return modelWithId;
    }

}
