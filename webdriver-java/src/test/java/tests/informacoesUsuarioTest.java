package tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import suporte.Generator;
import suporte.Screenshot;
import suporte.Web;

import java.nio.channels.SelectableChannel;
import java.util.concurrent.TimeUnit;

public class informacoesUsuarioTest {

    private WebDriver navegador;

    @Before
    public void setUp(){

        navegador = Web.createBrowserStack();

        // Abrindo o navegador
        //System.setProperty("webdriver.chrome.driver","/Users/renanchagas/Documents/Drivers/chromedriver");
        //navegador = new ChromeDriver();
        //navegador.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        // Navegando para a página do taskit
        //navegador.get("http://www.juliodelima.com.br/taskit");
    }

    @Test
    public void testAdicionarUmaInformacaoAdicionalDoUsuario(){

        // Clicar no sign in
        WebElement linkSignIn = navegador.findElement(By.linkText("Sign in"));
        linkSignIn.click();

        // Identificando o Formulário
        WebElement formularioSignInBox = navegador.findElement(By.id("signinbox"));

        // Digitar no campo com name "login" que está dentro do formulário de id "signinbox" o text "julio0001"
        formularioSignInBox.findElement(By.name("login")).sendKeys(("julio0001"));

        //Digitar no campo com name "password" que está dentro do formulário de id "signinbox" o text "123456"
        formularioSignInBox.findElement(By.name("password")).sendKeys(("123456"));

        //Clicar no link com o text "SIGN IN"
        navegador.findElement(By.linkText("SIGN IN")).click();

        //Validar que dentro do elemento com class "me" está o texto "Hi, Julio"
        WebElement me = navegador.findElement(By.className("me"));

        String textoNoElementoMe = me.getText();

        assertEquals("Hi, Julio",textoNoElementoMe);

        // Tirar screenshot
        Screenshot.take(navegador, "/Users/renanchagas/Documents/test-report/taskit/" + Generator.dateHourFile() +
            "Sign in.png");

        // Clicar em um link que possui a class "me"
        navegador.findElement(By.className("me")).click();

        // Clicar em um link que possui o texto "MORE DATA ABOUT YOU"
        navegador.findElement(By.linkText("MORE DATA ABOUT YOU")).click();

        // Clicar no botão através do seu xpath //button[@data-target="addmoredata"]
        navegador.findElement(By.xpath("//button[@data-target=\"addmoredata\"]")).click();

        // Identificar a popup onde está o formulário de id addmoredata
        WebElement popupAddMoreData = navegador.findElement(By.id("addmoredata"));

        // Na combo de name "type" escolhe a opção "Phone"
        WebElement campoType =  popupAddMoreData.findElement(By.name("type"));
        new Select(campoType).selectByVisibleText("Phone");

        // Na combo de name "contact" digitar "+5521996965919"
        popupAddMoreData.findElement(By.name("contact")).sendKeys("+5521996965919");

        // Clicar no link de text "SAVE" que está na popup
        popupAddMoreData.findElement(By.linkText("SAVE")).click();

        // Na mensagem de id "toast-container" validar que o texto é "Your contact has been added!"
        WebElement mensagemToast = navegador.findElement(By.id("toast-container"));
        String mensagem = mensagemToast.getText();

        assertEquals("Your contact has been added!", mensagem);

    }

    @After
    public void tearDown(){
        //Fechar o Navegador
        navegador.close();
    }
}
