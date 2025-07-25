package org.example;

import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;

public class UserService {
    private Map<String,User> userMap = new HashMap<>();
    private User currUser=null;

    public boolean registerUser(String username,String password,String fullName,String contact){
        if (userMap.containsKey(username)){
            System.out.println("Username already taken");
            return false;
        }else {
            User user = new User(username,password,fullName,contact);
            userMap.put(username,user);
            System.out.println("Registration successfgull");
            return true;

        }
    }

    public boolean loginUser(String username,String password){
        if (!userMap.containsKey(username)){
            System.out.println("Username not found");
            return false;
        }
        User user = userMap.get(username);
        if (!user.getPassword().equals(password)){
            System.out.println("Incorrect password");
            return false;
        }

        currUser =user;
        System.out.println("Welcome:"+currUser.getFullName());
        return true;
    }

    public void logOut(){
        if (currUser!=null){
            System.out.println("Logged out" + currUser.getFullName());
        }
        currUser = null;
    }

    public User getCurrUser(){
        return currUser;
    }
    public boolean isLoggin(){
        return currUser!=null;
    }

}


