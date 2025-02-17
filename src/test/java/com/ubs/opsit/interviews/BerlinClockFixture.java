package com.ubs.opsit.interviews;

import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.ubs.opsit.interviews.support.BehaviouralTestEmbedder.aBehaviouralTestRunner;

/**
 * Acceptance test class that uses the JBehave (Gerkin) syntax for writing stories.  You should not need to
 * edit this class to complete the exercise, this is your definition of done.
 */
public class BerlinClockFixture {

    private TimeConverter berlinClock = new BerlinClock(); // Initialize BerlinClock
    private String theTime;

    @Test
    public void berlinClockAcceptanceTests() throws Exception {
        aBehaviouralTestRunner()
                .usingStepsFrom(this)
                .withStory("berlin-clock.story")  // The story file that contains scenarios
                .run();
    }

    @When("the time is $time")
    public void whenTheTimeIs(String time) {
        theTime = time;
    }

    @Then("the clock should look like $")
    public void thenTheClockShouldLookLike(String theExpectedBerlinClockOutput) {
        try {
            String result = berlinClock.convertTime(theTime);
            Assertions.assertEquals(theExpectedBerlinClockOutput, result);
        } catch (IllegalArgumentException e) {
            Assertions.assertEquals("Invalid time format", e.getMessage());
        }
    }

    @Then("the clock should throw an error $")
    public void thenTheClockShouldThrowAnError(String expectedError) {
        try {
            berlinClock.convertTime(theTime);
            Assertions.fail("Expected error not thrown");
        } catch (IllegalArgumentException e) {
            Assertions.assertEquals(expectedError, e.getMessage());
        }
    }
}
