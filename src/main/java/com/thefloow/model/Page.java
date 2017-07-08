package com.thefloow.model;

import lombok.*;

/**
 * Created by samif on 08/07/2017.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Page {

    private String title;

    private String ns;

    private String id;

    private Redirect redirect;

    private Revision revision;
}