package co.devsu.reto.karate;

import com.intuit.karate.junit5.Karate;

class KarateRunner {

    @Karate.Test
    Karate testAll() {
        return Karate.run("classpath:features/api/users")
                .outputCucumberJson(true)
                .outputHtmlReport(true)
                .relativeTo(getClass());
    }
}
