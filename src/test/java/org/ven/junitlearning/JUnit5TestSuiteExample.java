package org.ven.junitlearning;

import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
@SelectClasses({TestBusinessLogic.class, TestJUnit4.class})
public class JUnit5TestSuiteExample
{
}