package co.devsu.reto.tasks;

import co.devsu.reto.userinterfaces.OrderSummaryPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;

public class ConfirmOrder implements Task {

    public static ConfirmOrder now() {
        return new ConfirmOrder();
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(Click.on(OrderSummaryPage.FINISH_BUTTON));
    }
}
