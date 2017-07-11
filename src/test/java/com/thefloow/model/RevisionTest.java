package com.thefloow.model;

import org.junit.Before;
import org.junit.Test;

import java.util.Collections;

import static com.thefloow.model.TestUtils.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by samif on 11/07/2017.
 */
public class RevisionTest {

    private Revision revision;
    private Contributor contributor;

    @Before
    public void setup(){

        contributor = populateContributor();

        revision = populateSetupRevision(contributor);
    }

    @Test
    public void checkGetters(){
        String text = "I would love to have finished but, too much has happened in my life right now." +
                " And, I've learnt this while doing it as it's my first time! ;-)";

        assertThat(revision.getId(), is(equalTo("54")));
        assertThat(revision.getComment(), is(equalTo("Sweet coding challenge this!")));
        assertThat(revision.getFormat(), is(equalTo("text/x-wiki")));
        assertThat(revision.getMinor(), is(equalTo(Collections.emptyList())));
        assertThat(revision.getParentid(), is(equalTo("32")));
        assertThat(revision.getTimestamp(), is(equalTo("08:05:34T11-07-2017")));
        assertThat(revision.getText(), is(equalTo(text)));
        assertThat(revision.getModel(), is(equalTo("sammy'swiki")));
        assertThat(revision.getContributor(), is(equalTo(contributor)));
        assertThat(revision.getSha1(), is(equalTo("ds1crfrjsn7xv73djcs4e4aq9niwanx")));
    }

    @Test
    public void checkSetters(){

        revision = populateRevision(contributor);

        assertThat(revision.getId(), is(equalTo("387")));
        assertThat(revision.getComment(), is(equalTo("Hello! It's me again!!")));
        assertThat(revision.getFormat(), is(equalTo("text/sammy")));
        assertThat(revision.getContributor(), is(equalTo(contributor)));
        assertThat(revision.getMinor(), is(equalTo(Collections.emptyList())));
        assertThat(revision.getParentid(), is(equalTo("16")));
        assertThat(revision.getTimestamp(), is(equalTo("10:37:44T11-07-2017")));
        assertThat(revision.getText(), is(equalTo("One more try")));
        assertThat(revision.getModel(), is(equalTo("marie'swiki")));
        assertThat(revision.getSha1(), is(equalTo("someverylongwackylookingstring")));
    }
}