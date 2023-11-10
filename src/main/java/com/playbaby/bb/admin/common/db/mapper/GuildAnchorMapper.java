package com.playbaby.bb.admin.common.db.mapper;

import com.playbaby.bb.admin.common.entity.io.GuildRptIO;
import com.playbaby.bb.admin.common.entity.po.GuildAnchorSettingPO;
import com.playbaby.bb.admin.common.entity.po.GuildAnchorTierPO;
import com.playbaby.bb.admin.common.entity.vo.guild.soc.GMAnchorVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface GuildAnchorMapper {
    boolean guildAnchorExists(@Param("gid") Integer gid, @Param("uid") Integer uid);
    void upsertGuildAnchorSetting(@Param("po") GuildAnchorSettingPO guildAnchorSettingPO);
    void upsertGuildAnchorTiers(@Param("guildAnchorTiers") List<GuildAnchorTierPO> guildAnchorTiers);
    void updateGuildAnchorTiers(@Param("uid") Integer uid, @Param("guildAnchorTiers") List<GuildAnchorTierPO> guildAnchorTiers);
    List<GMAnchorVO> getGuildAnchor(@Param("io")GuildRptIO io);
}
