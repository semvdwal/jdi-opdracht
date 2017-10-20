package library;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Id;

public class Model<T> {

    @Id
    private ObjectId _id;

    public ObjectId getId() {
        return _id;
    }

    public void setId(ObjectId id) {
        this._id = id;
    }

    public Model<T> withId() {
        if(getId() == null) _id = new ObjectId();
        return this;
    }

}
