package tests;

import manager.DataProviderUser;
import manager.ListenerTNG;
import model.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Listeners(ListenerTNG.class)
public class LoginTests extends TestBase {

    @BeforeMethod
    public void preCondition() {
        if (app.getHelperUser().isLogged()) {
            app.getHelperUser().logout();
            logger.info("I need logout");
        }
    }
    @DataProvider
    public Iterator<Object[]> loginData() {

        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{"dsa@gmail.com", "Qq12345$"});
        list.add(new Object[]{"mjmj@gmail.com", "Qq123456$"});
        list.add(new Object[]{"alex@gmail.com", "Qq12345$"});

        return list.iterator();
    }
    @Test(dataProvider = "loginData")
    public void loginSuccess(String email, String password) {

        logger.info("Login with valid data :  email: "+email +" & password: "+password);
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(email, password);
        app.getHelperUser().submitLogin();
        Assert.assertTrue(app.getHelperUser().isLogged());

        logger.info("Tests success");
    }
    @Test(invocationCount = 2)
    public void loginSuccessNew() {

        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm("mjmj@gmail.com", "Qq123456$");
        app.getHelperUser().submitLogin();
        Assert.assertTrue(app.getHelperUser().isLogged());
        logger.info("Tests success");
    }
    @Test(dataProvider = "loginDataCls", dataProviderClass = DataProviderUser.class)
    public void loginSuccess2(String email, String password) {

        logger.info("Login with valid data :  email: "+email +" & password: "+password);
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(email, password);
        app.getHelperUser().submitLogin();
        Assert.assertTrue(app.getHelperUser().isLogged());
        logger.info("Tests success");
    }
    @Test(dataProvider = "loginDataUserFromFile", dataProviderClass = DataProviderUser.class)
    public void loginSuccessModelFromFile(User user) {

        logger.info("Tests start with user model---" + user.toString());
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(user);
        app.getHelperUser().submitLogin();
        Assert.assertTrue(app.getHelperUser().isLogged());
    }

    @Test(dataProvider = "loginDataUser", dataProviderClass = DataProviderUser.class)
    public void loginSuccessModel(User user) {

        logger.info("Tests start with user model ---" + user.toString());
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(user);
        app.getHelperUser().submitLogin();
        Assert.assertTrue(app.getHelperUser().isLogged());
    }

    @Test
    public void loginWrongEmail() {
        logger.info("Login with invalid email: 'dsagmail.com' & valid password: Qq12345$");

        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm("dsagmail.com", "Qq12345$");
        app.getHelperUser().submitLogin();
        Assert.assertFalse(app.getHelperUser().isLogged());
        Assert.assertTrue(app.getHelperUser().isErrorMessageDisplayed("Wrong email or password"));
        logger.info("Error message is: 'Wrong email or password'");

    }

    @Test
    public void loginWrongPassword() {
        logger.info("Login with valid email: dsa@gmail.com & invalid password: 'Qq12'");
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm("dsa@gmail.com", "Qq12");
        app.getHelperUser().submitLogin();
        Assert.assertFalse(app.getHelperUser().isLogged());
        Assert.assertTrue(app.getHelperUser().isErrorMessageDisplayed("Wrong email or password"));
        logger.info("Error message is: 'Wrong email or password'");
    }

    @Test
    public void loginUnregisterUser() {
        logger.info("Login with valid data of unregistered user: 'email: mimi@gmail.com & invalid password: 'Tt12345$'");
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm("momo@gmail.com", "Tt12345$");
        app.getHelperUser().submitLogin();
        Assert.assertFalse(app.getHelperUser().isLogged());
        Assert.assertTrue(app.getHelperUser().isErrorMessageDisplayed("Wrong email or password"));
        logger.info("Error message is: 'Wrong email or password'");

    }
}
