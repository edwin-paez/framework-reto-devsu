package co.devsu.reto.interactions;

import co.devsu.reto.constants.ConstantsKeys;
import co.devsu.reto.models.ProductData;
import co.devsu.reto.userinterfaces.InventoryPage;
import java.util.ArrayList;
import java.util.List;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.actions.Click;

public class AddProductToCart implements Interaction {

    private final String productName;

    public AddProductToCart(String productName) {
        this.productName = productName;
    }

    public static AddProductToCart forProduct(String productName) {
        return new AddProductToCart(productName);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        String priceText =
                InventoryPage.SELECTED_PRODUCT_PRICE.of(productName).resolveFor(actor).getText();
        double price = Double.parseDouble(priceText.replace("$", "").trim());
        ProductData product = ProductData.builder().name(productName).price(price).build();

        List<ProductData> products = actor.recall(ConstantsKeys.PRODUCTS_KEY);
        if (products == null) products = new ArrayList<>();
        products.add(product);
        actor.remember(ConstantsKeys.PRODUCTS_KEY, products);

        actor.attemptsTo(Click.on(InventoryPage.ADD_TO_CART_SELECTED_PRODUCT.of(productName)));
    }
}
