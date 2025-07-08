package com.example.test2.data.dao.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

import com.example.test2.data.dao.UserDAO;
import com.example.test2.data.entity.User;
import com.example.test2.data.repository.UserRepository;
import com.example.test2.exception.UserNotFound;

@Component
@RequiredArgsConstructor
public class UserDAOImpl implements UserDAO {

    private final UserRepository userRepository;

    /*로그인 성공여부*/
    @Override
    public boolean select(String id, String pwd) {
        Optional<User> optionalUser = userRepository.findByIdAndPwd(id, pwd);
        //User user;
        if(optionalUser.isPresent()) {
            //user = optionalUser.get();
            return true;
        } else{
            //throw new UserNotFound("로그인 실패");
            return false;
        }
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
