package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Random;

public class RegistrationTests extends TestBase {

    @BeforeMethod(alwaysRun = true)
    public void preCondition() {
        if (app.getHelperUser().isLogged()) {
            app.getHelperUser().logout();
            logger.info("I need logout");
        }

    }

    @Test(groups = {"smoke", "task"})
    public void registrationSuccess() {
        logger.info("Registration with valid data: 'random email: dsa@gmail.com & password: Qq12345$'");
        Random random = new Random();
        int i = random.nextInt(100);
        String email = "dsa" + i + "@gmail.com";

        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(email, "Qq12345$");
        app.getHelperUser().submitRegistration();
        Assert.assertTrue(app.getHelperUser().isLogged());
        logger.info("registration success");
    }

    @Test
    public void registrationWrongEmail() {

        logger.info("Registration with 'invalid email: dsagmail.com & valid password: Qq12345$'");
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm("dsagmail.com", "Qq12345$");
        app.getHelperUser().submitRegistration();
        //Assert.assertFalse(app.getHelperUser().isLogged());
        Assert.assertTrue(app.getHelperUser().isErrorMessageDisplayed("Wrong email or password format"));
        logger.info("Error message is: 'Wrong email or password format'");
    }

    @Test
    public void registrationWrongPassword() {
        logger.info("Registration with 'valid: email: dsa@gmail.com & invalid password: Qq123'");
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm("dsa@gmail.com", "Qq123");
        app.getHelperUser().submitRegistration();
        //Assert.assertFalse(app.getHelperUser().isLogged());
        Assert.assertTrue(app.getHelperUser().isErrorMessageDisplayed("Wrong email or password format"));
        logger.info("Error message is: 'Wrong email or password format'");
    }

    @Test
    public void registrationUserAlreadyExists() {
        logger.info("Registration with valid data of registered user: 'email: dsa@gmail.com & password: 'Qq12345$'");
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm("dsa@gmail.com", "Qq12345$");
        app.getHelperUser().submitRegistration();
        //Assert.assertFalse(app.getHelperUser().isLogged());
        Assert.assertTrue(app.getHelperUser().isErrorMessageDisplayed("User already exist"));
        logger.info("Error message is: 'User already exist'");
    }
    //    @AfterMethod
//    public void poscondition(){
//        if(){
//            logout()
//        }
//    }

}
