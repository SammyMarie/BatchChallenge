package com.thefloow.model;

import lombok.*;
import java.util.List;

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

    private List<Count> countList;
}