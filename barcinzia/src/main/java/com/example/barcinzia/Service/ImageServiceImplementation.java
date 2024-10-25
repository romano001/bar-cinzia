package com.example.barcinzia.Service;

import com.example.barcinzia.Entity.Image;
import com.example.barcinzia.Repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class ImageServiceImplementation implements ImageService{

    @Autowired
    private ImageRepository imageRepository;

    // Save operation
    @Override
    public Image saveImage(Image image)
    {
        return imageRepository.save(image);
    }

    // Read operation
    @Override
    public List<Image> fetchImageList()
    {
        return (List<Image>) imageRepository.findAll();
    }

    // Update operation
    @Override
    public Image updateImage(Image image, Integer imageId)
    {

        Image imageDB = imageRepository.findById(imageId).get();

        if (Objects.nonNull(image.getLink()) && !"".equalsIgnoreCase(image.getLink())) {
            imageDB.setLink(image.getLink());
        }

        if (Objects.nonNull(image.getDescription()) && !"".equalsIgnoreCase(image.getDescription())) {
            imageDB.setDescription(image.getDescription());
        }

        if (Objects.nonNull(image.getItem())) {
            imageDB.setItem(image.getItem());
        }

        return imageRepository.save(imageDB);
    }

    // Delete operation
    @Override
    public void deleteImageById(Integer imageId)
    {
        imageRepository.deleteById(imageId);
    }

}
