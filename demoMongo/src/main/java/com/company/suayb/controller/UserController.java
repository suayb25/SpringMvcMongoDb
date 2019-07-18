package com.company.suayb.controller;

import com.company.suayb.model.ReCaptchaResponse;
import com.company.suayb.model.User;
import com.company.suayb.service.UserService;
import com.company.suayb.shared.UserDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class UserController {

    Logger logger= LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    RestTemplate restTemplate;

    @RequestMapping("/")
    public String findAllUsers(Model model){
        logger.info("Getting All Users.");
        model.addAttribute("userList",userService.findAll());
        User user=new User();
        user.setName("");
        user.setSurname("");
        user.setPhoneNumber("");
        user.setUserName("");
        model.addAttribute("user",user);
        return "user";
    }

    @RequestMapping("/delete/{id}")
    public String deleteStudent(@PathVariable(name="id")String id,Model model) {
        userService.delete(id);
        User user2=new User();
        user2.setName("");
        user2.setSurname("");
        user2.setPhoneNumber("");
        user2.setUserName("");
        model.addAttribute("user",user2);
        model.addAttribute("userList",userService.findAll());
        return "user";
    }

    @RequestMapping(value="/save",method= RequestMethod.POST)
    public String saveUser(@Valid @ModelAttribute("user")User user, @RequestParam(name="g-recaptcha-response")String captchaResponse ) {

        /*ModelMapper modelMapper=new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        UserDto userDto= modelMapper.map(user, UserDto.class);*/

        String url="https://www.google.com/recaptcha/api/siteverify";
        String params="?secret=6LdyJq4UAAAAALbJwOdZFSjDt7F4Yt-gsaTMo8t-&response="+captchaResponse;

        ReCaptchaResponse reCaptchaResponse=restTemplate.exchange(url+params, HttpMethod.POST,null,ReCaptchaResponse.class).getBody();
        if(reCaptchaResponse.isSuccess()){
            userService.save(user);
            return "redirect:/";
        }
        return "redirect:/";




    }

    @RequestMapping("/Delete/{id}")
    public void deleteStudent1(@PathVariable(name="id")String id) {

        userService.delete(id);

    }

}
