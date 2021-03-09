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

    @Column(name = "funds_amount", nullable = false)
    private double fundsAmount;

    @Column(name = "type", nullable = false)
    private int type;

    @Column(name = "created_at", nullable = false)
    private Date createdAt;

    @ManyToOne
    private UserAccount userAccount;
}
