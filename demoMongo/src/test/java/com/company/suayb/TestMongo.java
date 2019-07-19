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
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

import java.util.List;

@RunWith(SpringRunner.class)
@DataMongoTest
public class TestMongo {

    protected MockMvc mvc;

    @Autowired
    private RestTemplate restTemplate;

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
    public void test_Delete() {

        /*String uri = "/Delete/5d2ef6e89d51691644281cc4";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        assertEquals(content, "Product is deleted successsfully");*/

        User user1=new User();
        user1.setUserId("5d317aa39d516912fc005f96");
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
        assertNull(userRepository.findUserByUserName(user1.getUserId()));
        System.out.println("Deleted Successfully UserId= "+ user1.getUserId());
    }

    @Test
    public void test_Edit(){

        User user1= userRepository.findUserByUserName("Selami35");
        String old_phone=user1.getPhoneNumber();
        System.out.println("Before Edit PhoneNumber: "+ user1.getPhoneNumber());
        user1.setPhoneNumber("5398225566");

        userRepository.save(user1);

         user1= userRepository.findUserByUserName("Selami35");
        assertThat(user1.getPhoneNumber()).doesNotMatch(old_phone);
        System.out.println("After Edit PhoneNumber: " + user1.getPhoneNumber());
    }

    @Test
    public void isNotNullUser() {
        User user1= userRepository.findUserByUserName("Selami35");
        assertThat(user1.getName()).isNotNull();
    }
}
