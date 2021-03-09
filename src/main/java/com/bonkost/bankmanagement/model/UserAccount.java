package com.bonkost.bankmanagement.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/**
 * @author Vladimir Luchnikov
 */

@Entity
@Table(name = "account")
@Getter
@Setter
public final class UserAccount {

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false, unique = true)
    private long id;

    @Column(name = "funds", nullable = false)
    private double funds;

    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "userAccount", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AccountOperation> operations;
}
