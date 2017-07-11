package com.thefloow.model;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

/**
 * Created by samif on 11/07/2017.
 */
public class ContributorTest {

    private Contributor contributor;

    @Before
    public void setup(){
        contributor = Contributor.builder()
                                 .id("12")
                                 .ip("192.168.97.68")
                                 .username("spersontec")
                                 .build();
    }

    @Test
    public void checkGetters(){

        assertThat(contributor.getId(), is(equalTo("12")));
        assertThat(contributor.getIp(), is(equalTo("192.168.97.68")));
        assertThat(contributor.getUsername(), is(equalTo("spersontec")));
    }

    @Test
    public void checkSetters(){
        populateContributor();

        assertThat(contributor.getId(), is(equalTo("15")));
        assertThat(contributor.getIp(), is(equalTo("192.123.43.00")));
        assertThat(contributor.getUsername(), is(equalTo("whocares")));
    }

    private void populateContributor() {
        contributor.setId("15");
        contributor.setIp("192.123.43.00");
        contributor.setUsername("whocares");
    }
}