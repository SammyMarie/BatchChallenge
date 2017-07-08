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
public class SiteInfo {

    private String sitename;

    private String dbname;

    private String base;

    private String generator;

    private String _case;

    private List<Namespace> namespaces;
}