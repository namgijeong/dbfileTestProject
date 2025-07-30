package com.example.test3.data.repository;

import com.example.test3.data.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String>, UserRepositoryCustom {

    Optional<User> findByIdAndPwd(String id, String pwd);

    Optional<User> findByPwd(String pwd);

    Page<User> findAllByOrderByRegDateDesc(Pageable pageable);

    long count();


    //어느 필드든지 null일수 있기 때문에 가짓수가 많다.
    //OR을 사용하여 조건을 동적으로 만들기
    //두 조건 중 하나가 참이면 그 쿼리 조건이 참이 되어 결과가 반환
    //ex (:category IS NULL OR R.category = :category)
    // category가 null인 경우:
    //(:category IS NULL)이 true가 되어 R.category = :category 부분은 무시
    //category가 null이 아닌 경우:
    //(:category IS NULL)은 false가 되고, 대신 R.category = :category 조건이 적용
    //JPQL에서는 백틱(`)이나 큰따옴표(" ")를 사용하여 예약어를 필드 이름이나 매개변수로 처리
    //JPQL에서는 +를 이용해 문자열을 이어붙일 때 각 조건 사이에 공백을 넣어야 쿼리가 제대로 실행됩니다. 공백이 없는 경우, 문자열을 이어붙일 때 문법 오류가 발생할 수 있습니다.
    //date_trunc('day', 변수명)
    //입력 값: TIMESTAMP 값 (예: 2025-07-22 14:35:20)
    //반환 값: TIMESTAMP 값이 되며, 시간은 00:00:00으로 설정됩니다.
    // " AND (:regDate IS NULL OR ( u.regDate >= :regDate AND u.regDate <= :regDateEnd ))"
    //" AND (:regDate IS NULL OR  FUNCTION('TO_CHAR', u.regDate, 'YYYY-MM-DD') >= :regDate) " +
    // " AND (:regDateEnd IS NULL OR FUNCTION('TO_CHAR', u.regDate, 'YYYY-MM-DD') <= :regDateEnd)"
    //" AND (:dateStart IS NULL OR  ( u.regDate >= :dateStart) ) " +
    // " AND (:dateEnd IS NULL OR ( u.regDate <= :dateEnd) )"
    //   " AND (:dateStart IS NULL OR  FUNCTION('TO_CHAR', u.regDate, 'YYYY-MM-DD HH:mm:ss') >= :dateStart) " +
    //    " AND (:dateEnd IS NULL OR FUNCTION('TO_CHAR', u.regDate, 'YYYY-MM-DD HH:mm:ss') <= :dateEnd)"

    @Query("select u from User u where " +
            " (:id IS NULL OR u.id LIKE %:id%)" +
            " AND (:name IS NULL OR u.name LIKE %:name%) "+
            " AND (:level IS NULL OR u.level = :level) "+
            " AND (:desc1 IS NULL OR  u.`desc` LIKE %:desc1%) " +
            " AND (:dateStart IS NULL OR  FUNCTION('date_trunc', 'day', u.regDate) >= :dateStart) " +
            " AND (:dateEnd IS NULL OR FUNCTION('date_trunc', 'day', u.regDate) <= :dateEnd)"
    )
    List<User> findAllBySearchUserDTO(@Param("id") String id,
                                      @Param("name") String name,
                                      @Param("level") String level,
                                      @Param("desc1") String desc,
                                      @Param("dateStart") LocalDateTime regDate,
                                      @Param("dateEnd") LocalDateTime regDateEnd);
}

