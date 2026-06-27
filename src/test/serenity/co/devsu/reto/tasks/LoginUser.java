package co.devsu.reto.tasks;

import co.devsu.reto.userinterfaces.LoginPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;

public class LoginUser implements Task {

    private final String username;
    private final String password;

    public LoginUser(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public static LoginUser withCredentials(String username, String password) {
        return new LoginUser(username, password);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Enter.theValue(username).into(LoginPage.USERNAME_INPUT),
                Enter.theValue(password).into(LoginPage.PASSWORD_INPUT),
                Click.on(LoginPage.LOGIN_BUTTON));
    }
}
