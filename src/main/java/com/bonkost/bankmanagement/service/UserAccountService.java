package com.bonkost.bankmanagement.service;

import com.bonkost.bankmanagement.exception.InsufficientFundsException;
import com.bonkost.bankmanagement.model.AccountOperation;
import com.bonkost.bankmanagement.model.UserAccount;
import com.bonkost.bankmanagement.model.dto.CreateUserAccountRequest;
import com.bonkost.bankmanagement.model.dto.GetAccountOperationResponse;
import com.bonkost.bankmanagement.model.dto.GetUserAccountResponse;
import com.bonkost.bankmanagement.model.dto.PerformAccountOperationRequest;
import com.bonkost.bankmanagement.model.enumeration.OperationType;
import com.bonkost.bankmanagement.repository.AccountOperationRepository;
import com.bonkost.bankmanagement.repository.UserAccountRepository;
import com.bonkost.bankmanagement.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Vladimir Luchnikov
 */

@Service
public class UserAccountService {
    private final UserRepository userRepository;
    private final UserAccountRepository accountRepository;
    private final AccountOperationRepository accountOperationRepository;
    private final ModelMapper mapper;

    public UserAccountService(UserRepository userRepository, UserAccountRepository accountRepository, AccountOperationRepository accountOperationRepository, ModelMapper mapper) {
        this.userRepository = userRepository;
        this.accountRepository = accountRepository;
        this.accountOperationRepository = accountOperationRepository;
        this.mapper = mapper;
    }

    @PostConstruct
    private void setup() {
        mapper.createTypeMap(CreateUserAccountRequest.class, UserAccount.class)
                .setPostConverter(cnt -> {
                    var account = cnt.getDestination();
                    account.setFunds(0.00);
                    return account;
                });
        mapper.createTypeMap(UserAccount.class, GetUserAccountResponse.class);
        mapper.createTypeMap(AccountOperation.class, GetAccountOperationResponse.class)
                .setPostConverter(cnt -> {
                    var operation = cnt.getDestination();
                    operation.setType(OperationType.valueOf(cnt.getSource().getType()));
                    return operation;
                });
    }

    @Transactional
    public GetUserAccountResponse create(CreateUserAccountRequest request) {
        var account = mapper.map(request, UserAccount.class);
        var user = userRepository.findById(request.getUserId()).orElseThrow(NullPointerException::new);
        account.setUser(user);
        var saved = accountRepository.save(account);
        return mapper.map(saved, GetUserAccountResponse.class);
    }

    @Transactional
    public List<GetUserAccountResponse> list(long userId) {
        var user = userRepository.findById(userId).orElseThrow(NullPointerException::new);
        return user.getAccounts()
                .stream()
                .map(account -> mapper.map(account, GetUserAccountResponse.class))
                .collect(Collectors.toList());
    }

    @Transactional
    public List<GetAccountOperationResponse> operations(long accountId) {
        var account = accountRepository.findById(accountId).orElseThrow(NullPointerException::new);
        return account.getOperations()
                .stream()
                .map(operation -> mapper.map(operation, GetAccountOperationResponse.class))
                .sorted(Comparator.comparing(GetAccountOperationResponse::getCreatedAt, Comparator.reverseOrder()))
                .collect(Collectors.toList());
    }

    @Transactional
    public double balance(long accountId) {
        var account = accountRepository.findById(accountId).orElseThrow(NullPointerException::new);
        return account.getFunds();
    }

    @Transactional
    public void changeFund(long accountId, PerformAccountOperationRequest request) {
        var account = accountRepository.findById(accountId).orElseThrow(NullPointerException::new);
        var funds = request.getFunds();
        var type = request.getType();
        if (type == OperationType.WITHDRAW && account.getFunds() < funds) {
            throw new InsufficientFundsException("You cannot withdraw more money than you have");
        }

        if (type == OperationType.DEPOSIT) {
            account.setFunds(account.getFunds() + funds);
        } else {
            account.setFunds(account.getFunds() - funds);
        }
        accountRepository.save(account);

        var operation = new AccountOperation();
        operation.setFundsAmount(funds);
        operation.setType(type.getNumber());
        operation.setCreatedAt(new Date());
        operation.setUserAccount(account);
        accountOperationRepository.save(operation);
    }

    @Transactional
    public void delete(long id) {
        if (isExist(id)) {
            accountRepository.deleteById(id);
        } else {
            throw new NullPointerException();
        }
    }

    @Transactional
    public boolean isExist(long id) {
        return accountRepository.existsById(id);
    }
}
