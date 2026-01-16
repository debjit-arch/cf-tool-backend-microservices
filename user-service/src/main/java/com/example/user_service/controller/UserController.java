package com.example.user_service.controller;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.user_service.model.Department;
import com.example.user_service.model.User;
import com.example.user_service.repository.DepartmentRepository;
import com.example.user_service.repository.UserRepository;
import com.example.user_service.security.JwtTokenProvider;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
   
    // ---------------- REGISTER ----------------
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        try {
            if (userRepository.existsByEmail(user.getEmail()))
                return ResponseEntity.badRequest().body("Email already exists");

            if (!"super_admin".equals(user.getRole())) {
                if (user.getDepartment() == null)
                    return ResponseEntity.badRequest().body("Department required");

                Department dept = departmentRepository.findById(user.getDepartment()).orElse(null);
                if (dept == null) return ResponseEntity.badRequest().body("Invalid department");
            }

            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setCreatedAt(new Date());
            user.setUpdatedAt(new Date());

            User saved = userRepository.save(user);
            saved.setPassword(null);
            return ResponseEntity.ok(saved);

        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error: " + e.getMessage());
        }
    }

    // ---------------- LOGIN ----------------
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody User loginRequest) {
        User user = userRepository.findByEmail(loginRequest.getEmail()).orElse(null);
        if (user == null || !passwordEncoder.matches(loginRequest.getPassword(), user.getPassword()))
            return ResponseEntity.status(401).body("Invalid email or password");

        // Load department manually
        Department dept = null;
        if (user.getDepartment() != null) {
            dept = departmentRepository.findById(user.getDepartment()).orElse(null);
        }

        String token = jwtTokenProvider.generateToken(user);
        return ResponseEntity.ok(new LoginResponse(token, user, dept));
    }

    // ---------------- GET ALL USERS ----------------
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(HttpServletRequest request) {
        // Optionally, you can validate requester role from request attributes
        List<User> users = userRepository.findAll()
                .stream()
                .peek(u -> u.setPassword(null))
                .collect(Collectors.toList());
        return ResponseEntity.ok(users);
    }

    // ---------------- CHANGE PASSWORD ----------------
    @PostMapping("/change-password")
    public ResponseEntity<?> changePassword(@RequestBody ChangePasswordRequest req, HttpServletRequest request) {
        String userId = (String) request.getAttribute("userId");
        if (userId == null) return ResponseEntity.status(401).body("Unauthorized");

        User user = userRepository.findById(userId).orElse(null);
        if (user == null) return ResponseEntity.status(404).body("User not found");

        if (!passwordEncoder.matches(req.getOldPassword(), user.getPassword()))
            return ResponseEntity.status(401).body("Old password incorrect");

        user.setPassword(passwordEncoder.encode(req.getNewPassword()));
        user.setUpdatedAt(new Date());
        userRepository.save(user);

        return ResponseEntity.ok("Password changed successfully");
    }

    // ---------------- UPDATE USER ----------------
    @PostMapping("/update")
    public ResponseEntity<?> updateUser(@RequestBody User updatedUser, HttpServletRequest request) {
        String id = updatedUser.getId();
        User existing = userRepository.findById(id).orElse(null);
        if (existing == null) return ResponseEntity.status(404).body("User not found");

        String requesterId = (String) request.getAttribute("userId");
        User requester = userRepository.findById(requesterId).orElse(null);

        if ("super_admin".equals(existing.getRole()) &&
            (requester == null || !"super_admin".equals(requester.getRole())))
            return ResponseEntity.status(403).body("Only super_admin can modify another super_admin");

        if (updatedUser.getName() != null) existing.setName(updatedUser.getName());
        if (updatedUser.getEmail() != null) existing.setEmail(updatedUser.getEmail());
        if (updatedUser.getRole() != null) existing.setRole(updatedUser.getRole());
        if (updatedUser.getPassword() != null)
            existing.setPassword(passwordEncoder.encode(updatedUser.getPassword()));

        if (updatedUser.getDepartment() != null) {
            Department dept = departmentRepository.findById(updatedUser.getDepartment()).orElse(null);
            if (dept != null) existing.setDepartment(dept.getId());
        }

        existing.setUpdatedAt(new Date());
        userRepository.save(existing);
        existing.setPassword(null);

        return ResponseEntity.ok(existing);
    }

    // ---------------- DELETE USER ----------------
    @PostMapping("/delete")
    public ResponseEntity<?> deleteUser(@RequestBody String userId, HttpServletRequest request) {
        String requesterId = (String) request.getAttribute("userId");
        if (requesterId == null) return ResponseEntity.status(401).body("Unauthorized");

        if (requesterId.equals(userId)) return ResponseEntity.badRequest().body("Cannot delete yourself");

        User user = userRepository.findById(userId).orElse(null);
        if (user == null) return ResponseEntity.status(404).body("User not found");

        User requester = userRepository.findById(requesterId).orElse(null);
        if ("super_admin".equals(user.getRole()) &&
            (requester == null || !"super_admin".equals(requester.getRole())))
            return ResponseEntity.status(403).body("Only super_admin can delete another super_admin");

        userRepository.delete(user);
        return ResponseEntity.ok("User deleted successfully");
    }

    // ---------------- DTOs ----------------
    static class LoginResponse {
        public String token;
        public String id;
        public String name;
        public String role;
        public Department department;
        public String organization;
        public boolean isAuditor;
        public String auditorName;

        public LoginResponse(String token, User user, Department dept) {
            this.token = token;
            this.id = user.getId();
            this.name = user.getName();
            this.role = user.getRole();
            this.department = dept;
            this.isAuditor = user.isAuditor();
            this.organization = user.getOrganization();
            this.auditorName = user.getAuditorName();
        }
    }

    static class ChangePasswordRequest {
        public String oldPassword;
        public String newPassword;

        public String getOldPassword() { return oldPassword; }
        public void setOldPassword(String oldPassword) { this.oldPassword = oldPassword; }
        public String getNewPassword() { return newPassword; }
        public void setNewPassword(String newPassword) { this.newPassword = newPassword; }
    }
}
