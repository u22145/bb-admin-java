package com.playbaby.bb.admin.common.db.mapper;

import com.playbaby.bb.admin.common.entity.io.GuildQueryIO;
import com.playbaby.bb.admin.common.entity.po.GuildPO;
import com.playbaby.bb.admin.common.entity.po.GuildSettlementPO;
import com.playbaby.bb.admin.common.entity.vo.guild.bo.GuildVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface GuildMapper {
    List<GuildVO> findGuilds(@Param("io") GuildQueryIO guildQueryIO);
    boolean guildExists(@Param("id") int id);
    boolean isAnchorMemberOf(@Param("guild_id") Integer guildId, @Param("uid") Integer uid);
    void createGuild(@Param("guild") GuildPO guildPO);
    void updateGuild(@Param("guild") GuildPO guildPO);
    void invokeGuildSettlement(@Param("po") GuildSettlementPO guildSettlementPO);
    void invokeGuildAnchorSettlement(@Param("po") GuildSettlementPO guildSettlementPO);
}
