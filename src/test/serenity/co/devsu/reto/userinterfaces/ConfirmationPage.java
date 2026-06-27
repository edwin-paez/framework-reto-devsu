package co.devsu.reto.userinterfaces;

import java.time.Duration;
import net.serenitybdd.screenplay.targets.Target;

public class ConfirmationPage {

    public static final Target CHECKOUT_COMPLETE_TITLE =
            Target.the("Título de la página Checkout: Complete!")
                    .locatedBy("//span[@data-test='title' and text()='Checkout: Complete!']")
                    .waitingForNoMoreThan(Duration.ofSeconds(15));

    public static final Target THANK_YOU_MESSAGE =
            Target.the("Mensaje Thank you for your order!")
                    .locatedBy(
                            "//h2[@data-test='complete-header' and text()='Thank you for your order!']");

    public static final Target COMPLETE_TEXT =
            Target.the("Texto de confirmación de compra completa")
                    .locatedBy("//div[@data-test='complete-text']");

    public static final Target PONY_EXPRESS_IMAGE =
            Target.the("Imagen Pony Express").locatedBy("//img[@data-test='pony-express']");
}
