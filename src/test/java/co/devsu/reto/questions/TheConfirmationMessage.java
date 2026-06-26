package co.devsu.reto.questions;

import co.devsu.reto.userinterfaces.ConfirmationPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

public class TheConfirmationMessage implements Question<String> {

    public static TheConfirmationMessage text() {
        return new TheConfirmationMessage();
    }

    @Override
    public String answeredBy(Actor actor) {
        return ConfirmationPage.THANK_YOU_MESSAGE.resolveFor(actor).getText().trim().toUpperCase();
    }
}
