package ro.ubbcluj.steps.serenity;

import net.thucydides.core.annotations.Step;
import ro.ubbcluj.pages.GithubPage;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class EndUserStepsSearch {

    GithubPage githubPage;

    @Step
    public void enters(String keyword) {
        githubPage.enter_keywords(keyword);
    }

    @Step
    public void starts_search(){
        githubPage.start_lookup();
    }

    @Step
    public void should_see_repo(String repoName){
        assertTrue(githubPage.containsCertainRepository(repoName));
    }

    public void is_the_home_page() {
        githubPage.open();
    }

    @Step
    public void looks_for(String term) {
        enters(term);
        starts_search();
    }

    public void should_not_see_repo(String repoName) {
        assertFalse(githubPage.containsCertainRepository(repoName));
    }
}
