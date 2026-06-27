package co.devsu.reto.userinterfaces;

import java.time.Duration;
import net.serenitybdd.screenplay.targets.Target;

public class InventoryPage {

    public static final Target PRODUCTS_TITLE =
            Target.the("Título Products")
                    .locatedBy("//span[@data-test='title' and text()='Products']")
                    .waitingForNoMoreThan(Duration.ofSeconds(15));

    public static final Target ALL_PRODUCT_NAMES =
            Target.the("Todos los nombres de productos")
                    .locatedBy("//div[@data-test='inventory-item-name']");

    public static final Target SELECTED_PRODUCT_PRICE =
            Target.the("Precio del producto seleccionado")
                    .locatedBy(
                            "//div[@data-test='inventory-item-name' and text()='{0}']/ancestor::div[@data-test='inventory-item-description']//div[@data-test='inventory-item-price']");

    public static final Target ADD_TO_CART_SELECTED_PRODUCT =
            Target.the("Botón Add to Cart del producto seleccionado")
                    .locatedBy(
                            "//div[@data-test='inventory-item-name' and text()='{0}']/ancestor::div[@data-test='inventory-item-description']//button[contains(@data-test, 'add-to-cart')]");

    public static final Target SHOPPING_CART =
            Target.the("Carrito de compras").locatedBy("//a[@data-test='shopping-cart-link']");
}
