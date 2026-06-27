package co.devsu.reto.hooks;

import co.devsu.reto.utils.UrlResolver;
import io.cucumber.java.Before;
import net.serenitybdd.core.environment.WebDriverConfiguredEnvironment;
import net.serenitybdd.screenplay.actions.Open;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.thucydides.model.util.EnvironmentVariables;

public class SetStageHook {

    @Before(order = 0)
    public void setTheStage() {
        OnStage.setTheStage(new OnlineCast());
        EnvironmentVariables environmentVariables =
                WebDriverConfiguredEnvironment.getEnvironmentVariables();
        String baseUrl = UrlResolver.baseUrl(environmentVariables);
        OnStage.theActorCalled("User").attemptsTo(Open.url(baseUrl));
    }
}
