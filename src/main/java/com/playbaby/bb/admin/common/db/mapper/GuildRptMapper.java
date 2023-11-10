package com.playbaby.bb.admin.common.db.mapper;

import com.playbaby.bb.admin.common.entity.io.GuildRptIO;
import com.playbaby.bb.admin.common.entity.io.PerfAssessmentIApprovalIO;
import com.playbaby.bb.admin.common.entity.vo.guild.bo.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface GuildRptMapper {
    List<AggregationVO> getGuildAggrReport(@Param("io") GuildRptIO io);
    List<LiveRptVO> getLiveReport(@Param("io") GuildRptIO io);
    List<LiveAnchorVO> getLiveAnchor(@Param("io") GuildRptIO io);
    List<PSRptVO> getPSReport(@Param("io") GuildRptIO io);
    List<PSAnchorVO> getPSAnchor(@Param("io") GuildRptIO io);
    List<PMRptVO> getPMReport(@Param("io") GuildRptIO io);
    List<PMAnchorVO> getPMAnchor(@Param("io") GuildRptIO io);
    List<GuildPerfVO> getGuildPerf(@Param("io") GuildRptIO io);
    List<GMGuildPerfVO> getGMGuildPerf(@Param("io") GuildRptIO io);
    List<AnchorPerfVO> getAnchorPerf(@Param("io") GuildRptIO io);
    List<GuildCommissionVO> getGuildCommission(@Param("io") GuildRptIO io);
    List<GuildCommissionVO> getGMAnchorCommission(@Param("io") GuildRptIO io);
    List<AnchorPerfAssessmentVO> getAnchorPerfAssessment(@Param("io") GuildRptIO io);
    void approveAnchorPerfAssessment(@Param("io")PerfAssessmentIApprovalIO io);
}
