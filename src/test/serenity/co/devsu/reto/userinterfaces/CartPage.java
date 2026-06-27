package co.devsu.reto.userinterfaces;

import java.time.Duration;
import net.serenitybdd.screenplay.targets.Target;

public class CartPage {

    public static final Target CART_TITLE =
            Target.the("Título de la página Your Cart")
                    .locatedBy("//span[@data-test='title' and text()='Your Cart']")
                    .waitingForNoMoreThan(Duration.ofSeconds(15));

    public static final Target ALL_CART_PRODUCT_NAMES =
            Target.the("Todos los nombres de productos en el carrito")
                    .locatedBy("//div[@data-test='inventory-item-name']");

    public static final Target ALL_CART_PRODUCT_PRICES =
            Target.the("Todos los precios de productos en el carrito")
                    .locatedBy("//div[@data-test='inventory-item-price']");

    public static final Target CHECKOUT_BUTTON =
            Target.the("Botón Checkout")
                    .locatedBy("//button[@data-test='checkout' and @id='checkout']");
}
