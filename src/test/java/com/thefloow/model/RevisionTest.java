package com.thefloow.model;

import org.junit.Before;
import org.junit.Test;

import java.util.Collections;

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

        contributor = Contributor.builder()
                                 .id("65")
                                 .ip("192.00.00.00")
                                 .username("someonecares")
                                 .build();

        populateSetupRevision(contributor);
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

        populateRevision();

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

    private void populateRevision() {
        revision.setId("387");
        revision.setComment("Hello! It's me again!!");
        revision.setFormat("text/sammy");
        revision.setContributor(contributor);
        revision.setMinor(Collections.EMPTY_LIST);
        revision.setParentid("16");
        revision.setTimestamp("10:37:44T11-07-2017");
        revision.setText("One more try");
        revision.setModel("marie'swiki");
        revision.setSha1("someverylongwackylookingstring");
    }

    private void populateSetupRevision(Contributor contributor) {
        String text = "I would love to have finished but, too much has happened in my life right now." +
                " And, I've learnt this while doing it as it's my first time! ;-)";

        revision = Revision.builder()
                           .id("54")
                           .comment("Sweet coding challenge this!")
                           .format("text/x-wiki")
                           .minor(Collections.EMPTY_LIST)
                           .parentid("32")
                           .timestamp("08:05:34T11-07-2017")
                           .text(text)
                           .model("sammy'swiki")
                           .contributor(contributor)
                           .sha1("ds1crfrjsn7xv73djcs4e4aq9niwanx")
                           .build();
    }
}