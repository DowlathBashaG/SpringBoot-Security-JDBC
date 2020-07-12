package io.dowlath.springbootsecurityjdbc.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Dowlath
 * @create 7/12/2020 1:19 PM
 */
@RestController
public class HomeController {

    // All (unauthenticated)
    @GetMapping("/")
    public String home() {
        return ("<h1>Welcome</h1>");
    }

    // USER and ADMIN roles
    @GetMapping("/user")
    public String user() {
        return ("<h1>Welcome User</h1>");
    }

    // ADMIN role
    @GetMapping("/admin")
    public String admin() {
        return ("<h1>Welcome Admin</h1>");
    }
}

