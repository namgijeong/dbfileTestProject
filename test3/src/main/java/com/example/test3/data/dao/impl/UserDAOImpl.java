package com.example.test3.data.dao.impl;

import java.util.List;
import java.util.Optional;

import com.example.test3.data.dto.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import com.example.test3.data.dao.UserDAO;
import com.example.test3.data.entity.User;
import com.example.test3.data.repository.UserRepository;

@Component
@RequiredArgsConstructor
@Slf4j
public class UserDAOImpl implements UserDAO {

    private final UserRepository userRepository;

    /*로그인 성공여부*/
    @Override
    public Optional<User> select(String id) {

        Optional<User> optionalUser = userRepository.findById(id);

        return optionalUser;
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

    /*파일을 올리고 나서 새 파일을 올릴때 insert전 테이블 내용 강제 지우기*/
    @Override
    public void deleteAll() {
        userRepository.deleteAll();
    }

    /*시간 최신순 10개만 user 정보 출력*/
    @Override
    public List<User> select10Users(Long pageNumber) {
        //페이지는 0부터 시작
        Page<User> userPage= userRepository.findAllByOrderByRegDateDesc(PageRequest.of((int)(pageNumber-1), 10));
        log.info("paging jpa 결과 : "+userPage.getContent());
        List<User> userList = userPage.getContent();

        return userList;
    }

    /**
     * dhtmlx8용
     * 페이지 진입시 전체 유저리스트 뽑기
     * @return User List
     */
    @Override
    public List<User> selectAllUsers() {
        //페이지는 0부터 시작
        List<User> userList= userRepository.findAllByOrderByRegDateDesc();

        return userList;
    }

    /*전체 회원정보 개수 불러오기 */
    @Override
    public long countUsers() {
        long count = userRepository.count();
        return count;
    }

    //querydsl 버전 조건에 맞는 페이지별 회원 목록
     @Override
    public List<UserDTO> selectUsersBySearchUserDTO(SearchUserDTO searchUserDTO, Long pageNumber) {
        //페이지는 0부터 시작
        List<UserDTO> searchUserDTOResponseList =  userRepository.searchUsers(searchUserDTO, PageRequest.of((int)(pageNumber-1), 10));

        return searchUserDTOResponseList;
    }

    /**
     * 페이징버전 아님
     * 검색 결과에 맞는 회원 리스트들 뽑기
     * @param searchUserDTO UserDTO
     * @return UserDTO List
     */
//    @Override
//    public List<UserDTO> selectUsersBySearchUserDTO(SearchUserDTO searchUserDTO) {
//        //페이지는 0부터 시작
//        List<UserDTO> searchUserDTOResponseList =  userRepository.searchUsers(searchUserDTO);
//
//        return searchUserDTOResponseList;
//    }


    /**
     * query dsl 검색결과 회원 리스트 전체 개수
     * @param searchUserDTO
     * @return Long
     */
    @Override
    public Long selectUsersCountBySearchUserDTO(SearchUserDTO searchUserDTO) {
        Long count =  userRepository.searchUsersCount(searchUserDTO);
        return count;
    }


    /**
     * 회원수정을 할때
     * @param registerUserDTO
     */
    @Override
    public void updateUser(RegisterUserDTO registerUserDTO) {
        User user = userRepository.findById(registerUserDTO.getId()).orElse(null);

        user.setPwd(registerUserDTO.getPwd());
        user.setName(registerUserDTO.getName());
        user.setLevel(registerUserDTO.getLevel());
        user.setDesc(registerUserDTO.getDesc());
        user.setRegDate(registerUserDTO.getRegDate());

        userRepository.save(user);
    }

    //jpql 버전
//    @Override
//    public List<User> selectUsersBySearchUserDTO(SearchUserDTO searchUserDTO) {
//
//        return userRepository.findAllBySearchUserDTO(searchUserDTO.getId(),searchUserDTO.getName(), searchUserDTO.getLevel(), searchUserDTO.getDesc(), searchUserDTO.getRegDate(), searchUserDTO.getRegDate());
//    }
}
