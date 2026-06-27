package co.devsu.reto.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class CheckoutData {
    private String firstName;
    private String lastName;
    private String postalCode;
}
