package dev.michaelcao512.revshop_springboot.Services;

import dev.michaelcao512.revshop_springboot.DTO.ImageDto;
import dev.michaelcao512.revshop_springboot.Entities.Image;
import dev.michaelcao512.revshop_springboot.Entities.Product;
import dev.michaelcao512.revshop_springboot.Repositories.ImageRepository;
import dev.michaelcao512.revshop_springboot.Repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ImageService {
    private final S3Service s3Service;
    private final ImageRepository imageRepository;
    private final ProductRepository productRepository;

    public ImageService(S3Service s3Service, ImageRepository imageRepository, ProductRepository productRepository) {
        this.s3Service = s3Service;
        this.imageRepository = imageRepository;
        this.productRepository = productRepository;
    }

    public String generatePresignedUploadUrl(ImageDto request) {
        String bucketKey = UUID.randomUUID().toString();
        String presignedUrl = s3Service.generatePresignedUploadUrl(bucketKey);

        Product product = productRepository.findById(request.productProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        Image image = new Image();
        image.setProduct(product);
        image.setBucketKey(bucketKey);
        imageRepository.save(image);

        return presignedUrl;
    }

    public String generatePresignedDownloadUrl(Long imageId) {
        Image image = imageRepository.findById(imageId)
                .orElseThrow(() -> new RuntimeException("Image not found"));

        return s3Service.generatePresignedDownloadUrl(image.getBucketKey());
    }
}
