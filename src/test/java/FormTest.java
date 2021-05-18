import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Random;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class FormTest extends TestBase {

    private Logger logger = LoggerFactory.getLogger("FormTest.class");

    Random random = new Random();
    String url;
    String firstName;
    String lastName;
    String email;
    String age;
    String continent;
    int seleniumCommandIndex;
    String filePath;

    @Test
    public void shouldFillFormWithSuccess() {
        url = "https://seleniumui.moderntester.pl/form.php";
        getDriver().get(url);
        logger.info("Webpage url: " + url);

        firstName = "Jan";
        lastName = "Kowalski";
        getDriver().findElement(By.id("inputFirstName3")).sendKeys(firstName);
        getDriver().findElement(By.id("inputLastName3")).sendKeys(lastName);
        logger.debug("First name is: {} and last name is: {}", firstName, lastName);

        email = "jankowalski@sii.pl";
        getDriver().findElement(By.id("inputEmail3")).sendKeys(email);
        logger.debug("Email is: {}", email);

        getDriver().findElements(By.cssSelector("[name='gridRadiosSex']")).get(0).click();
        logger.debug("Radio button properly clicked");
        age = "33";
        getDriver().findElement(By.id("inputAge3")).sendKeys(age);
        logger.debug("Age is: {}", age);

        List<WebElement> gridRadioExperience = getDriver().
                findElements(By.cssSelector(".form-group [name='gridRadiosExperience']"));
        logger.debug("Number of radios: " + gridRadioExperience.size());
        int index = random.nextInt(gridRadioExperience.size());
        if (!gridRadioExperience.get(index).isSelected()) {
            gridRadioExperience.get(index).click();
        }
        logger.debug("Random index of radio: " + index);

        getDriver().findElements(By.xpath("//*[@name='gridCheckboxProfession']")).get(0).click();
        logger.debug("Checkbox button properly clicked");

        continent = "europe";
        Select continents = new Select(getDriver().findElement(By.id("selectContinents")));
        continents.selectByValue(continent);
        logger.debug("Selected continent: " + continent);

        seleniumCommandIndex = 4;
        Select selectSeleniumCommands = new Select(getDriver().findElement(By.id("selectSeleniumCommands")));
        selectSeleniumCommands.selectByIndex(seleniumCommandIndex);
        logger.debug("Selected value: " + seleniumCommandIndex + ", options: " + selectSeleniumCommands.getOptions());

        filePath = System.getProperty("user.dir")+"/src/main/resources/test.txt";
        getDriver().findElement(By.cssSelector("#chooseFile")).sendKeys(filePath);
        logger.debug("File path is :" + filePath);

        getDriver().findElement(By.cssSelector(".btn-primary")).click();
        logger.debug("Button properly clicked");

        //this should be at the end of test
        WebElement msg = getDriver().findElement(By.id("validator-message"));
        logger.debug("Message: " + msg.getText());
        assertThat(msg.getText(), equalTo("Form send with success"));
    }

}
