package com.bonkost.bankmanagement.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

/**
 * @author Vladimir Luchnikov
 */

@Entity
@Table(name = "operation")
@Getter
@Setter
public final class AccountOperation {

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false, unique = true)
    private long id;

    @Column(name = "fund_sum", nullable = false)
    private double fundSum;

    @Column(name = "type", nullable = false)
    private int operationType;

    @Column(name = "created_at", nullable = false)
    private Date createdAt;

    @ManyToOne
    private UserAccount userAccount;
}
