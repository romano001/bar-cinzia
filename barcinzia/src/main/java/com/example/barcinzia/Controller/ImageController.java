package com.example.barcinzia.Controller;

import com.example.barcinzia.Entity.Image;
import com.example.barcinzia.Service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ImageController {

    @Autowired
    private ImageService imageService;

    @GetMapping("/images")
    List<Image> getImages() {
        return imageService.fetchImageList();
    }

    @PostMapping("/image")
    Image createImage(@RequestBody Image image) {
        return imageService.saveImage(image);
    }

    @PutMapping("/image/{id}")
    public Image updateImage(@RequestBody Image image, @PathVariable("id") Integer imageId) {
        return imageService.updateImage(image, imageId);
    }

    @DeleteMapping("/image/{id}")
    public String deleteImageById(@PathVariable("id") Integer imageId) {
        imageService.deleteImageById(imageId);
        return "Deleted Successfully";
    }

}
