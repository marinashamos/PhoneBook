package manager;

import model.Contact;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.List;

public class HelperContact extends HelperBase {
    public HelperContact(WebDriver wd) {
        super(wd);
    }

    public void openContactForm() {

        click(By.cssSelector("a[href='/add']"));
    }
@Test
    public void fillContactForm(Contact contact) {
        type(By.cssSelector("input[placeholder='Name']"), contact.getName());
        type(By.cssSelector("input[placeholder='Last Name']"), contact.getLastName());
        type(By.cssSelector("input[placeholder='Phone']"), contact.getPhone());
        type(By.cssSelector("input[placeholder='email']"), contact.getEmail());
        type(By.cssSelector("input[placeholder='Address']"), contact.getAddress());
        type(By.cssSelector("input[placeholder='description']"), contact.getDescription());

    }
    @Test
    public void submitContactForm() {
        //.add_form__2rsm2 button
        click(By.xpath("//b[text()='Save']"));
    }
    @Test
    public boolean isContactAddedByName(String name) {

        List<WebElement> list = wd.findElements(By.cssSelector("h2"));
        for (WebElement el : list) {
            if (el.getText().equals(name)) {
                return true;
            }
        }
        return false;
    }
    @Test
    public boolean isContactAddedByPhone(String phone) {
        List<WebElement> list = wd.findElements(By.cssSelector("h3"));
        for (WebElement el : list) {
            if (el.getText().equals(phone)) {
                return true;
            }
        }
        return false;
    }
    @Test
    public boolean isAddPageStillDisplayed() {

        return  wd.findElements(By.cssSelector("a.active[href='/add']")).size()>0;
    }
    @Test
    public boolean isContactAddedByEmail(String email) {
        List<WebElement> lis = wd.findElements(By.cssSelector(".contact-item_card__2SOIM"));
        for(WebElement el: lis){
            el.click();
            String text = wd.findElement(By.cssSelector(".contact-item-detailed_card__50dTS")).getText();
            if(text.contains(email)){
                return true;
            }
        }
            return false;
    }
}
