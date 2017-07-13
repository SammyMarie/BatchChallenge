package com.thefloow.model;

import lombok.*;

/**
 * Created by samif on 13/07/2017.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Count {

    private String word;

    private long count;
}