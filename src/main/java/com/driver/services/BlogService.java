package com.driver.services;

import com.driver.models.Blog;
import com.driver.models.Image;
import com.driver.models.User;
import com.driver.repositories.BlogRepository;
import com.driver.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BlogService {
    @Autowired
    BlogRepository blogRepository1;

    @Autowired
    ImageService imageService1;

    @Autowired
    UserRepository userRepository1;

    public List<Blog> showBlogs(){
        //find all blogs
        return null;               //changes'
    }
    public int getAllBlogs(){
        return blogRepository1.findAll().size();
    }

    public void createAndReturnBlog(Integer userId, String title, String content) {
        //create a blog at the current time
        User user=userRepository1.findById(userId).get();

        Blog blog=new Blog(title,content);

        blog.setUser(user);

        //updating the blog details
        List<Blog> blogList=user.getBlogList();
        blogList.add(blog);

        //Updating the userInformation and changing its blogs
        user.setBlogList(blogList);
        user.setBlogList(blogList);
    }

    public Blog findBlogById(int blogId){
        //find a blog
        return null;
    }

    public void addImage(Integer blogId, String description, String dimensions){
        //add an image to the blog after creating it

       Blog blog=blogRepository1.findById(blogId).get();

       Image image=new Image(description,dimensions);

       List<Image> imageList=blog.getImageList();
       imageList.add(image);
       blog.setImageList(imageList);

       blogRepository1.save(blog);
    }

    public void deleteBlog(int blogId){
        //delete blog and corresponding images
        if(blogRepository1.findById(blogId).isPresent()){
            blogRepository1.deleteById(blogId);
        }
    }
}
