package co.devsu.reto.userinterfaces;

import java.time.Duration;
import net.serenitybdd.screenplay.targets.Target;

public class LoginPage {

    public static final Target WELCOME_TITLE =
            Target.the("Título de bienvenida Swag Labs")
                    .locatedBy("//div[contains(@class, 'login_logo') and text()='Swag Labs']")
                    .waitingForNoMoreThan(Duration.ofSeconds(15));

    public static final Target USERNAME_INPUT =
            Target.the("Campo de Username").locatedBy("//input[@id='user-name']");

    public static final Target PASSWORD_INPUT =
            Target.the("Campo de Password").locatedBy("//input[@id='password']");

    public static final Target LOGIN_BUTTON =
            Target.the("Botón Login").locatedBy("//input[@id='login-button']");
}
