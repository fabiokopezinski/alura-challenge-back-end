package br.com.alura.challenge.back.feature;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = {"classpath:br/com/alura/challenge/back/feature/"},plugin = {"pretty"},extraGlue = {"br.com.alura.challenge.back.feature.start"})
public class RunCucumberTestIT {

    
}
