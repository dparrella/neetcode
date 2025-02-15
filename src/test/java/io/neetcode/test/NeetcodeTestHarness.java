package io.neetcode.test;

import com.fasterxml.jackson.jr.ob.JSON;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

/**
 * Helper to run test cases as displayed in Neetcode.
 * <br/>
 * For example:
 * <br/>
 * Test steps: {@code ["insertTail", 1, "insertTail", 2, "get", 1, "remove", 1, "insertTail", 2, "get", 1, "get", 0]}
 * <br/>
 * Test expectations: {@code [null,null,2,true,null,2,1]}
 */
public class NeetcodeTestHarness {

    private static final NeetcodeTestHarness INSTANCE = new NeetcodeTestHarness();

    private NeetcodeTestHarness() {
    }

    public static NeetcodeTestHarness getInstance() {
        return INSTANCE;
    }

    public void runTest(final Object target,
                        final String testScript,
                        final String testExpectations) throws IOException {
        final List<Object> parsedTestScript = parseList(testScript);
        final List<Object> parsedTestExpectations = parseList(testExpectations);

        final int expectedExpectationsSize = parsedTestScript.size() / 2;
        if (parsedTestExpectations.size() != expectedExpectationsSize) {
            throw new IllegalArgumentException("Expected "
                    + expectedExpectationsSize
                    + " test expectations but got: "
                    + parsedTestExpectations.size());
        }

        final Iterator<Object> testScriptIterator = parsedTestScript.iterator();
        final Iterator<Object> testExpectationsIterator = parsedTestExpectations.iterator();
        while (testScriptIterator.hasNext() && testExpectationsIterator.hasNext()) {
            final Object methodName = testScriptIterator.next();
            if (!(methodName instanceof String)) {
                throw new IllegalArgumentException("Expected methodName to be a String but got: " + methodName.getClass());
            }

            if (!testScriptIterator.hasNext()) {
                throw new IllegalArgumentException("Method has no arguments.");
            }

            final Object methodArgument = testScriptIterator.next();
            final Object result = invokeMethod(target, (String) methodName, methodArgument);

            final Object expectedResult = testExpectationsIterator.next();
            assertThat(methodName + " invoked with argument " + methodArgument + " did not return expected value",
                    result, is(expectedResult));
        }
    }

    private List<Object> parseList(final String json) throws IOException {
        return JSON.std.listFrom(json);
    }

    private Object invokeMethod(final Object target, final String methodName, final Object argument) {
        final Class<?> targetClazz = target.getClass();
        final Optional<Method> method = Arrays.stream(targetClazz.getMethods())
                .filter(m -> m.getName().equals(methodName))
                .findFirst();

        try {
            method.get().setAccessible(true);
            return method.orElseThrow().invoke(target, argument);
        } catch (IllegalAccessException | InvocationTargetException ex) {
            throw new IllegalStateException("Failed to invoke method " + methodName + " with argument " + argument, ex);
        }
    }
}
