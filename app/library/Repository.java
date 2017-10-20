package library;

import it.unifi.cerm.playmorphia.PlayMorphia;
import org.bson.types.ObjectId;
import org.mongodb.morphia.query.Query;

import javax.inject.Inject;
import java.util.List;

abstract public class Repository<T> {

    @Inject
    protected PlayMorphia morphia;

    private Class<T> modelClass;

    public Repository(Class<T> modelClass) {
        this.modelClass = modelClass;
    }

    public List<T> findAll() {
        return morphia.datastore().createQuery(modelClass).asList();
    }

    public T get(ObjectId id) {
        return morphia.datastore().createQuery(modelClass).field("_id").equal(id).get();
    }

    public T getByKey(String key, Object value) {
        return morphia.datastore().createQuery(modelClass).field(key).equal(value).get();
    }

    public List<T> findByKey(String key, Object value) {
        return morphia.datastore().createQuery(modelClass).field(key).equal(value).asList();
    }

    abstract protected Query<T> getUniqueQuery(T model);

}
