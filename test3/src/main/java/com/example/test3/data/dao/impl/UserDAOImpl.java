package com.example.test3.data.dao.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import com.example.test3.data.dao.UserDAO;
import com.example.test3.data.dto.SearchUserDTO;
import com.example.test3.data.dto.SearchUserDTOResponse;
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
//        Optional<User> optionalUser = userRepository.findByIdAndPwd(id, pwd);
//        User user = null;
//        if (optionalUser.isPresent()) { // 아이디랑 비번 둘다 맞으면
//            user = optionalUser.get();
//
//        } else {
//            Optional<User> optionalUser2 = userRepository.findById(id);
//            if (optionalUser2.isPresent()) {  //아이디만 맞으면
//                user = optionalUser2.get();
//            } else {
//                Optional<User> optionalUser3 = userRepository.findByPwd(pwd);
//                if (optionalUser3.isPresent()) {
//                    user = optionalUser3.get(); //비밀번호만 맞으면
//                }
//            }
//
//        }

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

    /*전체 회원정보 개수 불러오기 */
    @Override
    public long countUsers() {
        long count = userRepository.count();
        return count;
    }

    //querydsl 버전 조건에 맞는 페이지별 회원 목록
    @Override
    public List<SearchUserDTOResponse> selectUsersBySearchUserDTO(SearchUserDTO searchUserDTO, Long pageNumber) {
        //페이지는 0부터 시작
        List<SearchUserDTOResponse> searchUserDTOResponseList =  userRepository.searchUsers(searchUserDTO, PageRequest.of((int)(pageNumber-1), 10));

        return searchUserDTOResponseList;
    }

    @Override
    public Long selectUsersCountBySearchUserDTO(SearchUserDTO searchUserDTO) {
        Long count =  userRepository.searchUsersCount(searchUserDTO);
        return count;
    }

    //jpql 버전
//    @Override
//    public List<User> selectUsersBySearchUserDTO(SearchUserDTO searchUserDTO) {
//
//        return userRepository.findAllBySearchUserDTO(searchUserDTO.getId(),searchUserDTO.getName(), searchUserDTO.getLevel(), searchUserDTO.getDesc(), searchUserDTO.getRegDate(), searchUserDTO.getRegDate());
//    }
}
