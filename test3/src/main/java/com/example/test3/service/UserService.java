package com.example.test3.service;

import java.util.List;

import jakarta.servlet.http.HttpSession;
import org.springframework.web.multipart.MultipartFile;

import com.example.test3.data.dto.*;
import com.example.test3.exception.FailFileOpen;
import com.example.test3.exception.WrongFileExtension;

public interface UserService {
    /*업로드 된 파일을 가지고 db table에 저장한다.*/
    public ProcessTotalResultDTO userInsert(MultipartFile file) throws FailFileOpen, WrongFileExtension;

    /*db table에 있는 user 레코드를 모두 조회한다.*/
    public List<UserDTO> findAll();

    /*db table에 있는 user 레코드를 모두 지운다.*/
    public void deleteAll();

    /*user 로그인을 수행한다.*/
    public ProcessResultDTO userLogin(LoginUserDTO loginUserDTO);

    /*
        등록 최신순 user 10명을 찾는다.
        등록된 전체 유저 개수를 세서 페이징 버튼들 블록 처리
    */

    /**
     * 페이징 버전임
     * 모든 회원을 최신 10명만 조회한다.
     * @param pageNumber
     * @return  UserPagingResultDTO => UserDTOList, ButtonBlockDTO가 포함
     */
    public UserPagingResultDTO<UserDTO> select10Users(long pageNumber);

    /**
     * 페이징 버전임
     * @param searchUserDTO
     * @return UserPagingResultDTO => UserDTOList, ButtonBlockDTO가 포함
     */
    public UserPagingResultDTO<UserDTO>  selectUsersBySearchUserDTO(SearchUserDTO searchUserDTO);


    /**
     * 페이징 버전 아님
     * 모든 회원을 조회한다.
     * @return UserDTO LIST
     */
    //public UserPagingResultDTO<UserDTO> selectAllUsers();

    /**
     * 페이징 버전 아님
     * 검색 dto를 가지고 회원들을 찾는다.
     * @param searchUserDTO
     * @return UserDTO를 담은 UserPagingResultDTO
     */
    //public UserPagingResultDTO<UserDTO> selectUsersBySearchUserDTO(SearchUserDTO searchUserDTO);


    /**
     * 수정페이지로 이동할때 수정버튼 누른 해당 회원의 정보 전체를 찾아 반환한다.
     * @param searchUserDTO
     * @return ProcessResultDTO
     */
    public ProcessResultDTO findUser(SearchUserDTO searchUserDTO);

    /**
     * 회원가입시, 아이디가 중복되었는지 체크한다.
     * @param id
     * @return ProcessResultDTO
     */
    public ProcessResultDTO isIDDuplicated(String id);
}
