import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.Random;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class FormTest extends TestBase {

    Random random = new Random();

    @Test
    public void shouldFillFormWithSuccess() {

        getDriver().get("https://seleniumui.moderntester.pl/form.php");
        getDriver().findElement(By.id("inputFirstName3")).sendKeys("Jan");
        getDriver().findElement(By.id("inputLastName3")).sendKeys("Kowalski");
        getDriver().findElement(By.id("inputEmail3")).sendKeys("jankowalski@sii.pl");
        getDriver().findElements(By.cssSelector("[name='gridRadiosSex']")).get(0).click();
        getDriver().findElement(By.id("inputAge3")).sendKeys("33");
        List<WebElement> gridRadioExperience = getDriver().findElements(By.cssSelector(".form-group [name='gridRadiosExperience']"));
        int index = random.nextInt(gridRadioExperience.size());
        if (!gridRadioExperience.get(index).isSelected()) {
            gridRadioExperience.get(index).click();
        }

        getDriver().findElements(By.xpath("//*[@name='gridCheckboxProfession']")).get(0).click();

        Select continents = new Select(getDriver().findElement(By.id("selectContinents")));
        continents.selectByValue("europe");
        Select selectSeleniumCommands = new Select(getDriver().findElement(By.id("selectSeleniumCommands")));
        selectSeleniumCommands.selectByValue("wait-commands");
        
        getDriver().findElement(By.cssSelector("#chooseFile")).sendKeys(System.getProperty("user.dir")+"/src/main/resources/test.txt");

        getDriver().findElement(By.cssSelector(".btn-primary")).click();
        //this should be at the end of test
        WebElement msg = getDriver().findElement(By.id("validator-message"));
        assertThat(msg.getText(), equalTo("Form send with success"));
    }

}
