import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class GithubSearchTest {

    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1920x1080";
    }

    @Test
    void codeSearchInGithubTest() {
        open("https://github.com/");

        //Откройте страницу Selenide в Github
        $("[data-test-selector=nav-search-input]").setValue("selenide").pressEnter();
        $$("ul.repo-list li").first().$("a").click();

        //Перейдите в раздел Wiki проекта
        $(By.id("wiki-tab")).click();

        //Убедитесь, что в списке страниц (Pages) есть страница SoftAssertions
        $(".markdown-body").shouldHave(Condition.text("Soft assertions"));

        //Откройте страницу SoftAssertions, проверьте что внутри есть пример кода для JUnit5
        $(".markdown-body").$(byText("Soft assertions")).click();
        $(".markdown-body").shouldHave(Condition.text("Using JUnit5 extend test class:"));
    }
}
