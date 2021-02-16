package tests;

import com.github.javafaker.Faker;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;
import io.qameta.allure.AllureId;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Locale;

import static com.codeborne.selenide.Condition.cssValue;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

@Feature("Form fill tests")
@Story("Student registration")
public class StudentRegistrationFormTests extends TestBase {

    @Test
    @DisplayName("Successful fill registration form")
    void successfulFillFormTest() {
        Faker faker = new Faker();
        FakeValuesService fakeValuesService = new FakeValuesService(
                new Locale("en-GB"), new RandomService());
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String userEmail = fakeValuesService.bothify("????##@gmail.com");
        String userNumber = fakeValuesService.regexify("[0-9]{10}");
        String currentAddress = faker.address().fullAddress();

        String gender = "Other",
                dayOfBirth = "10",
                monthOfBirth = "May",
                yearOfBirth = "1988",
                subject1Prefix = "m",
                subject1 = "Chemistry",
                subject2Prefix = "e",
                subject2 = "Commerce",
                hobby1 = "Sports",
                hobby2 = "Reading",
                hobby3 = "Music",
                picture = "1.png",
                state = "Uttar Pradesh",
                city = "Merrut";

        step("Open students registration form", () -> {
            open("https://demoqa.com/automation-practice-form");
            $(".practice-form-wrapper").shouldHave(text("Student Registration Form"));
        });

        step("Fill students registration form", () -> {
            $("#firstName").val(firstName);
            $("#lastName").val(lastName);
            $("#userEmail").val(userEmail);
            $("#genterWrapper").$(byText(gender)).click();
            $("#userNumber").val(userNumber);
            // set date
            $("#dateOfBirthInput").click();
            $(".react-datepicker__month-select").selectOption(monthOfBirth);
            $(".react-datepicker__year-select").selectOption(yearOfBirth);
            $(".react-datepicker__day--0" + dayOfBirth).click();
            // set subject
            $("#subjectsInput").val(subject1Prefix);
            $(".subjects-auto-complete__menu-list").$(byText(subject1)).click();
            $("#subjectsInput").val(subject2Prefix);
            $(".subjects-auto-complete__menu-list").$(byText(subject2)).click();
            // set hobbies
            $("#hobbiesWrapper").$(byText(hobby1)).click();
            $("#hobbiesWrapper").$(byText(hobby2)).click();
            $("#hobbiesWrapper").$(byText(hobby3)).click();
            $("#uploadPicture").uploadFile(new File("src/test/resources/" + picture));
            $("#currentAddress").val(currentAddress);
            // set state and city
            $("#state").click();
            $("#stateCity-wrapper").$(byText(state)).click();
            $("#city").click();
            $("#stateCity-wrapper").$(byText(city)).click();

            $("#submit").click();
        });

        step("Verify successful form submit", () -> {
            $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
            $x("//td[text()='Student Name']").parent().shouldHave(text(firstName + " " + lastName));
            $x("//td[text()='Student Email']").parent().shouldHave(text(userEmail));
            $x("//td[text()='Gender']").parent().shouldHave(text(gender));
            $x("//td[text()='Mobile']").parent().shouldHave(text(userNumber));
            $x("//td[text()='Date of Birth']").parent().shouldHave(text(dayOfBirth + " " + monthOfBirth + "," + yearOfBirth));
            $x("//td[text()='Subjects']").parent().shouldHave(text(subject1 + ", " + subject2));
            $x("//td[text()='Hobbies']").parent().shouldHave(text(hobby1 + ", " + hobby2 + ", " + hobby3));
            $x("//td[text()='Picture']").parent().shouldHave(text(picture));
            $x("//td[text()='Address']").parent().shouldHave(text(currentAddress));
            $x("//td[text()='State and City']").parent().shouldHave(text(state + " " + city));
        });
    }

    @Test
    @DisplayName("Unsuccessful fill registration form with short phone number")
    void unsuccessfulFillFormWithShortPhoneTest() {
        Faker faker = new Faker();
        FakeValuesService fakeValuesService = new FakeValuesService(
                new Locale("en-GB"), new RandomService());
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String userEmail = fakeValuesService.bothify("????##@gmail.com");
        String userNumber = fakeValuesService.regexify("[0-9]{9}");
        String currentAddress = faker.address().fullAddress();

        String gender = "Other",
                dayOfBirth = "10",
                monthOfBirth = "May",
                yearOfBirth = "1988",
                subject1Prefix = "m",
                subject1 = "Chemistry",
                subject2Prefix = "e",
                subject2 = "Commerce",
                hobby1 = "Sports",
                hobby2 = "Reading",
                hobby3 = "Music",
                picture = "1.png",
                state = "Uttar Pradesh",
                city = "Merrut";

        step("Open students registration form", () -> {
            open("https://demoqa.com/automation-practice-form");
            $(".practice-form-wrapper").shouldHave(text("Student Registration Form"));
        });

        step("Fill students registration form", () -> {
            $("#firstName").val(firstName);
            $("#lastName").val(lastName);
            $("#userEmail").val(userEmail);
            $("#genterWrapper").$(byText(gender)).click();
            $("#userNumber").val(userNumber);
            // set date
            $("#dateOfBirthInput").click();
            $(".react-datepicker__month-select").selectOption(monthOfBirth);
            $(".react-datepicker__year-select").selectOption(yearOfBirth);
            $(".react-datepicker__day--0" + dayOfBirth).click();
            // set subject
            $("#subjectsInput").val(subject1Prefix);
            $(".subjects-auto-complete__menu-list").$(byText(subject1)).click();
            $("#subjectsInput").val(subject2Prefix);
            $(".subjects-auto-complete__menu-list").$(byText(subject2)).click();
            // set hobbies
            $("#hobbiesWrapper").$(byText(hobby1)).click();
            $("#hobbiesWrapper").$(byText(hobby2)).click();
            $("#hobbiesWrapper").$(byText(hobby3)).click();
            $("#uploadPicture").uploadFile(new File("src/test/resources/" + picture));
            $("#currentAddress").val(currentAddress);
            // set state and city
            $("#state").click();
            $("#stateCity-wrapper").$(byText(state)).click();
            $("#city").click();
            $("#stateCity-wrapper").$(byText(city)).click();

            $("#submit").click();
        });

        // todo bad assert
        step("Verify successful form submit", () -> {
            $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
            $x("//td[text()='Student Name']").parent().shouldHave(text(firstName + " " + lastName));
            $x("//td[text()='Student Email']").parent().shouldHave(text(userEmail));
            $x("//td[text()='Gender']").parent().shouldHave(text(gender));
            $x("//td[text()='Mobile']").parent().shouldHave(text(userNumber));
            $x("//td[text()='Date of Birth']").parent().shouldHave(text(dayOfBirth + " " + monthOfBirth + "," + yearOfBirth));
            $x("//td[text()='Subjects']").parent().shouldHave(text(subject1 + ", " + subject2));
            $x("//td[text()='Hobbies']").parent().shouldHave(text(hobby1 + ", " + hobby2 + ", " + hobby3));
            $x("//td[text()='Picture']").parent().shouldHave(text(picture));
            $x("//td[text()='Address']").parent().shouldHave(text(currentAddress));
            $x("//td[text()='State and City']").parent().shouldHave(text(state + " " + city));
        });
    }

    @Test
    @AllureId("1513")
    @DisplayName("Unsuccessful fill registration form without Last name")
    void unsuccessfulFillFormWithoutLastName() {
        step("Open students registration form", () -> {
            open("https://demoqa.com/automation-practice-form");
            $(".practice-form-wrapper").shouldHave(text("Student Registration Form"));
        });

        step("Fill students registration form without Last name", () -> {
            $("#submit").click();
        });

        step("Last name input should be marked with red border", () -> {
//            $("#lastName").shouldHave(cssValue("border-color", "#dc3545"));
            $("#lastName").shouldHave(cssValue("border-color", "rgb(220, 53, 69)"));
        });
    }
}
