package com.bonkost.bankmanagement.service;

import com.bonkost.bankmanagement.model.User;
import com.bonkost.bankmanagement.model.UserAccount;
import com.bonkost.bankmanagement.model.dto.CreateUserAccountRequest;
import com.bonkost.bankmanagement.model.dto.CreateUserRequest;
import com.bonkost.bankmanagement.model.dto.GetUserAccountResponse;
import com.bonkost.bankmanagement.model.dto.GetUserResponse;
import com.bonkost.bankmanagement.repository.UserAccountRepository;
import com.bonkost.bankmanagement.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Vladimir Luchnikov
 */

@Service
public class UserAccountService {
    private final UserRepository userRepository;
    private final UserAccountRepository accountRepository;
    private final ModelMapper mapper;

    public UserAccountService(UserRepository userRepository, UserAccountRepository accountRepository, ModelMapper mapper) {
        this.userRepository = userRepository;
        this.accountRepository = accountRepository;
        this.mapper = mapper;
    }

    @PostConstruct
    private void setup() {
        mapper.createTypeMap(CreateUserAccountRequest.class, UserAccount.class)
                .setPostConverter(cnt -> {
                    var account = cnt.getDestination();
                    account.setFund(0.00);
                    return account;
                });
        mapper.createTypeMap(UserAccount.class, GetUserAccountResponse.class);
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
        if (userRepository.existsById(userId)) {
            return accountRepository.findAllByUserId(userId)
                    .stream()
                    .map(user -> mapper.map(user, GetUserAccountResponse.class))
                    .collect(Collectors.toList());
        } else {
            throw new NullPointerException("Specified user is not exist");
        }
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
