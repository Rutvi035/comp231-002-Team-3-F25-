package com.luvo.controller;



import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ContactController {

    @GetMapping("/contact")
    public String showContactPage(Model model) {
        model.addAttribute("pageTitle", "Contact Us - Luvo");
        return "contact";
    }

    @PostMapping("/contact/submit")
    public String submitContactForm(
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam(required = false) String phone,
            @RequestParam String subject,
            @RequestParam String message,
            RedirectAttributes redirectAttributes) {
        
        // Here you would typically:
        // 1. Validate the input
        // 2. Save to database
        // 3. Send email notification
        // 4. Log the contact request
        
        // For now, we'll just add a success message
        System.out.println("Contact Form Submitted:");
        System.out.println("Name: " + name);
        System.out.println("Email: " + email);
        System.out.println("Phone: " + phone);
        System.out.println("Subject: " + subject);
        System.out.println("Message: " + message);
        
        redirectAttributes.addFlashAttribute("successMessage", 
            "Thank you for contacting us! We'll get back to you soon.");
        
        return "redirect:/contact/success";
    }
    
    @GetMapping("/contact/success")
    public String contactSuccess(Model model) {
        model.addAttribute("pageTitle", "Message Sent - Luvo");
        return "contact-success";
    }
}