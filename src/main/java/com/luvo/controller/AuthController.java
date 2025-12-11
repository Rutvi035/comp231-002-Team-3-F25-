package com.luvo.controller;



import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AuthController {

    // Login Page
    @GetMapping("/login")
    public String showLoginPage(Model model) {
        model.addAttribute("pageTitle", "Login - Luvo");
        return "login";
    }

    // Process Login
    @PostMapping("/login/process")
    public String processLogin(
            @RequestParam String email,
            @RequestParam String password,
            @RequestParam(required = false) String remember,
            RedirectAttributes redirectAttributes) {
        
        // Here you would typically:
        // 1. Validate credentials against database
        // 2. Create session
        // 3. Handle "remember me" functionality
        
        System.out.println("Login attempt:");
        System.out.println("Email: " + email);
        System.out.println("Remember me: " + (remember != null));
        
        // For demonstration, we'll just redirect to home
        // In production, validate and create session
        redirectAttributes.addFlashAttribute("successMessage", 
            "Welcome back! You have successfully logged in.");
        
        return "redirect:/";
    }

    // Sign Up Page
    @GetMapping("/signup")
    public String showSignUpPage(Model model) {
        model.addAttribute("pageTitle", "Sign Up - Luvo");
        return "signup";
    }

    // Process Sign Up
    @PostMapping("/signup/process")
    public String processSignUp(
            @RequestParam String firstName,
            @RequestParam String lastName,
            @RequestParam String email,
            @RequestParam String password,
            @RequestParam String confirmPassword,
            @RequestParam String accountType,
            @RequestParam(required = false) String terms,
            @RequestParam(required = false) String newsletter,
            RedirectAttributes redirectAttributes) {
        
        // Here you would typically:
        // 1. Validate input (password match, email format, etc.)
        // 2. Check if email already exists
        // 3. Hash the password
        // 4. Save user to database
        // 5. Send verification email
        
        System.out.println("Sign up attempt:");
        System.out.println("Name: " + firstName + " " + lastName);
        System.out.println("Email: " + email);
        System.out.println("Account Type: " + accountType);
        System.out.println("Newsletter: " + (newsletter != null));
        
        // Validate password match
        if (!password.equals(confirmPassword)) {
            redirectAttributes.addFlashAttribute("errorMessage", 
                "Passwords do not match!");
            return "redirect:/signup";
        }
        
        // Validate terms accepted
        if (terms == null) {
            redirectAttributes.addFlashAttribute("errorMessage", 
                "You must agree to the Terms of Service!");
            return "redirect:/signup";
        }
        
        // Success - redirect to login or home
        redirectAttributes.addFlashAttribute("successMessage", 
            "Account created successfully! Please log in.");
        
        return "redirect:/login";
    }

    // Forgot Password Page
    @GetMapping("/forgot-password")
    public String showForgotPasswordPage(Model model) {
        model.addAttribute("pageTitle", "Forgot Password - Luvo");
        return "forgot-password";
    }

    // Process Forgot Password
    @PostMapping("/forgot-password/process")
    public String processForgotPassword(
            @RequestParam String email,
            RedirectAttributes redirectAttributes) {
        
        // Here you would typically:
        // 1. Check if email exists in database
        // 2. Generate password reset token
        // 3. Send reset email
        
        System.out.println("Password reset requested for: " + email);
        
        redirectAttributes.addFlashAttribute("successMessage", 
            "If an account exists with this email, you will receive password reset instructions.");
        
        return "redirect:/login";
    }

    // Logout
    @GetMapping("/logout")
    public String logout(RedirectAttributes redirectAttributes) {
        // Here you would typically:
        // 1. Invalidate session
        // 2. Clear cookies
        
        redirectAttributes.addFlashAttribute("successMessage", 
            "You have been successfully logged out.");
        
        return "redirect:/";
    }
}