package com.example.barcinzia.Service;

import com.example.barcinzia.Entity.Image;

import java.util.List;

public interface ImageService {

    // Save operation
    Image saveImage(Image image);

    // Read operation
    List<Image> fetchImageList();

    // Update operation
    Image updateImage(Image image, Integer imageId);

    // Delete operation
    void deleteImageById(Integer imageId);

}
