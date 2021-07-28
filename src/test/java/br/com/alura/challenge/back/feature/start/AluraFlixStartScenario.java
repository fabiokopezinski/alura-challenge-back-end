package br.com.alura.challenge.back.feature.start;

import org.springframework.boot.test.context.SpringBootTest;

import br.com.alura.challenge.back.feature.AbstractSteps;
import io.cucumber.core.api.Scenario;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AluraFlixStartScenario extends AbstractSteps {

    
    
    @Before("@save")
    public void before(Scenario scenario) {

        if (testContext().get(scenario.getId()) == null) {
            testContext().set(scenario.getId(), null);
            try {

                log.info("Init - beforeEverthing");
                log.info("End - beforeEverthing");

            } catch (Exception e) {
                log.error("error={}", e.getCause());
            }
        }

    }

    @After("@delete")
    public void after(Scenario scenario) {

        try {
            log.info("Init - after");
            log.info("End - after");

        } catch (Exception e) {
            log.error("erro={}", e.getCause());
        }

    }

}
