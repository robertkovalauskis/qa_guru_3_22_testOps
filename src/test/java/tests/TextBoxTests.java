package tests;

import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.cssClass;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

@Feature("Form fill tests")
@Story("Text box")
public class TextBoxTests extends TestBase {

    @Test
    @DisplayName("Successful fill text box form")
    void fillFormTest() {
        step("Open Text Box form", () -> {
            open("https://demoqa.com/text-box");
            $(".main-header").shouldHave(text("Text Box"));
        });

        step("Fill Text Box form", () -> {
            $("#userName").val("Alex");
            $("#userEmail").val("Egorov@alex.com");
            $("#currentAddress").val("Montenegro");
            $("#permanentAddress").val("Street 1");
            $("#submit").click();
        });

        step("Verify successful form submit", () -> {
            $("#output").shouldHave(text("Name:Alex\n" +
                    "Email:Egorov@alex.com\n" +
                    "Current Address :Montenegro\n" +
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
