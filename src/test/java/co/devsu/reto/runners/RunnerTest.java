package co.devsu.reto.runners;

import static io.cucumber.junit.platform.engine.Constants.FILTER_TAGS_PROPERTY_NAME;
import static io.cucumber.junit.platform.engine.Constants.GLUE_PROPERTY_NAME;
import static io.cucumber.junit.platform.engine.Constants.PLUGIN_PROPERTY_NAME;

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("features")
@ConfigurationParameter(
        key = GLUE_PROPERTY_NAME,
        value = "co.devsu.reto.stepdefinitions, co.devsu.reto.hooks")
@ConfigurationParameter(
        key = PLUGIN_PROPERTY_NAME,
        value = "pretty, io.cucumber.core.plugin.SerenityReporterParallel")
@ConfigurationParameter(key = FILTER_TAGS_PROPERTY_NAME, value = "@Smoke")
public class RunnerTest {}
