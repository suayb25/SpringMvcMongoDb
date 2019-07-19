package com.company.suayb.service;

import com.company.suayb.model.User;
import com.company.suayb.repository.UserRepository;
import com.company.suayb.shared.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    List<User> findAll();
    void delete(String id);
    void save(User user);
    User get(String id);

/*    @Autowired
    private UserRepository userRepository;

    public List<User> findAll(){
    return userRepository.findAll();
    }

    public void delete(String id){
        userRepository.deleteById(id);
    }

    public void save(User user){
        userRepository.save(user);
    }
    public User get(String id) {
        return userRepository.findById(id).get();
    }*/

}
