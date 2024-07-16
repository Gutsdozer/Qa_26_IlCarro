package tests;

import models.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Random;

public class RegistrationTest extends TestBase{

    @BeforeMethod
    public void preCondition(){
        if(app.getHelperUser().isLogged()){
            app.getHelperUser().logout();
        }
    }

    @Test
    public void registrationSuccess(){
        int i = new Random().nextInt(1000) + 1000;
        System.out.println(i);
        System.out.println(System.currentTimeMillis());
        int z = (int) ((System.currentTimeMillis()/1000)%3600);
        System.out.println(z);

        User user = new User()
                .setName("Lisa")
                .setLastName("Snow")
                .withEmail("snow"+z+"@gmail.com")
                .withPassword("Snow1234");

        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicyXY();
        app.getHelperUser().submit();
    }

//**********Negative
    public void registrationEmptyName(){
        User user = new User()
                .setName("")
                .setLastName("Snow")
                .withEmail("snow@gmail.com")
                .withPassword("Snow1234");
        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicyXY();
        app.getHelperUser().submit();
        Assert.assertEquals(app.getHelperUser().getErrorText(), "Name is required");
    }

    public void registrationEmptyLastName(){
        User user = new User()
                .setName("Lisa")
                .setLastName("")
                .withEmail("snow@gmail.com")
                .withPassword("Snow1234");
        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicyXY();
        app.getHelperUser().submit();
        Assert.assertEquals(app.getHelperUser().getErrorText(), "Last ame is required");
    }

    public void registrationWrongEmail(){
        User user = new User()
                .setName("Lisa")
                .setLastName("Snow")
                .withEmail("snowgmail.com")
                .withPassword("Snow1234");
        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicyXY();
        app.getHelperUser().submit();
        Assert.assertEquals(app.getHelperUser().getErrorText(), "Wrong email format");
    }

    public void registrationWrongPassword(){
        User user = new User()
                .setName("Lisa")
                .setLastName("Snow")
                .withEmail("snow@gmail.com")
                .withPassword("snow1234");
        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicyXY();
        app.getHelperUser().submit();
        Assert.assertEquals(app.getHelperUser().getErrorText(), "Password must contain minimum 8 symbols\n" +
                "Password must contain 1 uppercase letter, 1 lowercase letter, 1 number and one special symbol of [@$#^&*!]");
    }

    public void registrationEmptyEmail(){
        User user = new User()
                .setName("Lisa")
                .setLastName("Snow")
                .withEmail("")
                .withPassword("Snow1234");
        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicyXY();
        app.getHelperUser().submit();
        Assert.assertEquals(app.getHelperUser().getErrorText(), "Email is required");
    }

    public void registrationEmptyPassword(){
        User user = new User()
                .setName("Lisa")
                .setLastName("Snow")
                .withEmail("snow@gmail.com")
                .withPassword("");
        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicyXY();
        app.getHelperUser().submit();
        Assert.assertEquals(app.getHelperUser().getErrorText(), "Password is required");
    }

    public void registrationWithoutCheckbox(){
        User user = new User()
                .setName("Lisa")
                .setLastName("Snow")
                .withEmail("snow@gmail.com")
                .withPassword("Snow1234");
        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().submit();
    }

}
