package co.devsu.reto.questions;

import co.devsu.reto.constants.ConstansKeys;
import co.devsu.reto.models.ProductData;
import co.devsu.reto.userinterfaces.OrderSummaryPage;
import co.devsu.reto.utils.Utilities;
import java.util.List;
import java.util.stream.Collectors;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import org.openqa.selenium.WebElement;

public class TheOrderSummary implements Question<Boolean> {
    public static TheOrderSummary isValid() {
        return new TheOrderSummary();
    }

    @Override
    public Boolean answeredBy(Actor actor) {
        List<ProductData> expected = actor.recall(ConstansKeys.PRODUCTS_KEY);
        if (!validateProducts(actor, expected)) return false;
        return validateTotals(actor, expected);
    }

    private boolean validateProducts(Actor actor, List<ProductData> expected) {
        List<String> overviewNames =
                OrderSummaryPage.ALL_OVERVIEW_PRODUCT_NAMES.resolveAllFor(actor).stream()
                        .map(WebElement::getText)
                        .collect(Collectors.toList());

        List<String> overviewPrices =
                OrderSummaryPage.ALL_OVERVIEW_PRODUCT_PRICES.resolveAllFor(actor).stream()
                        .map(WebElement::getText)
                        .collect(Collectors.toList());

        for (ProductData product : expected) {
            if (!overviewNames.contains(product.getName())) return false;
            String expectedPrice = Utilities.formatPrice(product.getPrice());
            if (overviewPrices.stream().noneMatch(p -> p.trim().equals(expectedPrice)))
                return false;
        }
        return true;
    }

    private boolean validateTotals(Actor actor, List<ProductData> expected) {
        double expectedSubtotal = expected.stream().mapToDouble(ProductData::getPrice).sum();

        String subtotalText = OrderSummaryPage.SUBTOTAL_VALUE.resolveFor(actor).getText();
        double displayedSubtotal = parsePrice(subtotalText);

        if (Math.abs(expectedSubtotal - displayedSubtotal) > 0.01) return false;

        String taxText = OrderSummaryPage.TAX_VALUE.resolveFor(actor).getText();
        double tax = parsePrice(taxText);
        if (tax <= 0) return false;

        String totalText = OrderSummaryPage.TOTAL_VALUE.resolveFor(actor).getText();
        double total = parsePrice(totalText);

        return Math.abs((displayedSubtotal + tax) - total) <= 0.01;
    }

    private double parsePrice(String text) {
        return Double.parseDouble(text.replaceAll("[^0-9.]", "").trim());
    }
}
