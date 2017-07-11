package com.thefloow.model;

import java.util.Collections;

/**
 * Created by samif on 11/07/2017.
 */
public class TestUtils {

    public static Contributor populateContributor() {
        return Contributor.builder()
                          .id("65")
                          .ip("192.00.00.00")
                          .username("someonecares")
                          .build();
    }

    public static Revision populateRevision(Contributor contributor) {

        Revision revision = Revision.builder().build();

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

        return revision;
    }

    public static Revision populateSetupRevision(Contributor contributor) {
        String text = "I would love to have finished but, too much has happened in my life right now." +
                " And, I've learnt this while doing it as it's my first time! ;-)";

        Revision revision = Revision.builder()
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

        return revision;
    }
}