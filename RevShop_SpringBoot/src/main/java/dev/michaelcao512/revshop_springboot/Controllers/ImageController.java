package dev.michaelcao512.revshop_springboot.Controllers;

import dev.michaelcao512.revshop_springboot.DTO.ImageDto;
import dev.michaelcao512.revshop_springboot.Services.ImageService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/images")
public class ImageController {
    private final ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @PostMapping
    public String generatePresignedUploadUrl(@RequestBody ImageDto request) {
        return imageService.generatePresignedUploadUrl(request);
    }

    @GetMapping("/{imageId}")
    public String generatePresignedDownloadUrl(@PathVariable Long imageId) {
        return imageService.generatePresignedDownloadUrl(imageId);
    }
}
