import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.Random;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class FormTest extends TestBase {

    Random rnd = new Random();

    @Test
    public void shouldFillFormWithSuccess() {

        getDriver().get("https://seleniumui.moderntester.pl/form.php");
        getDriver().findElement(By.id("inputFirstName3")).sendKeys("Jan");
        getDriver().findElement(By.id("inputLastName3")).sendKeys("Kowalski");
        getDriver().findElement(By.id("inputEmail3")).sendKeys("jankowalski@sii.pl");
        getDriver().findElement(By.id("inputAge3")).sendKeys("33");
        List<WebElement> gridRadiosSex= getDriver().findElements(By.cssSelector("[name='gridRadiosSex']"));
        gridRadiosSex.get(0).click();
        List<WebElement> gridRadioExperience = getDriver().findElements(By.cssSelector("[name='gridRadioExperience']"));
        int index = rnd.nextInt(gridRadioExperience.size());
        if (!gridRadioExperience.get(index).isSelected()) {
            gridRadioExperience.get(index).click();
        }





        Select continents = new Select(getDriver().findElement(By.id("selectContinents")));
        continents.selectByValue("europe");

//        .form-group [name='gridRadiosExperience']

        //this should be at the end of test
        WebElement msg = getDriver().findElement(By.id("validator-message"));
        assertThat(msg.getText(), equalTo("Form send with success"));

    }


}
