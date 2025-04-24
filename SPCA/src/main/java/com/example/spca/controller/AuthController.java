package com.example.spca.controller;

import com.example.spca.entities.Customer;
import com.example.spca.service.CustomerService;
import jakarta.servlet.http.HttpSession;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping 
public class AuthController {

	@Value("${app.admin.username}")
	private String adminUser;

	@Value("${app.admin.password}")
	private String adminPass;

	private final CustomerService cs;

	public AuthController(CustomerService cs) {
		this.cs = cs;
	}

	@PostMapping("/login")
    public ResponseEntity<?> doLogin(@RequestParam String username, @RequestParam String password, HttpSession session) {
        Map<String,Object> resp = new HashMap<>();

        if (adminUser.equals(username) && adminPass.equals(password)) {
            session.setAttribute("role", "ADMIN");
            session.setAttribute("username", adminUser);
            resp.put("role", "ADMIN");
            resp.put("username", adminUser);
            return ResponseEntity.ok(resp);
        }

        Customer c = cs.authenticate(username, password);
        if (c == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        session.setAttribute("role", "USER");
        session.setAttribute("customer", c);

        resp.put("role", "USER");
        resp.put("id", c.getId());
        resp.put("username", c.getUsername());
        return ResponseEntity.ok(resp);
    }

	@PostMapping("/logout")
	public ResponseEntity<Void> doLogout(HttpSession session) {
		session.invalidate();
		return ResponseEntity.ok().build();
	}

	@GetMapping("/register")
	public String registerPage() {
		return "forward:/register.html";
	}

	@PostMapping("/register")
	public ResponseEntity<Customer> register(@Validated @RequestBody Customer c) {
		cs.registerCustomer(c.getUsername(), c.getPassword(), c.getShippingAddress(), c.getPaymentMethod());
		return ResponseEntity.ok().build();
	}

	@GetMapping("/api/session")
	public ResponseEntity<Customer> session(HttpSession session) {
		Customer c = (Customer) session.getAttribute("customer");
		if (c == null) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
		return ResponseEntity.ok(c);
	}
}
