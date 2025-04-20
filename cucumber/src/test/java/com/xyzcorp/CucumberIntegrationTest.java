package com.xyzcorp;

import org.junit.platform.suite.api.*;

import static io.cucumber.junit.platform.engine.Constants.GLUE_PROPERTY_NAME;
@Suite
@IncludeEngines("cucumber")
@SelectPackages("com.xyzcorp")
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "com.xyzcorp")
public class CucumberIntegrationTest {
}
