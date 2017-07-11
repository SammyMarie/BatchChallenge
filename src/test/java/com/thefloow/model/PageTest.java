package com.thefloow.model;

import org.junit.Before;

/**
 * Created by samif on 11/07/2017.
 */
public class PageTest {

    private Page page;

    @Before
    public void setup(){
        page = Page.builder()
                   .id("34")
                   .ns("8")
                   .title("Wonderful Challenge")
                   .build();
    }
}