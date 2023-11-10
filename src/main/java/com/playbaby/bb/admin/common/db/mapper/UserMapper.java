package com.playbaby.bb.admin.common.db.mapper;

import com.playbaby.bb.admin.common.entity.po.UserPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Mapper
@Repository
public interface UserMapper {
    Optional<UserPO> findById(@Param("id") Integer id);
    Integer userExists(@Param("id") Integer id);
    boolean isAnchor(@Param("id") Integer id);
    Integer findGuildIdByUID(@Param("id") Integer id);
}
