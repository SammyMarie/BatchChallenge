package com.thefloow.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    private String timestamp;

    private Contributor contributor;

    private List<Object> minor;

    private String comment;

    private String model;

    private String format;

    private String text;

    private String sha1;
}