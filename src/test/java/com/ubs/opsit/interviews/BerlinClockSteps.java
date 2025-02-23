package com.ubs.opsit.interviews;

import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.List;

import static org.junit.Assert.*;

public class BerlinClockSteps {
    private static TimeConverter berlinClock;
    private String time;
    private String errorMessage;

    private IllegalArgumentException exception;

    @BeforeAll
    public static void before_or_after_all() {
        berlinClock = new BerlinClock();
    }

    @When("the time is {int}:{int}:{int}")
    public void the_time_is(Integer int1, Integer int2, Integer int3) {
        this.time = String.format("%02d:%02d:%02d", int1, int2, int3);
    }

    @Then("the clock should look like")
    public void the_clock_should_look_like(List<String> expectedClockResult) {
        String[] expectedRows = expectedClockResult.toArray(new String[0]);
        //try {
            String[] actualRows = berlinClock.convertTime(time).split("\n");
            for (int i = 0; i < expectedRows.length; i++) {
                assertEquals(expectedRows[i], actualRows[i]);
            }
//        } catch (IllegalArgumentException ex) {
//            exception = ex;
//        }
    }

    @When("the invalid time is null")
    public void the_time_is_null() {
       this.time = null;
    }

    @When("the time is {string}")
    public void the_time_is_invalid(String time) {
        this.time = time;
    }

    @When("the time is {double}.{int}")
    public void the_time_is(Double double1, Integer int1) {
        this.time = time;
    }

    @Then("the clock should throw an error {string}")
    public void the_clock_should_throw_an_error(String expectedErrorMessage) {
        assertThrows(expectedErrorMessage, IllegalArgumentException.class, () -> berlinClock.convertTime(time));
    }

}
