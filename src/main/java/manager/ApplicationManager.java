package manager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.support.events.EventFiringDecorator;
import org.openqa.selenium.support.events.WebDriverListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

    public class ApplicationManager {
        WebDriver wd;
        HelperUser helperUser;
        HelperContact helperContact;
        Logger logger = LoggerFactory.getLogger(ApplicationManager.class);



        public void init() {
            wd = new ChromeDriver();
            logger.info("All tests star in Chrome Browser");

            WebDriverListener listener = new ListenerWD();
            wd=new EventFiringDecorator<>(listener).decorate(wd);

            wd.manage().window().maximize();
            wd.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
            wd.navigate().to("https://telranedu.web.app");

            logger.info("Current Url -->" +wd.getCurrentUrl());

            helperUser= new HelperUser(wd);
            helperContact = new HelperContact(wd);

        }

        public void stop() {
            wd.quit();
        }

        public HelperUser getHelperUser() {
            return helperUser;
        }
        public HelperContact helperContact() {
            return helperContact;
        }


    }

