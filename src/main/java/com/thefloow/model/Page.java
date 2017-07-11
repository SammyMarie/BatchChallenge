package com.thefloow.model;

import lombok.*;
import org.springframework.batch.item.ResourceAware;
import org.springframework.core.io.Resource;

/**
 * Created by samif on 08/07/2017.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Page implements ResourceAware{

    private String title;

    private String ns;

    private String id;

    private Redirect redirect;

    private Revision revision;

    private Resource resource;

    @Override
    public void setResource(Resource resource) {
        this.resource = resource;
    }
}