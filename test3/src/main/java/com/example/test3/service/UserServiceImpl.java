package com.example.test3.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import com.example.test3.data.dao.UserDAO;
import com.example.test3.data.dto.*;
import com.example.test3.data.entity.User;
import com.example.test3.exception.FailFileOpen;
import com.example.test3.exception.StringTokenException;
import com.example.test3.exception.WrongFileExtension;
import com.example.test3.utility.Utility;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserDAO userDAO;

    /**
     * 업로드 된 파일을 가지고 db table에 저장한다.
     * @param file 업로드 한 파일
     * @return processTotalResultDTO
     * @throws FailFileOpen
     * @throws WrongFileExtension
     */
    @Override
    public ProcessTotalResultDTO userInsert(MultipartFile file) throws FailFileOpen, WrongFileExtension {
        String originalFileName = file.getOriginalFilename();
        System.out.println(originalFileName);

        if (originalFileName != null && originalFileName.matches("^.+\\.(dbfile)$")) {
            log.info("유효한 파일");

            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8))) {
                int count = 0;
                int successCount = 0;
                String line;
                List<ProcessResultDTO> processResultDTOList = new ArrayList<>();
                ProcessTotalResultDTO processTotalResultDTO = null;

                /*
                    파일 한줄씩 읽어라.
                    각 한줄마다 / 기준으로 분리하라.
                    형식을 바꾸고 DTO에 집어넣은 후, 엔티티 컬럼에 맞게 변화해라.
                 */
                while ((line = reader.readLine()) != null) {
                    log.info("읽은 줄:" +line);

                    ProcessResultDTO processResultDTO = null;
                    count++;

                    try {
                        //슬래시 단위로 나누기
                        String[] parts = line.split("/");

                        //각 문자열 파트 개수가 맞는지 검사
                        boolean isStringLengthValid = Utility.checkStringCount(parts);
                        if (!isStringLengthValid) {
                            throw new StringTokenException("/로 구분한 칼럼 개수가 잘못되었습니다.");
                        }

                        //토큰이 공백이면 null로 만들어준다.
                        String[] clearParts = Utility.makeEmptyStringNull(parts);
                        //이미 여기서 생성할때 멤버변수에 집어넣는 값이 잘못되면 예외처리가 생김
                        UserDTO userDTO =  new UserDTO(clearParts);

                        User user = UserDTO.makeUserDTOToUser(userDTO);
                        userDAO.insert(user);

                        processResultDTO = ProcessResultDTO.builder()
                                .successLine(count)
                                .successFlag(true)
                                .build();

                        successCount++;

                    } catch (Exception e) { // 개별 라인에서 오류 발생해도 다음 줄로
                        log.warn("exception 종류 : "+e.getClass().getName());
                        log.warn(e.getMessage());
                        log.warn("라인 오류: " + line);


                        processResultDTO = ProcessResultDTO.builder()
                                .failLine(count)
                                .failText(line)
                                .successFlag(false)
//                                            .exceptionMessage(e.getMessage())
                                .build();

                    } finally {
                        processResultDTOList.add(processResultDTO);

                    }
                }

                processTotalResultDTO = ProcessTotalResultDTO.builder()
                        .processResultDTOList(processResultDTOList)
                        .totalCount(count)
                        .successCount(successCount)
                        .build();

                return processTotalResultDTO;

            } catch (IOException e2) {
                e2.printStackTrace();

                log.warn("파일 열기 실패");
                throw new FailFileOpen("파일 열기 실패");
            }

        } else {
            log.warn("잘못된 파일 확장자");
            throw new WrongFileExtension("잘못된 파일 확장자");

        }
    }


    /**
     * db table에 있는 user 레코드를 모두 조회한다.
     * @return List<UserDTO>
     */
    @Override
    public List<UserDTO> findAll() {
        List<User> userList = userDAO.selectAll();
        List<UserDTO> userDTOList = userList.stream().map(user -> new UserDTO(user)).collect(Collectors.toList());
        return userDTOList;

    }


    /**
     * db table에 있는 user 레코드를 모두 지운다.
     */
    @Override
    public void deleteAll() {
        userDAO.deleteAll();
    }


    /**
     * user 로그인을 수행한다.
     * @param loginUserDTO
     * @return ProcessResultDTO  => UserDTO와 ErrorField가 담겨있다.
     */
    @Override
    public ProcessResultDTO userLogin(LoginUserDTO loginUserDTO) {
        Optional<User> optionalUser =  userDAO.select(loginUserDTO.getId());

        ProcessResultDTO processResultDTO = new ProcessResultDTO();

        if (optionalUser.isEmpty()) {
            log.info("아이디가 틀린곳으로 들어왔다.");
            //processResultDTO.setErrorField("아이디가 틀렸습니다.", LoginField.ID);
            processResultDTO.setErrorMessage(LoginField.ID.getMessage());
            return processResultDTO;
        }

        // 아이디로 조회하기 성공하여 아이디는 맞은 상태
        User user = optionalUser.get();

        if (!user.isPwdCheckSuccess(loginUserDTO.getPwd())) {  //비밀번호 틀림
            //processResultDTO.setErrorField("비밀번호가 틀렸습니다.", LoginField.PWD);
            processResultDTO.setErrorMessage(LoginField.PWD.getMessage());
            return processResultDTO;
        }

        UserDTO userDTO = new UserDTO(user);
        processResultDTO.setUserDTO(userDTO);
        processResultDTO.setSuccessFlag(true);
        return processResultDTO;
    }


    /**
     * 페이징 버전
     * 등록 최신순 user 10명을 찾는다.
     * 등록된 전체 유저 개수를 세서 페이징 버튼들 정보도 넘겨준다.
     * @param pageNumber 페이지 번호
     * @return UserPagingResultDTO => UserDTOList, ButtonBlockDTO가 포함
     */
    @Override
    public UserPagingResultDTO<UserDTO> select10Users(long pageNumber) {
        //현재 페이지 번호에 맞는 최신순 user 10명을 뽑아온다.
        List<User> userList =  userDAO.select10Users(pageNumber);
        List<UserDTO> userDTOList = userList.stream().map(user -> new UserDTO(user)).collect(Collectors.toList());

        //총 게시물 갯수를 세서 페이징버튼들 처리 ButtonBlockDTO를 생성한다.
        long totalUsers = userDAO.countUsers();
        ButtonBlockDTO buttonBlockDTO = Utility.makeButtonBlockDTO(pageNumber, totalUsers);

        UserPagingResultDTO<UserDTO> userPagingResultDTO = UserPagingResultDTO.<UserDTO>builder()
                .userDTOList(userDTOList)
                .buttonBlockDTO(buttonBlockDTO)
                .build();
        return userPagingResultDTO;
    }


    /**
     * 페이징 버전 아님
     * 페이지 진입시 전체 회원들 목록을 조회해서 준다.
     * @return UserPagingResultDTO => UserDTOList, ButtonBlockDTO가 포함
     */
//    @Override
//    public UserPagingResultDTO<UserDTO> selectAllUsers() {
//        //현재 페이지 번호에 맞는 최신순 user 10명을 뽑아온다.
//        List<User> userList =  userDAO.selectAllUsers();
//        List<UserDTO> userDTOList = userList.stream().map(user -> new UserDTO(user)).collect(Collectors.toList());
//
//        UserPagingResultDTO<UserDTO> userPagingResultDTO = UserPagingResultDTO.<UserDTO>builder()
//                .userDTOList(userDTOList)
//                .build();
//        return userPagingResultDTO;
//    }


    /**
     * 페이징 버전임
     * @param searchUserDTO
     * @return  UserPagingResultDTO => UserDTOList, ButtonBlockDTO가 포함
     */
    @Override
    public UserPagingResultDTO<UserDTO>  selectUsersBySearchUserDTO(SearchUserDTO searchUserDTO) {
        //jpql 사용
        //List<User> userList = userDAO.selectUsersBySearchUserDTO(searchUserDTO);
        //log.info("userList : "+userList);

        //query dsl 사용
        long pageNumber = searchUserDTO.getPageNumber();
        List<UserDTO>  searchUserDTOResponseList =  userDAO.selectUsersBySearchUserDTO(searchUserDTO, pageNumber);
        log.info("userList : "+searchUserDTOResponseList.toString());

        //검색 조건에 맞는 총 게시물 갯수를 세서 페이징버튼들 처리 ButtonBlockDTO를 생성한다.
        long totalUsers = userDAO.selectUsersCountBySearchUserDTO(searchUserDTO);
        ButtonBlockDTO buttonBlockDTO = Utility.makeButtonBlockDTO(pageNumber, totalUsers);


        UserPagingResultDTO<UserDTO> userPagingResultDTO = UserPagingResultDTO.<UserDTO>builder()
                .userDTOList(searchUserDTOResponseList)
                .buttonBlockDTO(buttonBlockDTO)
                .build();
        return userPagingResultDTO;
    }


    /**
     * 페이징 버전 아님
     * 검색조건을 가지고 회원 리스트 뽑기
     * @param searchUserDTO UserDTO
     * @return UserPagingResultDTO
     */
//    @Override
//    public UserPagingResultDTO<UserDTO>  selectUsersBySearchUserDTO(SearchUserDTO searchUserDTO) {
//        //jpql 사용
//        //List<User> userList = userDAO.selectUsersBySearchUserDTO(searchUserDTO);
//        //log.info("userList : "+userList);
//
//        //query dsl 사용
//        List<UserDTO>  searchUserDTOResponseList =  userDAO.selectUsersBySearchUserDTO(searchUserDTO);
//        log.info("userList : "+searchUserDTOResponseList.toString());
//
//        UserPagingResultDTO<UserDTO> userPagingResultDTO = UserPagingResultDTO.<UserDTO>builder()
//                .userDTOList(searchUserDTOResponseList)
//                .build();
//        return userPagingResultDTO;
//    }


    /**
     * 수정페이지로 이동할때 수정버튼 누른 해당 회원의 정보 전체를 찾아 반환한다.
     * @param searchUserDTO
     * @return ProcessResultDTO
     */
    @Override
    public ProcessResultDTO findUser(SearchUserDTO searchUserDTO) {
        Optional<User> optionalUser =  userDAO.select(searchUserDTO.getId());

        ProcessResultDTO processResultDTO = new ProcessResultDTO();

        if (optionalUser.isEmpty()) {
            log.info("잘못된 수정페이지 접근. 해당 회원 아이디로 접근실패");

            processResultDTO.setErrorMessage("잘못된 수정페이지 접근. 해당 회원 아이디로 접근실패");
            return processResultDTO;
        }

        //회원 아이디로 접근 성공
        User user = optionalUser.get();

        UserDTO userDTO = new UserDTO(user);
        processResultDTO.setUserDTO(userDTO);
        processResultDTO.setSuccessFlag(true);
        return processResultDTO;
    }


    /**
     * 회원가입시, 아이디가 중복되었는지 체크한다.
     * @param id
     * @return ProcessResultDTO
     */
    @Override
    public ProcessResultDTO isIDDuplicated(String id) {

        Optional<User> optionalUser =  userDAO.select(id);

        ProcessResultDTO processResultDTO = new ProcessResultDTO();

        if (optionalUser.isEmpty()) {
            log.info("아이디가 중복되지 않습니다.");

            processResultDTO.setErrorMessage(RegisterField.NOT_DUPLICATED_ID.getMessage());
            processResultDTO.setSuccessFlag(true);
            return processResultDTO;
        }

        processResultDTO.setErrorMessage(RegisterField.DUPLICATED_ID.getMessage());

        return processResultDTO;
    }


}
