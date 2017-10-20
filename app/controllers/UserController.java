package controllers;

import library.UserMailer;
import models.User;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Result;
import repositories.UserRepository;
import views.html.userform;
import views.html.usersubmit;

import javax.inject.Inject;

public class UserController extends Controller {

    @Inject
    private UserRepository userRepository;

    @Inject
    private FormFactory formFactory;

    public Result viewUserForm() {
        Form<User> userForm = formFactory.form(User.class).fill(new User().withId());
        return ok(userform.render(userForm));
    }

    public Result submitUser() {

        Form<User> userForm = formFactory.form(User.class).bindFromRequest(request());

        if(userForm.hasErrors()) {
            return ok(userform.render(userForm));
        }
        User user = userForm.get();
        userRepository.save(user);
        UserMailer.sendUserCreateMail(user);

        return ok(usersubmit.render(user));
    }

}
