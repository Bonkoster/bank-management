package com.bonkost.bankmanagement.service;

import com.bonkost.bankmanagement.model.User;
import com.bonkost.bankmanagement.model.UserAccount;
import com.bonkost.bankmanagement.model.dto.CreateUserRequest;
import com.bonkost.bankmanagement.model.dto.GetUserResponse;
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
public class UserService {
    private final UserRepository userRepository;
    private final UserAccountService userAccountService;
    private final ModelMapper mapper;

    public UserService(UserRepository userRepository, UserAccountService userAccountService, ModelMapper mapper) {
        this.userRepository = userRepository;
        this.userAccountService = userAccountService;
        this.mapper = mapper;
    }

    @PostConstruct
    private void setup() {
        mapper.createTypeMap(CreateUserRequest.class, User.class);
        mapper.createTypeMap(User.class, GetUserResponse.class);
    }

    @Transactional
    public GetUserResponse create(CreateUserRequest request) {
        var user = mapper.map(request, User.class);
        var saved = userRepository.save(user);
        return mapper.map(saved, GetUserResponse.class);
    }

    @Transactional
    public List<GetUserResponse> list() {
        return userRepository.findAll()
                .stream()
                .map(user -> mapper.map(user, GetUserResponse.class))
                .collect(Collectors.toList());
    }

    @Transactional
    public void delete(long id) {
        if (isExist(id)) {
            userRepository.deleteById(id);
        } else {
            throw new NullPointerException("Specified user is not exist");
        }
    }

    @Transactional
    public boolean isExist(long id) {
        return userRepository.existsById(id);
    }
}
