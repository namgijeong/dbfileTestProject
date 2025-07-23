package com.example.test2.data.repository;


import com.example.test2.data.dto.QSearchUserDTOResponse;
import com.example.test2.data.entity.QUser;
import com.querydsl.core.types.Expression;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

import com.example.test2.data.dto.SearchUserDTO;
import com.example.test2.data.dto.SearchUserDTOResponse;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.querydsl.core.BooleanBuilder;


import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserRepositoryCustomImpl implements UserRepositoryCustom{
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<SearchUserDTOResponse> searchUsers(SearchUserDTO dto, Pageable pageable) {

        QUser user = QUser.user;
        BooleanBuilder booleanBuilder = new BooleanBuilder();

        if (dto.getId() != null && !dto.getId().isBlank()) {
            booleanBuilder.and(user.id.contains(dto.getId()));
        }
        if (dto.getName() != null && !dto.getName().isBlank()) {
            booleanBuilder.and(user.name.contains(dto.getName()));
        }
        if (dto.getLevel() != null && !dto.getLevel().isBlank()) {
            booleanBuilder.and(user.level.eq(dto.getLevel()));
        }
        if (dto.getDesc() != null && !dto.getDesc().isBlank()) {
            booleanBuilder.and(user.desc.contains(dto.getDesc()));
        }
        if (dto.getRegDate() != null) {
            //localdatetime을 localdate로 바꿔서 해도 작동
            Timestamp startDate = Timestamp.valueOf(dto.getRegDate().toLocalDate().atStartOfDay());
            Timestamp endDate = Timestamp.valueOf(dto.getRegDate().plusDays(1).toLocalDate().atStartOfDay());
            booleanBuilder.and(user.regDate.goe( startDate)); // 이상
            booleanBuilder.and(user.regDate.lt(endDate)); //  미만
        }

        System.out.println("검색조건 id: " + dto.getId());
        System.out.println("검색조건 name: " + dto.getName());
        System.out.println("검색조건 level: " + dto.getLevel());
        System.out.println("검색조건 desc: " + dto.getDesc());
        System.out.println("검색조건 regDate: " + dto.getRegDate());

        List<SearchUserDTOResponse> searchUserDTOResponseList = jpaQueryFactory
                .select(new QSearchUserDTOResponse(
                        user.id,
                        user.pwd,
                        user.name,
                        user.level,
                        user.desc,
                        user.regDate
                ))
                .from(user)
                .where(booleanBuilder)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        return searchUserDTOResponseList;

    }
}
