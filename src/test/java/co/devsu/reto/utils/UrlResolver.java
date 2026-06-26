package co.devsu.reto.utils;

import static co.devsu.reto.constants.ConstansKeys.BASE_URL_KEY;

import net.serenitybdd.model.environment.EnvironmentSpecificConfiguration;
import net.thucydides.model.util.EnvironmentVariables;

public final class UrlResolver {

    private UrlResolver() {}

    public static String baseUrl(EnvironmentVariables environmentVariables) {
        String baseUrl =
                EnvironmentSpecificConfiguration.from(environmentVariables)
                        .getOptionalProperty(BASE_URL_KEY)
                        .orElse(null);

        if (isBlank(baseUrl)) {
            baseUrl = System.getProperty(BASE_URL_KEY);
        }

        if (isBlank(baseUrl)) {
            baseUrl = System.getenv("WEBDRIVER_BASE_URL");
        }

        if (isBlank(baseUrl)) {
            throw new IllegalStateException(
                    "No se encontro webdriver.base.url. Usa -Denvironment=stg|dev, "
                            + "-Dwebdriver.base.url=... o WEBDRIVER_BASE_URL=...");
        }

        return baseUrl;
    }

    private static boolean isBlank(String value) {
        return value == null || value.isBlank();
    }
}
