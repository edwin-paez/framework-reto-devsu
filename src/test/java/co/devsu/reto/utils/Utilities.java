package co.devsu.reto.utils;

import java.util.Locale;

public class Utilities {

    public static String formatPrice(double price) {
        return "$" + String.format(Locale.US, "%.2f", price);
    }
}
