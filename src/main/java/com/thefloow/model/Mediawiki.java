package com.thefloow.model;

import lombok.*;
/**
 * Created by samif on 09/07/2017.
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Mediawiki {

    private String schemaLocation;

    private String version;

    private String lang;

    private SiteInfo siteinfo;

    private Page page;
}