package com.bonkost.bankmanagement.controller;

import com.bonkost.bankmanagement.model.dto.CreateUserRequest;
import com.bonkost.bankmanagement.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author Vladimir Luchnikov
 */

@RestController
@RequestMapping("/user")
@Api(tags = "User")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ApiOperation(value = "Create user")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> create(@RequestBody CreateUserRequest request) {
        return ResponseEntity.ok().body(userService.create(request));
    }

    @ApiOperation(value = "Get users list")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> list() {
        return ResponseEntity.ok().body(userService.list());
    }

    @ApiOperation(value = "Get total balance from all user accounts")
    @GetMapping(value = "/total/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> totalBalance(@PathVariable long id) {
        try {
            return ResponseEntity.ok().body(userService.totalBalance(id));
        } catch (NullPointerException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @ApiOperation(value = "Delete user")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable long id) {
        try {
            userService.delete(id);
            return ResponseEntity.ok().build();
        } catch (NullPointerException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
