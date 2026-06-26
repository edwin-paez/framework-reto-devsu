package co.devsu.reto.stepdefinitions;

import co.devsu.reto.tasks.LoginUser;
import co.devsu.reto.userinterfaces.LoginPage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import java.util.List;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.ensure.Ensure;

public class LoginSteps {

    @Given("un usuario autenticado con credenciales:")
    public void unUsuarioAutenticadoConCredenciales(DataTable dataTable) {
        List<String> row = dataTable.values();
        String username = row.get(0);
        String password = row.get(1);
        OnStage.theActorInTheSpotlight()
                .attemptsTo(
                        Ensure.that(LoginPage.WELCOME_TITLE).isDisplayed(),
                        LoginUser.withCredentials(username, password));
    }
}
