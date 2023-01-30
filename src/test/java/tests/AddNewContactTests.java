package tests;

import model.Contact;
import model.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Random;

public class AddNewContactTests extends TestBase {

    @BeforeMethod
    public void preCondition() {
        if (!app.getHelperUser().isLogged()) {
            app.getHelperUser().login(User.builder().email("dsa@gmail.com").password("Qq12345$").build());

        }
    }

    @Test
    public void addContactSuccessAllFields() {
        Random random = new Random();
        int i = random.nextInt(100) + 1000;

        Contact contact = Contact.builder()
                .name("Marina" + i)
                .lastName("Shamos")
                .address("Canberra")
                .phone("7777777" + i)
                .email("marina" + i + "@gmail.com")
                .description("Friend").build();

        System.out.println(contact.toString());
        logger.info("Tests start with data : " +contact.toString());

        app.helperContact().openContactForm();
        app.helperContact().fillContactForm(contact);
        app.helperContact().submitContactForm();

        Assert.assertTrue(app.helperContact().isContactAddedByName(contact.getName()));
        Assert.assertTrue(app.helperContact().isContactAddedByPhone(contact.getPhone()));
        Assert.assertTrue(app.helperContact().isContactAddedByEmail(contact.getEmail()));
        logger.info("Checked, if a contact was created by name,phone number and email");


    }

    @Test
    public void addContactSuccessRequiredFields() {
        Random random = new Random();
        int i = random.nextInt(1000) + 1000;
        Contact contact = Contact.builder()
                .name("Leo" + i)
                .lastName("Shamos")
                .address("NY")
                .phone("57575757" + i)
                .email("leo"+i+"@gmail.ru")
                .build();
        System.out.println(contact.toString());

        logger.info("Tests start with data : " +contact.toString());

        app.helperContact().openContactForm();
        app.helperContact().fillContactForm(contact);
        app.helperContact().submitContactForm();
        Assert.assertTrue(app.helperContact().isContactAddedByName(contact.getName()));
        Assert.assertTrue(app.helperContact().isContactAddedByPhone(contact.getPhone()));
        Assert.assertTrue(app.helperContact().isContactAddedByEmail(contact.getEmail()));
        logger.info("Checked, if a contact was created by name,phone number and email");
    }

    @Test
    public void addNewContactWrongName() {

        Contact contact = Contact.builder()

                .lastName("Shamos")
                .address("Canberra")
                .phone("777777711111")
                .email("marina@gmail.com")
                .description("wrong name").build();
        System.out.println("contact" + contact.getName());
        logger.info("Tests start with data : " +contact.toString());

        /*Contact contact1 = Contact.builder()
                .name("")
                .lastName("Shamos")
                .address("Canberra")
                .phone("777777711111")
                .email("marina@gmail.com")
                .description("wrong name").build();
        System.out.println(contact.equals(contact1));
        //System.out.println(contact1.getName());
        //System.out.println("contact1" + contact.getName());
        System.out.println(contact.toString());
        */
        app.helperContact().openContactForm();
        app.helperContact().fillContactForm(contact);
        app.helperContact().pause(10000);
        app.helperContact().submitContactForm();
        Assert.assertTrue(app.helperContact().isAddPageStillDisplayed());
        logger.info("Status code 400, message not received: mane must not be empty => Bug report");
    }

    @Test
    public void addNewContactWrongLastName() {

        Contact contact = Contact.builder()
                .name("Maria")
                .lastName("")
                .address("Canberra")
                .phone("777777711111")
                .email("maria@gmail.com")
                .description("wrong Last name").build();
        System.out.println(contact.toString());

        app.helperContact().openContactForm();
        app.helperContact().fillContactForm(contact);
        //app.helperContact().pause(10000);
        app.helperContact().submitContactForm();
        //app.helperContact().pause(10000);
        Assert.assertTrue(app.helperContact().isAddPageStillDisplayed());
        logger.info("Status code 400, message not received: last name must not be empty => Bug report");
    }
    @Test
    public void addNewContactWrongAddress() {

        Contact contact = Contact.builder()
                .name("Maria")
                .lastName("Siman")
                .address("")
                .phone("551111111111")
                .email("maria@mail.com")
                .description("wrong address").build();
        System.out.println(contact.toString());

        app.helperContact().openContactForm();
        app.helperContact().fillContactForm(contact);

        app.helperContact().submitContactForm();

        Assert.assertTrue(app.helperContact().isAddPageStillDisplayed());
        //Assert.assertTrue(app.helperContact().isErrorMessageDisplayed("Email not valid: must be a well-formed email address"));
    }

    @Test
    public void addNewContactWrongPhone() {

        Contact contact = Contact.builder()
                .name("John")
                .lastName("Vacsman")
                .address("NY")
                .phone("51")
                .email("john@gmail.com")
                .description("wrong number phone").build();
        System.out.println(contact.toString());

        app.helperContact().openContactForm();
        app.helperContact().fillContactForm(contact);

        app.helperContact().submitContactForm();
        Assert.assertTrue(app.helperContact().isAddPageStillDisplayed());
        Assert.assertTrue(app.getHelperUser().isErrorMessageDisplayed("Phone not valid: Phone number must contain only digits! And length min 10, max 15"));

    }
    @Test
    public void  addNewContactWrongEmail(){

        Contact contact = Contact.builder()
                .name("John")
                .lastName("Vacsman")
                .address("NY")
                .phone("51111234567")
                .email("johnmail.ru")
                .description("wrong email").build();
        System.out.println(contact.toString());

        app.helperContact().openContactForm();
        app.helperContact().fillContactForm(contact);
        app.helperContact().submitContactForm();
        Assert.assertTrue(app.helperContact().isAddPageStillDisplayed());
        Assert.assertTrue(app.getHelperUser().isErrorMessageDisplayed("Email not valid: must be a well-formed email address"));

    }

}
