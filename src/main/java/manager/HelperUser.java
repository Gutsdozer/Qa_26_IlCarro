package manager;

import models.User;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class HelperUser extends HelperBase {


    public boolean isYallaButtonNotActive(){
        boolean res = isElementPresent(By.cssSelector("button[disabled]"));


        WebElement element = wd.findElement(By.cssSelector("button[type = 'submit']"));
        boolean result = element.isEnabled();

        return res && !result;
    };

    public HelperUser(WebDriver wd) {
        super(wd);
    }

    public void openLoginForm() {
        click(By.xpath("//*[@href = 'login']"));
    }

    public void fillLoginForm(String email, String password) {
        type(By.xpath("//*[@autocomplete = 'username']"), email);
        type(By.xpath("//*[@ng-reflect-name = 'password']"), password);
    }

    public void submitLogin() {
        click(By.xpath("//*[@type = 'submit']"));
    }

    public boolean isLogged() {
        return isElementPresent(By.xpath("//*[@href = '/logout?url=%2Fsearch']"));
    }

    public void logout() {
        click(By.xpath("//*[@href = '/logout?url=%2Fsearch']"));
    }


    public void clickOkAfterLogin() {
        click(By.xpath("//*[@class = 'positive-button ng-star-inserted']"));
    }

    public void loginOrPasswordIncorrectPresent() {
        WebElement message = wd.findElement(By.xpath("//*[@class = 'message']"));
        Assert.assertEquals(message.getText(), "\"Login or Password incorrect\"");
    }

    public void notAnEmailMessagePresent() {
        WebElement message = wd.findElement(By.xpath("//*[@class = 'error']"));
        Assert.assertEquals(message.getText(), "It'snot look like email");
    }

//***************Registration
    public void submit() {
        submitLogin();
    }


    public void openRegistrationForm() {
        click(By.xpath("//*[text() = ' Sign up ']"));
    }

    public void fillRegistrationForm(User user) {
        type(By.id("name"), user.getName());
        type(By.id("lastName"), user.getLastName());
        type(By.id("email"), user.getEmail());
        type(By.id("password"), user.getPassword());
    }

    public void checkPolicy() {
        // click(By.id("terms-of-use")); 0*0
        //variant 2
        click(By.cssSelector("label[for = 'terms-of-use']"));

        //variant 3

        JavascriptExecutor js = (JavascriptExecutor) wd;
        js.executeScript("document.querySelector('#terms-of-use').click()");
    }
}

