package com.example.userList.util;

import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class UserList {
    public List<User> userList = new LinkedList<>();
    public Map<Integer, User> dataMap = new HashMap<>();

    public UserList() {

    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public Map<Integer, User> getDataMap() {
        return dataMap;
    }

    public void setDataMap(Map<Integer, User> dataMap) {
        this.dataMap = dataMap;
    }

    public void allUsersList(){
        List<User> list = new LinkedList<>();
        list.add(new User("Mayank", 1, "1234", new Address("123","MP","Bhopal")));
        list.add(new User("Prakhar", 2, "1234adsfa",new Address("1234","Rajasthan","Udaipur")));
        list.add(new User("Vijay", 3, "1234adfas",new Address("12345","UP","Kanpur")));
        list.add(new User("Subhashish", 4, "12asdfas34",new Address("12367","Orrisa","Bhuvaneshawar")));
        list.add(new User("Prakash", 5, "123adafa4",new Address("12389","MP","Rewa")));
        this.userList.addAll(list);
    }

    public void allUsersMap(){
        Map<Integer, User> map = new HashMap<>();
        for(int i = 0; i<this.userList.size();i++) {
            map.put(this.userList.get(i).getId(),this.userList.get(i));
        }
        this.dataMap.putAll(map);
    }

}
