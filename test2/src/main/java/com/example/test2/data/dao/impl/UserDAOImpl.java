package com.example.test2.data.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.test2.data.dao.UserDAO;
import com.example.test2.data.entity.User;
import com.example.test2.data.repository.UserRepository;

@Component
public class UserDAOImpl implements UserDAO {

    private final UserRepository userRepository;

    @Autowired
    public UserDAOImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /*아이디를 가지고 user 레코드 찾기*/
    @Override
    public User select(String id) {
        User selectedUser = userRepository.getById(id);
        return selectedUser;
    }

    /*User를 db table에 insert 하기 */
    @Override
    public void insert(User user) {
        userRepository.save(user);
    }

    /*db table에 있는 user 레코드를 모두 불러오기*/
    @Override
    public List<User> selectAll() {
        List<User> userList = userRepository.findAll();
        return userList;
    }

    @Override
    public void deleteAll() {
        userRepository.deleteAll();
    }
}
