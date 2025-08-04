package com.example.test3.data.dao;

import java.util.List;
import java.util.Optional;

import com.example.test3.data.dto.SearchUserDTO;
import com.example.test3.data.dto.SearchUserDTOResponse;
import com.example.test3.data.dto.UserDTO;
import com.example.test3.data.entity.User;

public interface UserDAO {
    /*아이디와 비번을 가지고 user 레코드 찾기*/
    Optional<User> select(String id);

    /*User를 db table에 insert 하기 */
    void insert(User user);

    /*db table에 있는 user 레코드를 모두 불러오기*/
    List<User> selectAll();

    /*파일을 올리고 나서 새 파일을 올릴때 insert전 테이블 내용 강제 지우기*/
    void deleteAll();

    /*시간 최신순 10개만 user 정보 출력*/

    /**
     * 페이징 기법적용
     * 시간 최신순 10개만 user 정보 출력
     * @param pageNumber
     * @return User List
     */
    List<User> select10Users(Long pageNumber);

    /**
     * 페이지 진입시 전체 유저리스트 뽑기
     * @return User List
     */
    List<User> selectAllUsers();


    /**
     * 전체 회원정보 개수 불러오기
     * @return long
     */
    long countUsers();


    /* 검색 결과 회원 리스트 */
//    List<User> selectUsersBySearchUserDTO(SearchUserDTO searchUserDTO);

    /**
     * 페이징 기법
     * query dsl 검색결과 회원 리스트 페이지단위
     * @param searchUserDTO
     * @param pageNumber
     * @return UserDTO List
     */
   List<UserDTO> selectUsersBySearchUserDTO(SearchUserDTO searchUserDTO, Long pageNumber);

    /**
     *  페이징 아님
     * 검색 결과에 맞는 회원 리스트들 뽑기
     * @param searchUserDTO UserDTO
     * @return UserDTO List
     */
//    List<UserDTO> selectUsersBySearchUserDTO(SearchUserDTO searchUserDTO);


    /**
     * query dsl 검색결과 회원 리스트 전체 개수
     * @param searchUserDTO
     * @return Long
     */
    Long selectUsersCountBySearchUserDTO(SearchUserDTO searchUserDTO);
}
