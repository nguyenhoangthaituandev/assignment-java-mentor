package org.example.services;

import org.example.constants.UserStatus;
import org.example.models.User;

import java.util.HashMap;
import java.util.Map;

import static org.example.constants.Constant.sc;

public class UserServiceImp implements IUserService{
    private Map<String,User> users=new HashMap<>();

    public UserServiceImp(){
        users.put("admin",new User("admin","123456"));
    }

    @Override
    public boolean register() {
        System.out.print("Enter your username: ");
        String username=sc.nextLine();
        System.out.print("Enter your password: ");
        String password=sc.nextLine();
        if(users.containsKey(username)){
            System.out.println("Username exists, Please enter another username");
            return false;
        }

        User newUser=new User(username,password);
        users.put(username,newUser);
        System.out.println("Register successfully");
        return true;
    }

    @Override
    public User login(String username,String password) {
        User user=users.get(username);

        if(user==null){
            System.out.println("Wrong username");
            return null;
        }
        if(!user.getPassword().equals(password)){
            user.increaseFailedLoginAttempts();
            System.out.printf("You enter password failed %s times %n",user.getFailedLoginAttempts());
            if(user.getFailedLoginAttempts()>=3){
                System.out.println("Your account is suspended cause enter wrong 3 time");
            }
            return null;
        }

        if(user.getStatus() == UserStatus.SUSPENDED){
            System.out.println("Your account is suspended");
            return null;
        }

        if(user.getStatus()==UserStatus.BANNED){
            System.out.println("Your account is banned");
            return null;
        }

        user.setFailedLoginAttempts(0);
        System.out.println("Login successfully");
        return user;
    }
}
