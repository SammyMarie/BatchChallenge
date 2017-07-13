package com.thefloow.model;

import org.junit.Before;
import org.junit.Test;

import static com.thefloow.model.TestUtils.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

/**
 * Created by samif on 11/07/2017.
 */
public class PageTest {

    private Page page;
    private Redirect redirect;
    private Revision revision;
    private Contributor contributor;

    @Before
    public void setup(){

        contributor = populateContributor();

        revision = populateSetupRevision(contributor);

        redirect = Redirect.builder()
                           .title("Some sweet acceptance")
                           .build();

        page = Page.builder()
                   .id("34")
                   .ns("8")
                   .title("Wonderful Challenge")
                   .revision(revision)
                   .redirect(redirect)
                   .build();
    }

    @Test
    public void checkGetters(){
        assertThat(page.getId(), is(equalTo("34")));
        assertThat(page.getNs(), is(equalTo("8")));
        assertThat(page.getRedirect(), is(equalTo(redirect)));
        assertThat(page.getRevision(), is(equalTo(revision)));
    }
}