package com.bonkost.bankmanagement.repository;

import com.bonkost.bankmanagement.model.AccountOperation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Vladimir Luchnikov
 */

@Repository
public interface AccountOperationRepository extends JpaRepository<AccountOperation, Long> {
}
