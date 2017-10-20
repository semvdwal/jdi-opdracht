package models;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import play.data.validation.Constraints;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Entity(value = "users")
public class User {

    @Id
    protected ObjectId _id;

    @Constraints.Required
    protected String username;
    protected String email;
    protected String phone;

    protected String firstName;
    protected String lastName;

    public ObjectId get_id() {
        return _id;
    }

    public void set_id(ObjectId _id) {
        this._id = _id;
    }

    public User withId() {
        if(get_id() == null) _id = new ObjectId();
        return this;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFullName() {
        if(getFirstName() == null || getFirstName().equals("")) {
            return getLastName();
        }
        if(getLastName() == null || getLastName().equals("")) {
            return getFirstName();
        }
        return getFirstName() + " " + getLastName();
    }

    public Map<String, String> validate() {
        Map<String, String> errors = new HashMap<>();
        if(username == null || username.equals("")) {
            errors.put("username", "Gebruikersnaam mag niet leeg zijn");
        }
        if(username.toLowerCase().equals("pepijn") || username.toLowerCase().equals("jarno") || username.toLowerCase().equals("sem")) {
            errors.put("username", "Gebruik wel een originele username!");
        }

        if(email == null || email.equals("")) {
            errors.put("email", "Vul aub een email adres in");
        }
        Pattern emailPattern = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
        Matcher emailMatcher = emailPattern.matcher(email);
        if(!emailMatcher.find()) errors.put("email", "Email moet een email adres zijn in de vorm van persoon@server.com");

        if(phone != null && !phone.equals("")) {
            Pattern phonePattern = Pattern.compile("^[0-9]+-?[0-9]+$", Pattern.CASE_INSENSITIVE);
            Matcher phoneMatcher = phonePattern.matcher(phone);
            if(!phoneMatcher.find()) errors.put("phone", "Telefoon nummer klopt niet, we verwachten cijfers met hoogstens 1 koppelstreepje");
        }

        return errors;
    }

}
