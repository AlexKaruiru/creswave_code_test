package com.posts.controller;

import com.posts.model.UserModel;
import com.posts.service.UserService;
import com.posts.vo.LoginForm;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @Operation(summary = "User Signup")
    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> signupUser(@Valid @RequestBody UserModel userModel) {
        userService.signup(userModel);
        return ResponseEntity.ok("User signup completed.");
    }

    @Operation(summary = "User Login")
    @PostMapping("/login")
    public String loginUser(@RequestBody LoginForm loginForm) {
        return userService.login(loginForm);
    }

    @Operation(summary = "Get All Users")
    @GetMapping("/users")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<UserModel> getAllUsers() {
        return userService.getAllUsers();
    }

    @Operation(summary = "Update User Profile")
    @PutMapping("/users/{userId}")
    public ResponseEntity<String> updateUserProfile(@PathVariable Long userId, @Valid @RequestBody UserModel userModel) {
        userService.updateProfile(userId, userModel);
        return ResponseEntity.ok("User profile updated successfully.");
    }

    @Operation(summary = "Update User")
    @PutMapping("/users/{userId}")
    @PreAuthorize("#userId == principal.userId or hasRole('ROLE_ADMIN')")
    public ResponseEntity<String> updateUser(@PathVariable Long userId, @Valid @RequestBody UserModel userModel) {
        userModel.setUserId(userId);
        userService.updateUser(userModel);
        return ResponseEntity.ok("User updated successfully.");
    }

    @Operation(summary = "Delete User")
    @DeleteMapping("/users/{userId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<String> deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return ResponseEntity.ok("User deleted successfully.");
    }
}
