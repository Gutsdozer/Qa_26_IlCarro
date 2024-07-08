package tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTests extends TestBase {

    @BeforeMethod
    public void precondition(){
        if (app.getHelperUser().isLogged()){
            app.getHelperUser().logout();
        }
    }

    @Test
    public void loginSuccess(){
    app.getHelperUser().openLoginForm();
    app.getHelperUser().fillLoginForm("warderbass@gmail.com", "Freddy666!");
    app.getHelperUser().submitLogin();
    app.getHelperUser().clickOkAfterLogin();
        Assert.assertTrue(app.getHelperUser().isLogged());
    }

    @Test
    public void loginWrongEmail(){
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm("warderbas@gmail.com", "Freddy666!");
        app.getHelperUser().submitLogin();
        app.getHelperUser().loginOrPasswordIncorrectPresent();

    }

    @Test
    public void loginWrongEmailWithoutAt(){
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm("warderbassgmail.com", "Freddy666!");
        app.getHelperUser().notAnEmailMessagePresent();

    }

    @Test
    public void loginWrongPassword(){
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm("warderbass@gmail.com", "Freddy666");
        app.getHelperUser().submitLogin();
        app.getHelperUser().stop(1);
        app.getHelperUser().loginOrPasswordIncorrectPresent();

    }

    @Test
    public void loginUnregisteredUser(){
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm("luka@gmail.com", "GoodDay1234!");
        app.getHelperUser().submitLogin();
        app.getHelperUser().stop(1);
        app.getHelperUser().loginOrPasswordIncorrectPresent();

    }
}
