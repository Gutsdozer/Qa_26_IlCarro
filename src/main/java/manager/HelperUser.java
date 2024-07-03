package manager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HelperUser extends HelperBase{


    public HelperUser(WebDriver wd) {
        super(wd);
    }

    public void openLoginForm(){
        click(By.xpath("//*[@href = 'login']"));
    }

    public void fillLoginForm(String email, String password){
        type(By.xpath("//*[@autocomplete = 'username']"), email);
        type(By.xpath("//*[@ng-reflect-name = 'password']"), password);
    }

    public void submitLogin(){
        click(By.xpath("//*[@type = 'submit']"));
    }

    public boolean isLogged(){
        return isElementPresent(By.xpath("//*[@href = '/logout?url=%2Fsearch']"));
    }

    public void logout(){
        click(By.xpath("//*[@href = '/logout?url=%2Fsearch']"));
    }
}
