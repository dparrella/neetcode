package io.neetcode;

import org.testng.annotations.Test;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;

public class BrowserHistoryTest {

    @Test
    public void failingScenario() {
        final BrowserHistory history = new BrowserHistory("leetcode.com");
        history.visit("google.com");
        history.visit("facebook.com");
        history.visit("youtube.com");

        assertThat(history.back(1), is("facebook.com"));
        assertThat(history.back(1), is("google.com"));
        assertThat(history.forward(1), is("facebook.com"));
    }
}