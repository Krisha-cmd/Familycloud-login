package com.family.cloud.loginsystem.controller;

import com.family.cloud.loginsystem.dto.ReqRes;
import com.family.cloud.loginsystem.entity.OurUsers;
import com.family.cloud.loginsystem.service.UsersManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserManagementController {
    @Autowired
    private UsersManagementService usersManagementService;

    @PostMapping("/auth/register")
    public ResponseEntity<ReqRes> register(@RequestBody ReqRes req){
        return ResponseEntity.ok(usersManagementService.register(req));
    }

    @PostMapping("/auth/login")
    public ResponseEntity<ReqRes> login(@RequestBody ReqRes req){
        return ResponseEntity.ok(usersManagementService.login(req));
    }

    @PostMapping("/auth/refresh")
    public ResponseEntity<ReqRes> refreshToken(@RequestBody ReqRes req){
        return ResponseEntity.ok(usersManagementService.refreshToken(req));
    }

    @GetMapping("/admin/get-all-users")
    public ResponseEntity<ReqRes> getAllUsers(){
        return ResponseEntity.ok(usersManagementService.getAllUsers());
    }

    @GetMapping("/admin/get-user/{userid}")
    public ResponseEntity<ReqRes> getUserById(@PathVariable Integer userid){
        return ResponseEntity.ok(usersManagementService.getUserById(userid));
    }

    @PutMapping("/admin/update/{userid}")
    public ResponseEntity<ReqRes> updateUser(@PathVariable Integer userid, @RequestBody OurUsers reqRes ){
        return ResponseEntity.ok(usersManagementService.updateUser(userid, reqRes));
    }

    @GetMapping("/adminuser/get-profile")
    public ResponseEntity<ReqRes> getMyProfile(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        ReqRes response = usersManagementService.getMyInfo(email);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @DeleteMapping("/admin/delete/{userid}")
    public ResponseEntity<ReqRes> deleteUser(@PathVariable Integer userid){
        return ResponseEntity.ok(usersManagementService.deleteUser(userid));
    }

}
