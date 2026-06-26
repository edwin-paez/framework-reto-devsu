package co.devsu.reto.interactions;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.targets.Target;
import net.serenitybdd.screenplay.waits.WaitUntil;

public class WaitForElement implements Interaction {

    private final Target target;
    private final int seconds;

    public WaitForElement(Target target, int seconds) {
        this.target = target;
        this.seconds = seconds;
    }

    public static WaitForElement visible(Target target, int seconds) {
        return net.serenitybdd.screenplay.Tasks.instrumented(WaitForElement.class, target, seconds);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(WaitUntil.the(target, isVisible()).forNoMoreThan(seconds).seconds());
    }
}
