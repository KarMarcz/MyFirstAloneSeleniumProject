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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

import static org.assertj.core.api.Assertions.anyOf;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.util.Sets.newLinkedHashSet;

public class RegistrationTest {
    private WebDriver driver;
    private MainPage mainPage;
    private AuteticationPage auteticationPage;
    private CreateAnAccountPage createAnAccountPage;
    private Logger logger = LoggerFactory.getLogger(RegistrationTest.class);

    private static final LinkedHashSet<String> emailAssertion = newLinkedHashSet("Syntax correct for Email", "Invalid email address.");
    private static final LinkedHashSet<String> registrationAssertion = newLinkedHashSet(
            "firstname is required.",
            "firstname is invalid.",
            "lastname is required.",
            "lastname is invalid.",
            "passwd is required.",
            "passwd is invalid.",
            "This country requires you to choose a State.",
            "Country cannot be loaded with address->id_country",
            "Country is invalid",
            "address1 is required.",
            "city is required.",
            "The Zip/Postal code you've entered is invalid. It must follow this format: 00000",
            "Data correct",
            "Wrong birth date.");
    public static final Condition<String> emailCondition = new Condition<>(emailAssertion::contains, "Condition That check if proper text return from method after passing email into site, if you see this message that means that something wrong");
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
    public void registrationTest (String email, String gender, String firstName, String lastName, String passwd, String Date, String stateOfUSA, String country, Boolean differentDataForYourAddress, String differentFirstName, String differentLastName, String companyName, String address, String city, String postal) {
        driver.get("http://automationpractice.com/index.php");
        mainPage.clickOnSingInButton();
        List<String> allReturnedMessageFromPageAfterTryingToRegisterNewMembere = new ArrayList<>();
        //Save message to String to assert that email is correct or not, depends on condition in DDT
        String fillEmailFieldAndClickCreateAnAccountButtonReturnMessage = auteticationPage.fillEmailFieldAndClickcreateAnAccountButton(email);
        //Assert previous message
        assertThat(fillEmailFieldAndClickCreateAnAccountButtonReturnMessage).is(anyOf(emailCondition));
        //If email passed check the rest condition
        if(fillEmailFieldAndClickCreateAnAccountButtonReturnMessage.equals("Syntax correct for Email")) allReturnedMessageFromPageAfterTryingToRegisterNewMembere = createAnAccountPage.fillingPersonalInformationAndAddressAndClickRegisterButton(gender, firstName, lastName, passwd, Date, stateOfUSA, country, differentDataForYourAddress, differentFirstName, differentLastName, companyName, address, city, postal);

        System.out.println(allReturnedMessageFromPageAfterTryingToRegisterNewMembere.size());
        assertThat(allReturnedMessageFromPageAfterTryingToRegisterNewMembere).isSubsetOf(registrationAssertion);
        logger.debug(String.valueOf(allReturnedMessageFromPageAfterTryingToRegisterNewMembere));
    }
    @AfterEach
    public void tearDown () {
        driver.close();
    }

    static Object[][] registrationTest () {
        return new Object[][]{
          new Object[]{"karol@sss.pl","M","Michal","Wisnia2","asc", "02-08-1991", "Alabaf", "-", true, "Morswin", "SwinMor", "", "", "", "123"},
          new Object[]{"karol@sss.pl","W","Iwona","Poranek","asd23", "18-02-1990", "Alabama", "United States", false, "","", "JM", "Misiakowa", "Pomostowo", "12345"},
        };
    }


    //----------------
    //Zadanie dodatkowe :
    //Builder dla danych w Parametrized Test
    //Dodanie warunku co oczekuje że się zepsuje(chyba w sumie bede zwracał liczbe z listy że np. jeżeli miało być 6 złych danych i 6 problemow to 6 bedzie zapisanych)
    //----------------

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

    //Zadanie zrobie tak że jeżeli Boolean differentDataForYourAddress jest true to znaczy że nastepne dane mają zastąpić dane w YourAddress, w innym przypadku nic nie rób
}
