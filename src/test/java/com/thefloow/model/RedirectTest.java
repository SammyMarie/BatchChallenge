package com.thefloow.model;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

/**
 * Created by samif on 11/07/2017.
 */
public class RedirectTest {

    private Redirect redirect;

    @Before
    public void setup(){
        redirect = Redirect.builder()
                           .title("Sing for Me!!!")
                           .build();

    }

    @Test
    public void checkGetter(){
        assertThat(redirect.getTitle(), is(equalTo("Sing for Me!!!")));
    }

    @Test
    public void checkSetter(){
        redirect.setTitle("Who this be now?");

        assertThat(redirect.getTitle(), is(equalTo("Who this be now?")));
    }
}