package library;

import it.unifi.cerm.playmorphia.PlayMorphia;
import org.bson.types.ObjectId;
import org.mongodb.morphia.query.Query;

import javax.inject.Inject;
import java.util.List;

abstract public class Repository<T extends Model> {

    @Inject
    protected PlayMorphia morphia;

    private Class<Model<T>> modelClass;

    public Repository(Class<Model<T>> modelClass) {
        this.modelClass = modelClass;
    }

    public List<Model<T>> findAll() {
        return morphia.datastore().createQuery(modelClass).asList();
    }

    public Model<T> get(ObjectId id) {
        return morphia.datastore().createQuery(modelClass).field("_id").equal(id).get();
    }

    public Model<T> getByKey(String key, Object value) {
        return morphia.datastore().createQuery(modelClass).field(key).equal(value).get();
    }

    public List<Model<T>> findByKey(String key, Object value) {
        return morphia.datastore().createQuery(modelClass).field(key).equal(value).asList();
    }

    public Model<T> save(T model) {
        Model<T> modelWithId = model.withId();
        morphia.datastore().save(modelWithId);
        return modelWithId;
    }

    abstract protected Query<T> getUniqueQuery(T model);

}
