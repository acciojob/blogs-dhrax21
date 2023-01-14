package com.driver.services;

import com.driver.models.*;
import com.driver.repositories.BlogRepository;
import com.driver.repositories.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageService {
    @Autowired
    ImageRepository imageRepository2;

    @Autowired
    BlogRepository blogRepository;

    public Image createAndReturn(Blog blog, String description, String dimensions){
        //create an image based on given parameters and add it to the imageList of given blog

        Image image=new Image(description,dimensions);

        Blog blog1=blogRepository.findById(blog.getId()).get();
        image.setBlog(blog1);

        List<Image> listImage=blog1.getImageList();
        listImage.add(image);

        blogRepository.save(blog1);
        return image;
    }

    public void deleteImage(int id){

       if(imageRepository2.findById(id).isPresent()){
           imageRepository2.deleteById(id);
       }
    }

    public Image findById(int id) {
        return null;
    }

    public int countImagesInScreen(int id, String screenDimensions) {
        //Find the number of images of given dimensions that can fit in a screen having `screenDimensions`
        //In case the image is null, return 0

        Image image1=new Image();

        if(imageRepository2.findById(id).isPresent()){
            image1=imageRepository2.findById(id).get();
        }else
            return 0;

        String imgSize=image1.getDimensions();
        int x=0;
        int i=0;
        while(i<imgSize.length()){
            char c=imgSize.charAt(i);
             if(c=='x' || c=='X'){
                 x=i;
                 break;
             }
             i++;
        }

        int first=Integer.valueOf(imgSize.substring(0,x));
        int second=Integer.valueOf(imgSize.substring(x+1));

        int finalImageDim=first*second;
        x=0;i=0;

        while(i<screenDimensions.length()){
            char c=screenDimensions.charAt(i);
            if(c=='x' || c=='X'){
                x=i;
                break;
            }
            i++;
        }

        first=Integer.valueOf(screenDimensions.substring(0,x));
        second=Integer.valueOf(screenDimensions.substring(x+1));

        int finalScreenDim=first * second;
        int count=finalScreenDim/finalImageDim;
        return count;
    }
}
