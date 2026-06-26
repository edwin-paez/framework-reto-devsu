package co.devsu.reto.stepdefinitions;

import co.devsu.reto.tasks.SelectRandomProducts;
import co.devsu.reto.userinterfaces.InventoryPage;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.ensure.Ensure;

public class ProductSteps {

    @When("agrega {int} productos al carrito")
    public void agregaDoProductosAlCarrito(int productCount) {
        OnStage.theActorInTheSpotlight()
                .attemptsTo(
                        Ensure.that(InventoryPage.PRODUCTS_TITLE).isDisplayed(),
                        SelectRandomProducts.withCount(productCount));
    }
}
