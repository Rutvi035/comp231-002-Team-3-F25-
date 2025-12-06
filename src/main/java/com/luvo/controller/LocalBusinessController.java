package com.luvo.controller;

import com.luvo.model.LocalBusiness;
import com.luvo.service.LocalBusinessService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.http.MediaType;

@Controller
@RequestMapping("/business")
public class LocalBusinessController {

    private final LocalBusinessService service;

    public LocalBusinessController(LocalBusinessService service) {
        this.service = service;
    }

    // ============================
    // SHOW BUSINESS PAGE + FORM
    // ============================
    @GetMapping
    public String list(Model model) {
        model.addAttribute("localBusiness", new LocalBusiness()); // NEW form (id = null)
        model.addAttribute("businesses", service.findAll());
        model.addAttribute("editing", false);
        return "local-business";
    }

    // ============================
    // SAVE (ADD OR UPDATE)
    // ============================
    @PostMapping
    public String save(@Valid @ModelAttribute("localBusiness") LocalBusiness business,
                       BindingResult br,
                       @RequestParam(value = "imageFile", required = false) MultipartFile imageFile,
                       Model model,
                       RedirectAttributes redirectAttrs) {

        boolean isNew = (business.getId() == null || business.getId().trim().isEmpty());

        // For NEW listing → generate ID
        if (isNew) {
            business.setId(UUID.randomUUID().toString());
        }

        // IMAGE HANDLING
        try {
            if (imageFile != null && !imageFile.isEmpty()) {

                String uploadDir = "C:/luvo-uploads";
                Files.createDirectories(Paths.get(uploadDir));

                String original = StringUtils.cleanPath(imageFile.getOriginalFilename());
                String ext = original.contains(".") ? original.substring(original.lastIndexOf(".")) : "";
                String filename = business.getId() + "-" + UUID.randomUUID() + ext;

                Path dest = Paths.get(uploadDir).resolve(filename);
                imageFile.transferTo(dest.toFile());

                business.setImagePath("/uploads/" + filename);

            } else {
                if (isNew) {
                    // NEW + no image → error
                    br.rejectValue("imagePath", "NotBlank", "Image is required");
                } else {
                    // EDIT: keep old image
                    service.findById(business.getId())
                           .ifPresent(existing -> business.setImagePath(existing.getImagePath()));
                }
            }
        } catch (IOException e) {
            br.reject("imageUpload", "Failed to upload image");
        }

        // SHOW ERRORS
        if (br.hasErrors()) {
            model.addAttribute("businesses", service.findAll());
            model.addAttribute("editing", !isNew);
            return "local-business";
        }

        // SAVE SUCCESS
        service.save(business);

        redirectAttrs.addFlashAttribute("successMessage",
                isNew ? "Listing added successfully" : "Listing updated successfully");

        return "redirect:/business";
    }

   
    @PostMapping(path = "/api", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> saveApi(@Valid @ModelAttribute("localBusiness") LocalBusiness business,
                                                       BindingResult br,
                                                       @RequestParam(value = "imageFile", required = false) MultipartFile imageFile) {

        Map<String, Object> resp = new HashMap<>();

        boolean isNew = (business.getId() == null || business.getId().trim().isEmpty());
        if (isNew) {
            business.setId(UUID.randomUUID().toString());
        }

        try {
            if (imageFile != null && !imageFile.isEmpty()) {
                String uploadDir = "C:/luvo-uploads";
                Files.createDirectories(Paths.get(uploadDir));

                String original = StringUtils.cleanPath(imageFile.getOriginalFilename());
                String ext = original.contains(".") ? original.substring(original.lastIndexOf(".")) : "";
                String filename = business.getId() + "-" + UUID.randomUUID() + ext;
                Path dest = Paths.get(uploadDir).resolve(filename);
                imageFile.transferTo(dest.toFile());
                business.setImagePath("/uploads/" + filename);
            } else {
                if (isNew) {
                    br.rejectValue("imagePath", "NotBlank", "Image is required");
                } else {
                    service.findById(business.getId()).ifPresent(existing -> business.setImagePath(existing.getImagePath()));
                }
            }
        } catch (IOException e) {
            br.reject("imageUpload", "Failed to upload image");
        }

        if (br.hasErrors()) {
            resp.put("success", false);
            resp.put("errors", br.getAllErrors());
            return ResponseEntity.badRequest().body(resp);
        }

        service.save(business);
        resp.put("success", true);
        resp.put("message", isNew ? "Listing added successfully" : "Listing updated successfully");
        resp.put("business", business);
        return ResponseEntity.ok(resp);
    }

    // ============================
    // EDIT LISTING
    // ============================
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable String id, Model model) {

        return service.findById(id).map(business -> {
            model.addAttribute("localBusiness", business);
            model.addAttribute("businesses", service.findAll());
            model.addAttribute("editing", true);
            return "local-business";
        }).orElse("redirect:/business");
    }

    // ============================
    // DELETE LISTING
    // ============================
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable String id) {
        service.delete(id);
        return "redirect:/business";
    }
}
