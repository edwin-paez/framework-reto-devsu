package co.devsu.reto.tasks;

import co.devsu.reto.userinterfaces.CartPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.ensure.Ensure;

public class StartCheckout implements Task {

    public static StartCheckout now() {
        return new StartCheckout();
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Ensure.that(CartPage.CART_TITLE).isDisplayed(), Click.on(CartPage.CHECKOUT_BUTTON));
    }
}
