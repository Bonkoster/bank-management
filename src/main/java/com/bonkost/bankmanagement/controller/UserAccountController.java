package com.bonkost.bankmanagement.controller;

import com.bonkost.bankmanagement.model.dto.CreateUserAccountRequest;
import com.bonkost.bankmanagement.model.dto.CreateUserRequest;
import com.bonkost.bankmanagement.service.UserAccountService;
import io.swagger.annotations.Api;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author Vladimir Luchnikov
 */

@RestController
@RequestMapping("/account")
@Api(tags = "User account")
public class UserAccountController {
    private final UserAccountService userAccountService;

    public UserAccountController(UserAccountService userAccountService) {
        this.userAccountService = userAccountService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> create(@RequestBody CreateUserAccountRequest request) {
        return ResponseEntity.ok().body(userAccountService.create(request));
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> list(@PathVariable long id) {
        return ResponseEntity.ok().body(userAccountService.list(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable long id) {
        try {
            userAccountService.delete(id);
            return ResponseEntity.ok().build();
        } catch (NullPointerException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
