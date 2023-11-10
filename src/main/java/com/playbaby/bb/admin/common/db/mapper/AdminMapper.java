package com.playbaby.bb.admin.common.db.mapper;

import com.playbaby.bb.admin.common.entity.po.AdminUserPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Mapper
@Repository
public interface AdminMapper {
    Optional<AdminUserPO> findByUsername(@Param("username") String username);
}
