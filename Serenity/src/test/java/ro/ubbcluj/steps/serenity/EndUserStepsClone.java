package ro.ubbcluj.steps.serenity;


import net.thucydides.core.annotations.Step;
import ro.ubbcluj.pages.GithubRepoPage;

public class EndUserStepsClone {

    GithubRepoPage githubRepoPage;

    @Step
    public void clicks_code(){
        githubRepoPage.click_code_button();
    }

    @Step
    public boolean is_link_available(String repoLink){
        return githubRepoPage.is_link_available(repoLink);
    }

    public boolean seesTheCloneButton() {
        return githubRepoPage.is_button_available();
    }

    public void setRepoPage(String repoUrl) {
        githubRepoPage.setDefaultBaseUrl(repoUrl);
        githubRepoPage.open();
    }
}
