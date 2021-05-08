package ro.ubbcluj.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.By;

public class GithubRepoPage extends PageObject {

    @FindBy(xpath = "//summary[@class='btn btn-primary']")
    private WebElementFacade codeButton;

    public boolean is_button_available(){
        return codeButton.isPresent();
    }

    public void click_code_button(){
        codeButton.click();
    }

    public boolean is_link_available(String link){
        return find(By.className("btn btn-sm").xpath("//input[@value='" + link + "']")).isPresent();
    }

}
