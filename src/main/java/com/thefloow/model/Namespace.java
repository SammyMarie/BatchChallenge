package com.thefloow.model;

import lombok.*;

/**
 * Created by samif on 08/07/2017.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Namespace {

    private String key;

    private String _case;

    private String text;
}