package co.devsu.reto.tasks;

import co.devsu.reto.models.CheckoutData;
import co.devsu.reto.userinterfaces.CheckoutInfoPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;

public class FillCheckoutInfo implements Task {

    private final CheckoutData checkoutData;

    public FillCheckoutInfo(CheckoutData checkoutData) {
        this.checkoutData = checkoutData;
    }

    public static FillCheckoutInfo withData(CheckoutData checkoutData) {
        return new FillCheckoutInfo(checkoutData);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Enter.theValue(checkoutData.getFirstName()).into(CheckoutInfoPage.FIRST_NAME_INPUT),
                Enter.theValue(checkoutData.getLastName()).into(CheckoutInfoPage.LAST_NAME_INPUT),
                Enter.theValue(checkoutData.getPostalCode())
                        .into(CheckoutInfoPage.POSTAL_CODE_INPUT),
                Click.on(CheckoutInfoPage.CONTINUE_BUTTON));
    }
}
