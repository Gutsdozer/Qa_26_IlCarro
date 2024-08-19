package manager;

import models.Car;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import java.sql.SQLOutput;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class HelperCar extends HelperBase{

    public HelperCar(WebDriver wd) {
        super(wd);
    }

    public void openCarForm() {
        pause(500);
        click(By.xpath("//a[text()=' Let the car work ']"));
    }

 public void fillCarForm(Car car){
        typeLocation(car.getLocation());
        type(By.id("make"), car.getManufacture());
        type(By.id("model"), car.getModel());
        type(By.id("year"), car.getYear());
        type(By.id("fuel"), car.getFuel());
        type(By.id("seats"), String.valueOf(car.getSeats()));
        type(By.id("class"), car.getCarClass());
        type(By.id("serialNumber"), car.getCarRegNumber());
        type(By.id("price"), car.getPrice()+"");

 }

    private void typeLocation(String location) {
        type(By.id("pickUpPlace"), location);
        click(By.cssSelector("div.pac-item"));
    }

    public void select(By locator, String option){
        Select select = new Select(wd.findElement(locator));
        select.selectByValue(option);
        //Gas
        //select.selectByIndex(5);
        //select.selectByValue("Gas");
        //select.selectByVisibleText(" Gas ");
    }

    public void returnToHomePage() {
        click(By.xpath("//button[text()='Search cars']"));
    }


    public void attachPhoto(String link) {
        wd.findElement(By.cssSelector("#photos")).sendKeys(link);
    }

    public void searchCurrentMonth(String city, String dateFrom, String dateTo) {
        typeCity(city);
        clearTextField(By.id("dates"));
        click(By.id("dates"));

        //"1/25/2024", "1/28/2024"   25  28
        String[] from = dateFrom.split("/"); ///["1"] ["25"] ["2024"]
        String locatorFrom = "//div[text() = ' " + from[1] + " ']";
        click(By.xpath(locatorFrom));
        String[] to = dateTo.split("/");
        click(By.xpath("//div[text() = ' " + to[1] + " ']"));

    }

    private void typeCity(String city) {
        type(By.id("city"), city);
        click(By.cssSelector("div.pac-item"));
    }


    public boolean isListOfCarsAppeared() {
        return isElementPresent(By.cssSelector("a.car-container"));
    }

    public void searchCurrentYear(String city, String dateFrom, String dateTo) {
    type(By.id("city"), city);
    click(By.id("dates"));
        LocalDate now = LocalDate.now();
        System.out.println(now);

        int year = now.getYear();
        int month = now.getMonthValue();
        int day = now.getDayOfMonth();

        LocalDate from = LocalDate.parse(dateFrom, DateTimeFormatter.ofPattern("M/dd/yyyy"));
        LocalDate to = LocalDate.parse(dateTo, DateTimeFormatter.ofPattern("M/dd/yyyy"));
        System.out.println(from);
        //LocalDate from1 = LocalDate.parse("2013:23/05", DateTimeFormatter.ofPattern("yyyy:dd/mm"));
        //System.out.println(from1);

        int difMonth = from.getMonthValue() - month;
        if(difMonth>0){
            clickNextMonthBtn(difMonth);
        }
        click(By.xpath("//div[text() = ' " + from.getDayOfMonth() + " ']"));

        difMonth = to.getMonthValue() - from.getMonthValue();
        if(difMonth>0){
            clickNextMonthBtn(difMonth);
        }
        String locator = String.format("//div[text() = ' %s ']", to.getDayOfMonth());

    }

    private void clickNextMonthBtn(int difMonth) {
        for (int i = 0; i < difMonth; i++) {
            click(By.cssSelector("button[aria-label='Next month']"));
        }
    }
    public void searchAnyPeriod(String city, String dateFrom, String dateTo) {
        clearTextField(By.id("city"));
        typeCity(city);
        clearTextField(By.id("dates"));
        click(By.id("dates"));

        LocalDate now = LocalDate.now();
        LocalDate from = LocalDate.parse(dateFrom, DateTimeFormatter.ofPattern("M/d/yyyy"));
        LocalDate to = LocalDate.parse(dateTo, DateTimeFormatter.ofPattern("M/d/yyyy"));

        int diffYear;
        int diffMonth;

        ///***from
        diffYear = from.getYear() - now.getYear();
        if (diffYear == 0) {
            diffMonth = from.getMonthValue() - now.getMonthValue();
        } else {
            diffMonth = 12 - now.getMonthValue() + from.getMonthValue();
        }
        clickNextMonthBtn(diffMonth);

        String locator = String.format("//div[text()=' %s ']", from.getDayOfMonth());
        click(By.xpath(locator));

        ///***to

        diffYear = to.getYear() - from.getYear();
        if (diffYear == 0) {
            diffMonth = to.getMonthValue() - from.getMonthValue();
        } else {
            diffMonth = 12 - from.getMonthValue() + to.getMonthValue();
        }
        clickNextMonthBtn(diffMonth);

        locator = String.format("//div[text()=' %s ']", to.getDayOfMonth());
        click(By.xpath(locator));

    }

    public void navigateByLogo() {
        click(By.cssSelector("a.logo"));
    }

    public void searchNotValidPeriod(String city, String dateFrom, String dateTo) {
    typeCity(city);
    clearTextField(By.id("dates"));
    type(By.id("dates"), dateFrom + " - " + dateTo);
    click(By.cssSelector("div.cdk-overlay-backdrop"));
    }
}
