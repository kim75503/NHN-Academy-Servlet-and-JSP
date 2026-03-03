package com.nhnacademy.reflection;

import java.util.ArrayList;
import java.util.List;

public class UserRepository {
    private List<User> users = new ArrayList<>();

    private final UserRepository userRepository;


    public User findByName(String userName){
        return users.stream()
                .filter(o->o.getUserName().equals(userName)).findFirst().orElse(null);
    }

    public void save(User user){
        this.users.add(user);
    }

/*    public void setUserRepository(UserRepository userRepository){
        this.userRepository = userRepository;
    }*/

    public UserRepository(final UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public User getUser(String userName){
        return userRepository.findByName(userName);
    }
}
