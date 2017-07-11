package com.thefloow.model;

import lombok.*;

/**
 * Created by samif on 08/07/2017.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Contributor {

    private String username;

    private String id;

    private String ip;
}