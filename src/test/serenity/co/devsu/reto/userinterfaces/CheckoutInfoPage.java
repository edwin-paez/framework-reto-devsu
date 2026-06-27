package co.devsu.reto.userinterfaces;

import java.time.Duration;
import net.serenitybdd.screenplay.targets.Target;

public class CheckoutInfoPage {

    public static final Target CHECKOUT_INFO_TITLE =
            Target.the("Título de la página Checkout: Your Information")
                    .locatedBy("//span[@data-test='title' and text()='Checkout: Your Information']")
                    .waitingForNoMoreThan(Duration.ofSeconds(15));

    public static final Target FIRST_NAME_INPUT =
            Target.the("Campo First Name")
                    .locatedBy("//input[@data-test='firstName' and @id='first-name']");

    public static final Target LAST_NAME_INPUT =
            Target.the("Campo Last Name")
                    .locatedBy("//input[@data-test='lastName' and @id='last-name']");

    public static final Target POSTAL_CODE_INPUT =
            Target.the("Campo Zip/Postal Code")
                    .locatedBy("//input[@data-test='postalCode' and @id='postal-code']");

    public static final Target CONTINUE_BUTTON =
            Target.the("Botón Continue")
                    .locatedBy(
                            "//input[@data-test='continue' and @id='continue' and @value='Continue']");
}
