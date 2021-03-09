package com.bonkost.bankmanagement.repository;

import com.bonkost.bankmanagement.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Vladimir Luchnikov
 */

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
