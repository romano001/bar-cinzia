package com.example.barcinzia.Controller;

import com.example.barcinzia.Entity.Image;
import com.example.barcinzia.Service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/image")
public class ImageController {

    @Autowired
    private ImageService imageService;

    @GetMapping("/all")
    List<Image> getImages() {
        return imageService.fetchImageList();
    }

    @PostMapping()
    Image createImage(@RequestBody Image image) {
        return imageService.saveImage(image);
    }

    @PutMapping("/{id}")
    public Image updateImage(@RequestBody Image image, @PathVariable("id") Integer imageId) {
        return imageService.updateImage(image, imageId);
    }

    @DeleteMapping("/{id}")
    public String deleteImageById(@PathVariable("id") Integer imageId) {
        imageService.deleteImageById(imageId);
        return "Deleted Successfully";
    }

}
