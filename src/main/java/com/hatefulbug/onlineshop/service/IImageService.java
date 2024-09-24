package com.hatefulbug.onlineshop.service;

import com.hatefulbug.onlineshop.dto.ImageDto;
import com.hatefulbug.onlineshop.model.Image;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IImageService {
    Image getImageById(int id);
    void deleteImageById(int id);
    List<ImageDto> saveImages(int productId, List<MultipartFile> files);
    void updateImage(MultipartFile file,  int imageId);
}