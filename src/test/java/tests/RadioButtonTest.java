package tests;

import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

@Story("Radio button")
@Feature("Select the radio button")
public class RadioButtonTest extends TestBase {

    @Test

    @DisplayName("Select Yes radio button")
    void fillFormTest() {
        step("Open Text Box form", () -> {
            open("https://demoqa.com/radio-button");
            $(".main-header").shouldHave(text("Radio Button"));
        });

        step("Select the radio button", () -> {
            $(".custom-control-label").click();
        });

        step("Verify that radio button is selected", () -> {
            $(".text-success").shouldHave(text("Yes"));
        });
    }

}
