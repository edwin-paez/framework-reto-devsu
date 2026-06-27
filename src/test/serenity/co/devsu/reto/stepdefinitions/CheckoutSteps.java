package co.devsu.reto.stepdefinitions;

import co.devsu.reto.factories.CheckoutDataFactory;
import co.devsu.reto.questions.TheCartProducts;
import co.devsu.reto.questions.TheOrderSummary;
import co.devsu.reto.tasks.ConfirmOrder;
import co.devsu.reto.tasks.FillCheckoutInfo;
import co.devsu.reto.tasks.NavigateToCart;
import co.devsu.reto.tasks.StartCheckout;
import co.devsu.reto.userinterfaces.CheckoutInfoPage;
import co.devsu.reto.userinterfaces.OrderSummaryPage;
import io.cucumber.java.en.And;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.ensure.Ensure;

public class CheckoutSteps {

    @And("inicia el proceso de checkout")
    public void iniciaElProcesoDeCheckout() {
        OnStage.theActorInTheSpotlight()
                .attemptsTo(
                        NavigateToCart.now(),
                        Ensure.that(TheCartProducts.areCorrect()).isTrue(),
                        StartCheckout.now(),
                        Ensure.that(CheckoutInfoPage.CHECKOUT_INFO_TITLE).isDisplayed(),
                        FillCheckoutInfo.withData(CheckoutDataFactory.withRandomData()));
    }

    @And("completa la compra")
    public void completaLaCompra() {
        OnStage.theActorInTheSpotlight()
                .attemptsTo(
                        Ensure.that(OrderSummaryPage.CHECKOUT_OVERVIEW_TITLE).isDisplayed(),
                        Ensure.that(TheOrderSummary.isValid()).isTrue(),
                        ConfirmOrder.now());
    }
}
