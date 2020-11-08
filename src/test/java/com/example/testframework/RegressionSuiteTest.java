package com.example.testframework;

import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.IncludeTags;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
@IncludeTags("regression")
@SelectPackages("com.example.testframework")
public class RegressionSuiteTest {
}
