package co.devsu.reto.questions;

import co.devsu.reto.constants.ConstantsKeys;
import co.devsu.reto.models.ProductData;
import co.devsu.reto.userinterfaces.CartPage;
import co.devsu.reto.utils.Utilities;
import java.util.List;
import java.util.stream.Collectors;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import org.openqa.selenium.WebElement;

public class TheCartProducts implements Question<Boolean> {

    public static TheCartProducts areCorrect() {
        return new TheCartProducts();
    }

    @Override
    public Boolean answeredBy(Actor actor) {
        List<ProductData> expected = actor.recall(ConstantsKeys.PRODUCTS_KEY);
        return validateProducts(actor, expected);
    }

    private boolean validateProducts(Actor actor, List<ProductData> expected) {
        List<String> cartNames = getCartNames(actor);
        List<String> cartPrices = getCartPrices(actor);

        if (expected.size() != cartNames.size()) return false;

        for (ProductData product : expected) {
            if (!cartNames.contains(product.getName())) return false;
            String expectedPrice = Utilities.formatPrice(product.getPrice());
            if (cartPrices.stream().noneMatch(p -> p.trim().equals(expectedPrice))) return false;
        }
        return true;
    }

    private List<String> getCartNames(Actor actor) {
        return CartPage.ALL_CART_PRODUCT_NAMES.resolveAllFor(actor).stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    private List<String> getCartPrices(Actor actor) {
        return CartPage.ALL_CART_PRODUCT_PRICES.resolveAllFor(actor).stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }
}
