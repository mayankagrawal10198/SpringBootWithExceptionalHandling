package com.example.userList.service;

import com.example.userList.exceptions.EmptyInputExceptions;
import com.example.userList.exceptions.NoUserFoundExceptions;
import com.example.userList.util.Address;
import com.example.userList.util.User;
import com.example.userList.util.UserList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

@Service
public class UserListServiceImpl implements UserListService {

    @Autowired
    private UserList users;

    @Override
    public void initUser(){
        users.allUsersList();
        users.allUsersMap();
    }

    @Override
    public boolean addUser(User user) {
        if(user.getName().trim().isEmpty() ||
                user.getEmail().trim().isEmpty() ||
                user.getAddress().getAddress().trim().isEmpty() ||
                user.getAddress().getState().trim().isEmpty() ||
                user.getAddress().getCity().trim().isEmpty() ||
                user.getId() < 1) {
            throw new EmptyInputExceptions("601","Input Fields are Empty or Invalid");
            //throw new IOException("Wrong");
        }
        if(users.dataMap.containsKey(user.getId())) return false;
        else {
            user.getName().trim();
            user.getEmail().trim();
            user.getAddress().getAddress().trim();
            user.getAddress().getState().trim();
            user.getAddress().getCity().trim();
            users.userList.add(user);
            users.dataMap.put(user.getId(), user);
            return true;
        }
    }

    @Override
    public boolean updateUser(int Id, User user) {
        if(users.dataMap.containsKey(Id)) {
            users.dataMap.remove(Id);
            return addUser(user);
        }
        else {
            throw new NoUserFoundExceptions("604", "No Such Id Found");
        }
    }

    @Override
    public List<User> showAllUserSortedById() {
//        Collections.sort(users.userList, new Comparator<User>() {
//            @Override
//            public int compare(User o1, User o2) {
//                return o1.getId() - o2.getId();
//            }
//        });
        Collections.sort(users.userList, Comparator.comparingInt(User::getId));
        return users.userList;
    }

    @Override
    public List<User> showAllUserSortedByName() {
//        Collections.sort(users.userList, new Comparator<User>() {
//            @Override
//            public int compare(User o1, User o2) {
//                return o1.getName().compareTo(o2.getName());
//            }
//        });
        Collections.sort(users.userList, Comparator.comparing(User::getName));
        return users.userList;
    }

    @Override
    public User showSingleUser(int Id) {
        if(users.dataMap.containsKey(Id)) {
            return users.dataMap.get(Id);
        }
        else {
            throw new NoUserFoundExceptions("604", "No Such Id Found");
        }
    }

    @Override
    public boolean removeUser(int Id) {
        if(users.dataMap.containsKey(Id)) {
            users.dataMap.remove(Id);
            return true;
        }
        else {
            throw new NoUserFoundExceptions("604", "No Such Id Found");
        }
    }
}
