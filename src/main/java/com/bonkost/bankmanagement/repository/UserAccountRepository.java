package com.bonkost.bankmanagement.repository;

import com.bonkost.bankmanagement.model.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Vladimir Luchnikov
 */

@Repository
public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {
}
