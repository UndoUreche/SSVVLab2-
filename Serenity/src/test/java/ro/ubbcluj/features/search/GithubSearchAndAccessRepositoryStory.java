package ro.ubbcluj.features.search;

import net.serenitybdd.junit.runners.SerenityParameterizedRunner;
import net.thucydides.core.annotations.Issue;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import net.thucydides.junit.annotations.Qualifier;
import net.thucydides.junit.annotations.UseTestDataFrom;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import ro.ubbcluj.steps.serenity.EndUserStepsSearch;

@RunWith(SerenityParameterizedRunner.class)
@UseTestDataFrom("src/test/githubSearchAndAccess.csv")
public class GithubSearchAndAccessRepositoryStory {

    @Managed(uniqueSession = true)
    public WebDriver webdriver;

    public String keyword;
    public String repoName;
    public String isValid;

    @Qualifier
    public String getQualifier(){
        return keyword;
    }

    @Steps
    public EndUserStepsSearch endUser;

    @Issue("GITHUB-1")
    @Test
    public void searchGithubRepoByKeyword(){
        endUser.is_the_home_page();
        endUser.looks_for(getKeyword());
        if(Boolean.parseBoolean(getIsValid())){
            endUser.should_see_repo(getRepoName());
        }
        else{
            endUser.should_not_see_repo(getRepoName());
        }
    }

    public WebDriver getWebdriver() {
        return webdriver;
    }

    public void setWebdriver(WebDriver webdriver) {
        this.webdriver = webdriver;
    }

    public EndUserStepsSearch getEndUser() {
        return endUser;
    }

    public void setEndUser(EndUserStepsSearch endUser) {
        this.endUser = endUser;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getRepoName() {
        return repoName;
    }

    public void setRepoName(String repoName) {
        this.repoName = repoName;
    }

    public String getIsValid() {
        return isValid;
    }

    public void setIsValid(String isValid) {
        this.isValid = isValid;
    }
}
