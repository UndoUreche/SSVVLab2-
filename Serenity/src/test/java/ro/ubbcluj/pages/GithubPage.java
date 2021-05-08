package ro.ubbcluj.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

@DefaultUrl("http://github.com")
public class GithubPage extends PageObject {

    @FindBy(name = "q")
    private WebElementFacade searchTerms;

    @FindBy(id = "jump-to-suggestion-search-global")
    private WebElementFacade allGithubLookup;

    public void enter_keywords(String keyword) {
        searchTerms.type(keyword);
    }

    public void start_lookup() {
        allGithubLookup.click();
    }

    public boolean containsCertainRepository(String repositoryName) {
        return find(By.xpath ("//*[contains(text(),'"+ repositoryName+ "')]")).isPresent();
    }
}
