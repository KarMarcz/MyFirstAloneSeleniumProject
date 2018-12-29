package com.automationpractice.ui;

import com.automationpractice.pageObject.AuteticationPage;
import com.automationpractice.pageObject.CreateAnAccountPage;
import com.automationpractice.pageObject.MainPage;
import com.automationpractice.utils.WebDriverProvider;
import org.assertj.core.api.Condition;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.WebDriver;

import java.util.LinkedHashSet;

import static org.assertj.core.api.Assertions.anyOf;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.util.Sets.newLinkedHashSet;

public class RegistrationTest {
    private WebDriver driver;
    private MainPage mainPage;
    private AuteticationPage auteticationPage;
    private CreateAnAccountPage createAnAccountPage;
    private static final LinkedHashSet<String> emailAssertion = newLinkedHashSet("Syntax correct for Email", "Invalid email address.");
    public static final Condition<String> emailCondition = new Condition<>(emailAssertion::contains, "Condition That check if proper text return from method after passing email into site");
    @BeforeEach
    public void setUp() {
        driver = WebDriverProvider.WebDriverProvider();
        driver.manage().window().maximize();
        mainPage = new MainPage(driver);
        auteticationPage = new AuteticationPage(driver);
        createAnAccountPage = new CreateAnAccountPage(driver);

    }
    @ParameterizedTest
    @MethodSource
    public void registrationTest (String email, String gender, String firstName, String lastName, String passwd, String Date) {
        driver.get("http://automationpractice.com/index.php");
        mainPage.clickOnSingInButton();
        String fillEmailFieldAndClickcreateAnAccountButtonReturnMessage = auteticationPage.fillEmailFieldAndClickcreateAnAccountButton(email);
        assertThat(fillEmailFieldAndClickcreateAnAccountButtonReturnMessage).is(anyOf(emailCondition));
        if(fillEmailFieldAndClickcreateAnAccountButtonReturnMessage.equals("Syntax correct for Email"))
            System.out.println(createAnAccountPage.fillingPersonalInformationAndAddressAndClickRegisterButton(gender, firstName, lastName, passwd, Date));
    }
    @AfterEach
    public void tearDown () {
        driver.close();
    }

    static String[][] registrationTest () {
        return new String[][]{
          new String[]{"karol@sss.pl","M","Michal","Wisnia2","asc", "12-08-1994"},
          new String[]{"karol@sss.pl","W","Iwona","Poranek1","asdf1", "18-02-1990"},
        };
    }

    /*Zadania:
    -Parametrize Test - DDT
    -Przekazywanie danych dalej
    -Ze wzgledu na dane Takie operowanie nimi żeby zawsze asercje sprawdzialy to co powinny dla odpowiednich danych typu:
    Jeżeli ktoś nie wpisał imienia to co się dzieje
    Co będzie jak nie wpiszesz numeru
    ...
     */
    /*
        DDT:
        - Zip required format like / 00000 /
        - City required
        - password required minimum 5 char
        - first name required
        - lastname required
        - adress required
        -
     */
}
