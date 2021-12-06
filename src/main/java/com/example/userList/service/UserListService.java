package com.example.userList.service;

import com.example.userList.exceptions.EmptyInputExceptions;
import com.example.userList.util.User;

import java.util.List;

public interface UserListService {
    public boolean addUser(User user) throws EmptyInputExceptions;
    public boolean updateUser(int id, User user);
    public List<User> showAllUserSortedById();
    public List<User> showAllUserSortedByName();
    public User showSingleUser(int Id);
    public boolean removeUser(int id);
    public void initUser();
}
