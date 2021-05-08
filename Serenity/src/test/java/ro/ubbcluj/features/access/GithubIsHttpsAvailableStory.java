package ro.ubbcluj.features.access;

import net.serenitybdd.junit.runners.SerenityParameterizedRunner;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Issue;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import net.thucydides.junit.annotations.UseTestDataFrom;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import ro.ubbcluj.steps.serenity.EndUserStepsClone;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(SerenityParameterizedRunner.class)
@UseTestDataFrom("src/test/githubIsHttpsAvailable.csv")
public class GithubIsHttpsAvailableStory {

    @Managed(uniqueSession = true)
    public WebDriver webdriver;

    @Steps
    public EndUserStepsClone endUser;

    public String repoPage;
    public String isValid;

    @Issue("GITHUB-2")
    @Test
    public void isHttpsCodeVisible() {
        endUser.setRepoPage(getRepoPage());
        if (Boolean.parseBoolean(getIsValid())) {
            assertTrue(endUser.seesTheCloneButton());
            endUser.clicks_code();
            assertTrue(endUser.is_link_available(getRepoPage() + ".git"));
        }
        else{
            assertFalse(endUser.seesTheCloneButton());
        }
    }

    public WebDriver getWebdriver() {
        return webdriver;
    }

    public void setWebdriver(WebDriver webdriver) {
        this.webdriver = webdriver;
    }

    public EndUserStepsClone getEndUser() {
        return endUser;
    }

    public void setEndUser(EndUserStepsClone endUser) {
        this.endUser = endUser;
    }

    public String getRepoPage() {
        return repoPage;
    }

    public void setRepoPage(String repoPage) {
        this.repoPage = repoPage;
    }

    public String getIsValid() {
        return isValid;
    }

    public void setIsValid(String isValid) {
        this.isValid = isValid;
    }
}
