package tests;

        import org.slf4j.Logger;
        import org.testng.Assert;
        import org.testng.annotations.BeforeMethod;
        import org.testng.annotations.Test;

public class LoginTests extends TestBase{

    @BeforeMethod
    public void preCondition(){
        if(app.getHelperUser().isLogged()){
            app.getHelperUser().logout();
            logger.info("I need logout");
        }

    }
    @Test
    public void loginSuccess(){

        logger.info("Login with valid data: email: dsa@gmail.com & password: Qq12345$");

        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm("dsa@gmail.com","Qq12345$");
        app.getHelperUser().submitLogin();
        Assert.assertTrue(app.getHelperUser().isLogged());

        logger.info("Tests success");
    }
    @Test
    public void loginSuccessNew(){
        logger.info("Login with valid data: 'email: cxz@gmail.com & password: Ss12345$'");

        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm("cxz@gmail.com","Ss12345$");
        app.getHelperUser().submitLogin();
        Assert.assertTrue(app.getHelperUser().isLogged());

        logger.info("Tests success");

    }
    @Test
    public void loginWrongEmail(){
        logger.info("Login with invalid email: 'dsagmail.com' & valid password: Qq12345$");

        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm("dsagmail.com","Qq12345$");
        app.getHelperUser().submitLogin();
        Assert.assertFalse(app.getHelperUser().isLogged());
        Assert.assertTrue(app.getHelperUser().isErrorMessageDisplayed("Wrong email or password"));
        logger.info("Error message is: 'Wrong email or password'");

    }
    @Test
    public void loginWrongPassword(){
        logger.info("Login with valid email: dsa@gmail.com & invalid password: 'Qq12'");
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm("dsa@gmail.com","Qq12");
        app.getHelperUser().submitLogin();
        Assert.assertFalse(app.getHelperUser().isLogged());
        Assert.assertTrue(app.getHelperUser().isErrorMessageDisplayed("Wrong email or password"));
        logger.info("Error message is: 'Wrong email or password'");
    }
    @Test
    public void loginUnregisterUser(){
        logger.info("Login with valid data of unregistered user: 'email: mimi@gmail.com & invalid password: 'Tt12345$'");
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm("mimi@gmail.com","Tt12345$");
        app.getHelperUser().submitLogin();
        Assert.assertFalse(app.getHelperUser().isLogged());
        Assert.assertTrue(app.getHelperUser().isErrorMessageDisplayed("Wrong email or password"));
        logger.info("Error message is: 'Wrong email or password'");

    }
}
