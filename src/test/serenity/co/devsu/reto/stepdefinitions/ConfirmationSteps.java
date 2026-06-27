package co.devsu.reto.stepdefinitions;

import co.devsu.reto.questions.TheConfirmationMessage;
import co.devsu.reto.userinterfaces.ConfirmationPage;
import io.cucumber.java.en.Then;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.ensure.Ensure;

public class ConfirmationSteps {
    @Then("el sistema muestra el mensaje de confirmación de compra")
    public void elSistemaMuestraElMensajeDeConfirmacion() {
        OnStage.theActorInTheSpotlight()
                .attemptsTo(
                        Ensure.that(ConfirmationPage.CHECKOUT_COMPLETE_TITLE).isDisplayed(),
                        Ensure.that(TheConfirmationMessage.text())
                                .isEqualTo("THANK YOU FOR YOUR ORDER!"),
                        Ensure.that(ConfirmationPage.THANK_YOU_MESSAGE).isDisplayed(),
                        Ensure.that(ConfirmationPage.COMPLETE_TEXT).isDisplayed(),
                        Ensure.that(ConfirmationPage.PONY_EXPRESS_IMAGE).isDisplayed());
    }
}
