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
    app.getHelperUser().click(By.xpath("//*[@class = 'positive-button ng-star-inserted']"));
        Assert.assertTrue(app.getHelperUser().isLogged());
    }
}
