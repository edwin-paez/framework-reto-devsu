package co.devsu.reto.factories;

import co.devsu.reto.models.CheckoutData;
import net.datafaker.Faker;

public class CheckoutDataFactory {

    public static CheckoutData withRandomData() {
        Faker faker = new Faker();
        return CheckoutData.builder()
                .firstName(faker.name().firstName())
                .lastName(faker.name().lastName())
                .postalCode(faker.address().zipCode())
                .build();
    }
}
