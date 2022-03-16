package com.revature.cachemoney.backend.beans.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Model containing information regarding a Transfer.
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Transfer {
    private Integer sourceAccountId;
    private Integer destinationAccountId;
    private Transaction transaction;
}
