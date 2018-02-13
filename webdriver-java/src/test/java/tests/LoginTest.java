package tests;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import suporte.Generator;
import suporte.Screenshot;
import suporte.Web;

import static org.junit.Assert.assertEquals;

public class LoginTest {

    private WebDriver browser;

    @Before
    public void setUp(){

        browser = Web.createBrowserStack();
    }

    @Test
    public void testLogin(){

        // Login click
        WebElement linkSignIn = browser.findElement(By.linkText("Already have an account"));
        linkSignIn.click();

        // Type the username on the "login" field
        linkSignIn.findElement(By.name("username")).sendKeys(("userTest"));

        // Type the password on the "password" field
        linkSignIn.findElement(By.name("password")).sendKeys(("12345678"));

        // Click on the button login
        browser.findElement(By.linkText("login")).click();

        // Validate that the element with the login user has the text "Hi, userTest"
        WebElement me = browser.findElement(By.className("me"));

        String textoNoElementoMe = me.getText();

        assertEquals("Hi, userTest",textoNoElementoMe);

        // Take a screenshot
        Screenshot.take(browser, "/location/" + Generator.dateHourFile() +
                "Login.png");

    }
}
