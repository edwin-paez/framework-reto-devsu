package co.devsu.reto.interactions;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

import net.serenitybdd.core.steps.Instrumented;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.targets.Target;
import net.serenitybdd.screenplay.waits.WaitUntil;

public class ClickOnElement implements Interaction {

    private final Target target;
    private final int seconds;

    public ClickOnElement(Target target, int seconds) {
        this.target = target;
        this.seconds = seconds;
    }

    public static ClickOnElement of(Target target, int seconds) {
        return Instrumented.instanceOf(ClickOnElement.class).withProperties(target, seconds);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {

        actor.attemptsTo(
                WaitUntil.the(target, isVisible()).forNoMoreThan(seconds).seconds(),
                Click.on(target));
    }
}
