package com.mostafa.bdd.cashmachine;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features =  "classpath:com/mostafa/bdd/cashmachine/features")
public class RunCucumberTest {
}
