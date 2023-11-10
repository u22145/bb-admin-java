package com.playbaby.bb.admin.common.entity.io;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public abstract class CommonIO {
    private String from;
    private String to;

    private String fromDate;
    private String toDate;

    private String sort;
    private String sortStr;
}
