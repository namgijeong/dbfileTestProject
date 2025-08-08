package com.example.test3.data.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.example.test3.data.entity.QUser;
import com.example.test3.data.dto.*;


@Repository
@RequiredArgsConstructor
@Slf4j
public class UserRepositoryCustomImpl implements UserRepositoryCustom{
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<UserDTO> searchUsers(SearchUserDTO dto, Pageable pageable) {
    //public List<UserDTO> searchUsers(SearchUserDTO dto) {
    //Q 클래스는 엔티티 클래스의 메타 정보를 담고 있는 클래스
        //타입 안전한 쿼리작성을 할 수 있다
        QUser user = QUser.user;

        //where에 여러 조건을 넘겨주는 방법은 크게 2가지
        //1.BooleanBuilder를 생성하여 and()에 BooleanExpression을 추가하는 방법
        //2.여러개의 BooleanExpression을 파라미터로 넘기는 방법
        //BooleanBuilder는 JPA에서 사용하는 동적 쿼리를 작성할 수 있는 빌더 클래스
        //and()와 or() 메서드는 BooleanBuilder 객체에 조건을 추가할 때 사용
        BooleanBuilder booleanBuilder = new BooleanBuilder();

        booleanBuilder = makeSearchUsersBooleanBuilder(booleanBuilder, user, dto);

        System.out.println("검색조건 id: " + dto.getId());
        System.out.println("검색조건 name: " + dto.getName());
        System.out.println("검색조건 level: " + dto.getLevel());
        System.out.println("검색조건 desc: " + dto.getDesc());
        System.out.println("검색조건 regDate: " + dto.getRegDate());

        List<UserDTO> searchUserDTOResponseList = jpaQueryFactory
                //select() 안에 new 키워드를 사용하여 원하는 DTO를 반환하도록 설정
                .select(new QUserDTO(
                        user.id,
                        user.pwd,
                        user.name,
                        user.level,
                        user.desc,
                        user.regDate
                ))
                .from(user)
                .where(booleanBuilder)
                //OFFSET은 몇 번째 레코드부터 시작할지를 지정하는 값
                //페이지 번호는 0부터 시작하는 인덱스를 기준
                //첫 번째 페이지 (pageNumber = 0): 0번째부터 9번째까지의 레코드 (offset = 0)
                .offset(pageable.getOffset())
                //limit은 한 번에 가져올 데이터의 개수
                .limit(pageable.getPageSize())
                //리스트를 조회한다, 값이 없을 때에는 빈 리스트가 반환
                .fetch();

        return searchUserDTOResponseList;

    }

    @Override
    public Long searchUsersCount(SearchUserDTO dto) {
        QUser user = QUser.user;
        BooleanBuilder booleanBuilder = new BooleanBuilder();

        booleanBuilder = makeSearchUsersBooleanBuilder(booleanBuilder, user, dto);

        System.out.println("검색조건 id: " + dto.getId());
        System.out.println("검색조건 name: " + dto.getName());
        System.out.println("검색조건 level: " + dto.getLevel());
        System.out.println("검색조건 desc: " + dto.getDesc());
        System.out.println("검색조건 regDate: " + dto.getRegDate());

        //집계 함수의 결과는 null일 가능성이 있기 때문에, long(기본형 타입) 대신 Long(객체형 타입)을 반환
        Long totalCount = jpaQueryFactory
                .select(user.count())
                .from(user)
                .where(booleanBuilder)
                //fetchOne(): 단 건 조회
                //결과가 없을 때에는 null을 리턴한다.
                //결과가 둘 이상일 때에는 NonUniqueResultException이 발생
                .fetchOne();

        //Long,Integer 는 null이 가능. long,int는 null이 불가능
        if(totalCount == null){
            return 0L;
        }
        return totalCount;
    }


    private BooleanBuilder makeSearchUsersBooleanBuilder(BooleanBuilder booleanBuilder, QUser user, SearchUserDTO dto) {
        //isEmpty() null 뿐만 아니라 빈문자열일때 true 반환
        //isBlank()는 null 뿐만 아니라 빈 문자열("")이나 공백만 있는 문자열에도 true를 반환
        //다만, null 값에 isBlank()를 호출할 수 없음
        //StringUtils.hasText() => 공백과 null 다 체크하는 함수
        if (StringUtils.hasText(dto.getId())) {
            //like(str)은 쿼리가 나갈 때 str자체가 나간다
            //contains(str)은 쿼리가 나갈 때 %str%가 나간다
            booleanBuilder.and(user.id.contains(dto.getId()));
        }

        if (StringUtils.hasText(dto.getName())) {
            booleanBuilder.and(user.name.contains(dto.getName()));
        }

        if (StringUtils.hasText(dto.getLevel())) {
            //builder.eq(field, value) => 주어진 필드와 값이 같은지 확인하는 조건을 추가
            booleanBuilder.and(user.level.eq(dto.getLevel()));
        }

        if (StringUtils.hasText(dto.getDesc())) {
            booleanBuilder.and(user.desc.contains(dto.getDesc()));
        }

        if (dto.getStartRegDate() != null && dto.getEndRegDate() != null) {
            //localdatetime을 localdate로 바꿔서 해도 작동
            //Timestamp.valueOf() => 매개변수는 String s, LocalDateTime dateTime
            //atStartOfDay() =>  LocalDate 타입에서만 사용, 해당 날짜의 자정 (00:00:00) 을 나타내는 LocalDateTime 객체를 반환
            //Timestamp startDate = Timestamp.valueOf(dto.getRegDate().toLocalDate().atStartOfDay());
            LocalDateTime startDate = dto.getStartRegDate();

            //plusDays => LocalDate, LocalDateTime 타입 모두 사용가능
            //Timestamp endDate = Timestamp.valueOf(dto.getRegDate().plusDays(1).toLocalDate().atStartOfDay());
            LocalDateTime endDate = dto.getEndRegDate();


            //goe(): A >= ?
            //gt(): A > ?
            //loe(): A <= ?
            //lt(): A < ?
            log.info("Timestamp startDate : "+startDate);
            log.info("Timestamp endDate : "+endDate);
            booleanBuilder.and(user.regDate.goe(startDate)); // 이상
            booleanBuilder.and(user.regDate.loe(endDate)); //  미만
        }
        return booleanBuilder;
    }
}

