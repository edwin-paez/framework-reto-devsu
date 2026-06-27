package co.devsu.reto.userinterfaces;

import java.time.Duration;
import net.serenitybdd.screenplay.targets.Target;

public class OrderSummaryPage {

    public static final Target CHECKOUT_OVERVIEW_TITLE =
            Target.the("Título de la página Checkout: Overview")
                    .locatedBy("//span[@data-test='title' and text()='Checkout: Overview']")
                    .waitingForNoMoreThan(Duration.ofSeconds(15));

    public static final Target ALL_OVERVIEW_PRODUCT_NAMES =
            Target.the("Todos los nombres de productos en el resumen")
                    .locatedBy("//div[@data-test='inventory-item-name']");

    public static final Target ALL_OVERVIEW_PRODUCT_PRICES =
            Target.the("Todos los precios de productos en el resumen")
                    .locatedBy("//div[@data-test='inventory-item-price']");

    public static final Target ALL_OVERVIEW_PRODUCT_QUANTITIES =
            Target.the("Todas las cantidades de productos en el resumen")
                    .locatedBy("//div[@data-test='item-quantity']");

    public static final Target OVERVIEW_PRODUCT_NAME =
            Target.the("Nombre del producto en el resumen")
                    .locatedBy("//div[@data-test='inventory-item-name' and text()='{0}']");

    public static final Target OVERVIEW_PRODUCT_PRICE =
            Target.the("Precio del producto en el resumen")
                    .locatedBy(
                            "//div[@data-test='inventory-item-name' and text()='{0}']/ancestor::div[@class='cart_item']//div[@data-test='inventory-item-price']");

    public static final Target OVERVIEW_PRODUCT_QUANTITY =
            Target.the("Cantidad del producto en el resumen")
                    .locatedBy(
                            "//div[@data-test='inventory-item-name' and text()='{0}']/ancestor::div[@class='cart_item']//div[@data-test='item-quantity']");

    public static final Target SUBTOTAL_LABEL =
            Target.the("Etiqueta Item total").locatedBy("//div[@data-test='subtotal-label']");

    public static final Target SUBTOTAL_VALUE =
            Target.the("Valor de Item total").locatedBy("//div[@data-test='subtotal-label']");

    public static final Target TAX_LABEL =
            Target.the("Etiqueta Tax").locatedBy("//div[@data-test='tax-label']");

    public static final Target TAX_VALUE =
            Target.the("Valor de Tax").locatedBy("//div[@data-test='tax-label']");

    public static final Target TOTAL_LABEL =
            Target.the("Etiqueta Total").locatedBy("//div[@data-test='total-label']");

    public static final Target TOTAL_VALUE =
            Target.the("Valor Total").locatedBy("//div[@data-test='total-label']");

    public static final Target FINISH_BUTTON =
            Target.the("Botón Finish").locatedBy("//button[@data-test='finish' and @id='finish']");
}
