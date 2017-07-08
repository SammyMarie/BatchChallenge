package com.thefloow.model;

import lombok.*;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by samif on 08/07/2017.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Revision {

    private String id;

    private String parentid;

    private Timestamp timestamp;

    private Contributor contributor;

    private List<Object> minor;

    private String comment;

    private String model;

    private String format;

    private Text text;

    private String sha1;
}