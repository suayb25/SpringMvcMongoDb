package com.company.suayb;

import com.company.suayb.model.User;
import com.company.suayb.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@DataMongoTest
public class TestMongo {

    protected MockMvc mvc;


    @Autowired
    private UserRepository userRepository;

    @Test
    public void test_AllUser(){
        List<User> userList =userRepository.findAll();
        for(User user:userList){
            System.out.println("UserId: "+ user.getUserId());
        System.out.println("UserName: "+ user.getUserName());
            System.out.println("Name: "+user.getName());
            System.out.println("Surname: "+ user.getSurname());
        }
    }
    @Test
    public void test_Delete() throws Exception {

        User user1=new User();
        user1.setUserId("5d2edb749198b11710713b81");
        List<User> userList =userRepository.findAll();
        for(User user:userList){
            if(user.getUserId().equals(user1.getUserId())){
                System.out.println("UserId: "+ user.getUserId());
                System.out.println("Deleted UserName: "+ user.getUserName());
                System.out.println("Deleted Name: "+user.getName());
                System.out.println("Deleted Surname: "+ user.getSurname());
                user1=user;
                break;
            }
        }
        userRepository.deleteById(user1.getUserId());
        System.out.println("Deleted Successfully UserId= "+ user1.getUserId());
    }



}
