package com.bonkost.bankmanagement.controller;

import com.bonkost.bankmanagement.exception.InsufficientFundsException;
import com.bonkost.bankmanagement.model.dto.CreateUserAccountRequest;
import com.bonkost.bankmanagement.model.dto.PerformAccountOperationRequest;
import com.bonkost.bankmanagement.service.UserAccountService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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

    @ApiOperation(value = "Create new user account")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> create(@RequestBody CreateUserAccountRequest request) {
        return ResponseEntity.ok().body(userAccountService.create(request));
    }

    @ApiOperation(value = "Change account fund")
    @PatchMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> changeFund(@PathVariable long id, @RequestBody PerformAccountOperationRequest request) {
        try {
            userAccountService.changeFund(id, request);
            return ResponseEntity.ok().build();
        } catch (NullPointerException e) {
            return ResponseEntity.notFound().build();
        } catch (InsufficientFundsException e) {
            return ResponseEntity.unprocessableEntity().body(e.getMessage());
        }
    }

    @ApiOperation(value = "Get accounts list for specified user")
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> list(@PathVariable long id) {
        return ResponseEntity.ok().body(userAccountService.list(id));
    }

    @ApiOperation(value = "Get balance of specified user account")
    @GetMapping(value = "/balance/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> balance(@PathVariable long id) {
        try {
            userAccountService.balance(id);
            return ResponseEntity.ok().body(userAccountService.balance(id));
        } catch (NullPointerException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @ApiOperation(value = "Get all fund operations with specified user account")
    @GetMapping(value = "/operations/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> operations(@PathVariable long id) {
        try {
            return ResponseEntity.ok().body(userAccountService.operations(id));
        } catch (NullPointerException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @ApiOperation(value = "Delete user account")
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
