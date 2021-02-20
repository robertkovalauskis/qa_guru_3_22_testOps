package tests;

import com.github.javafaker.Faker;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Locale;

import static com.codeborne.selenide.Condition.cssClass;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

@Story("Text box")
@Feature("Fill the Text box")
public class TextBoxTests extends TestBase {
    Faker faker = new Faker();
    FakeValuesService fakeValuesService = new FakeValuesService(
            new Locale("en-GB"), new RandomService());
    String firstName = faker.name().firstName();
    String lastName = faker.name().lastName();
    String userEmail = fakeValuesService.bothify("????##@gmail.com");
    String userNumber = fakeValuesService.regexify("[0-9]{10}");
    String currentAddress = faker.address().fullAddress();

    @Test
    @DisplayName("Successful filling of the text box")
    void fillFormTest() {
        step("Open Text Box form", () -> {
            open("https://demoqa.com/text-box");
            $(".main-header").shouldHave(text("Text Box"));
        });

        step("Fill Text Box form", () -> {
            $("#userName").val(firstName);
            $("#userEmail").val(userEmail);
            $("#currentAddress").val(currentAddress);
            $("#permanentAddress").val("Street 1");
            $("#submit").click();
        });

        step("Verify successful form submit", () -> {
            $("#output").shouldHave(text("Name:" + firstName +
                    "Email:" + userEmail +
                    "Current Address : " + currentAddress +
                    "Permananet Address :Street 1"));
        });
    }

    @Test
    @DisplayName("Unsuccessful fill text box form with wrong email")
    void wrongEmailTest() {
        step("Open Text Box form", () -> {
            open("https://demoqa.com/text-box");
            $(".main-header").shouldHave(text("Text Box"));
        });

        step("Fill Text Box form", () -> {
            $("#userName").val("Alex");
            $("#userEmail").val("Egorov");
            $("#currentAddress").val("Montenegro");
            $("#permanentAddress").val("Street 1");
            $("#submit").click();
        });

        step("Verify unsuccessful form submit", () -> {
            $("#userEmail").shouldHave(cssClass("field-error"));
        });
    }
}
