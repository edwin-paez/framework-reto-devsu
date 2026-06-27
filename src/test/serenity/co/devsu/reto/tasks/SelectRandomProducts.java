package co.devsu.reto.tasks;

import co.devsu.reto.interactions.AddProductToCart;
import co.devsu.reto.userinterfaces.InventoryPage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import org.openqa.selenium.WebElement;

public class SelectRandomProducts implements Task {

    private final int count;

    public SelectRandomProducts(int count) {
        this.count = count;
    }

    public static SelectRandomProducts withCount(int count) {
        return new SelectRandomProducts(count);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        List<String> allNames =
                InventoryPage.ALL_PRODUCT_NAMES.resolveAllFor(actor).stream()
                        .map(WebElement::getText)
                        .collect(Collectors.toList());
        Collections.shuffle(allNames);
        List<String> selected = new ArrayList<>(allNames.subList(0, count));

        for (String name : selected) {
            actor.attemptsTo(AddProductToCart.forProduct(name));
        }
    }
}
