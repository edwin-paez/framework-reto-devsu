package co.devsu.reto.tasks;

import co.devsu.reto.userinterfaces.InventoryPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;

public class NavigateToCart implements Task {

    public static NavigateToCart now() {
        return new NavigateToCart();
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(Click.on(InventoryPage.SHOPPING_CART));
    }
}
