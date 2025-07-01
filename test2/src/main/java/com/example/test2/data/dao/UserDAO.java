package com.example.test2.data.dao;

import java.util.List;

import com.example.test2.data.entity.User;

public interface UserDAO {

    /*아이디를 가지고 user 레코드 찾기*/
    User select(String id);
    /*User를 db table에 insert 하기 */
    void insert(User user);
    /*db table에 있는 user 레코드를 모두 불러오기*/
    List<User> selectAll();
    /*파일을 올리고 나서 새 파일을 올릴때 insert전 테이블 내용 강제 지우기*/
    void deleteAll();
}
