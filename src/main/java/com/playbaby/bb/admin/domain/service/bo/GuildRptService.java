package com.playbaby.bb.admin.domain.service.bo;

import com.playbaby.bb.admin.common.db.mapper.GuildMapper;
import com.playbaby.bb.admin.common.db.mapper.GuildRptMapper;
import com.playbaby.bb.admin.common.entity.io.GuildRptIO;
import com.playbaby.bb.admin.common.entity.io.PerfAssessmentIApprovalIO;
import com.playbaby.bb.admin.common.entity.vo.guild.bo.*;
import com.playbaby.bb.admin.common.exception.APIException;
import com.playbaby.bb.admin.common.util.Paging;
import com.playbaby.bb.admin.common.util.validator.GuildRptValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.playbaby.bb.admin.common.constant.CommonConst.PAGE_SIZE;

@Service
public class GuildRptService {
    private GuildMapper guildMapper;
    private GuildRptMapper guildRptMapper;

    @Autowired
    public GuildRptService(GuildMapper guildMapper,
                           GuildRptMapper guildRptMapper) {
        this.guildMapper = guildMapper;
        this.guildRptMapper = guildRptMapper;
    }

    public Paging<AggregationVO> getGuildAggrReport(GuildRptIO io) {
        GuildRptValidator.validateReport(io);

        return Paging.getInstance(io.getPageInt(), PAGE_SIZE, GuildRptMapper::getGuildAggrReport, guildRptMapper, io);
    }

    public Paging<LiveRptVO> getLiveReport(GuildRptIO io) {
        GuildRptValidator.validateReport(io);

        return Paging.getInstance(io.getPageInt(), PAGE_SIZE, GuildRptMapper::getLiveReport, guildRptMapper, io);
    }

    public Paging<LiveAnchorVO> getLiveAnchorReport(GuildRptIO io) {
        GuildRptValidator.validateReport(io);

        return Paging.getInstance(io.getPageInt(), PAGE_SIZE, GuildRptMapper::getLiveAnchor, guildRptMapper, io);
    }

    public Paging<PSRptVO> getPSReport(GuildRptIO io) {
        GuildRptValidator.validateReport(io);

        return Paging.getInstance(io.getPageInt(), PAGE_SIZE, GuildRptMapper::getPSReport, guildRptMapper, io);
    }

    public Paging<PSAnchorVO> getPSAnchorReport(GuildRptIO io) {
        GuildRptValidator.validateReport(io);

        return Paging.getInstance(io.getPageInt(), PAGE_SIZE, GuildRptMapper::getPSAnchor, guildRptMapper, io);
    }

    public Paging<PMRptVO> getPMReport(GuildRptIO io) {
        GuildRptValidator.validateReport(io);

        return Paging.getInstance(io.getPageInt(), PAGE_SIZE, GuildRptMapper::getPMReport, guildRptMapper, io);
    }

    public Paging<PMAnchorVO> getPMAnchor(GuildRptIO io) {
        GuildRptValidator.validateReport(io);

        return Paging.getInstance(io.getPageInt(), PAGE_SIZE, GuildRptMapper::getPMAnchor, guildRptMapper, io);
    }

    public Paging<GuildPerfVO> getGuildPerf(GuildRptIO io) {
        GuildRptValidator.validateReport(io);

        return Paging.getInstance(io.getPageInt(), PAGE_SIZE, GuildRptMapper::getGuildPerf, guildRptMapper, io);
    }

    public Paging<GMGuildPerfVO> getGMGuildPerf(GuildRptIO io) {
        GuildRptValidator.validateReport(io);

        return Paging.getInstance(io.getPageInt(), PAGE_SIZE, GuildRptMapper::getGMGuildPerf, guildRptMapper, io);
    }

    public Paging<AnchorPerfVO> getAnchorPerf(GuildRptIO io) {
        GuildRptValidator.validateReport(io);

        return Paging.getInstance(io.getPageInt(), PAGE_SIZE, GuildRptMapper::getAnchorPerf, guildRptMapper, io);
    }

    public Paging<GuildCommissionVO> getGMAnchorCommission(GuildRptIO io) {
        GuildRptValidator.validateReport(io);

        return Paging.getInstance(io.getPageInt(), PAGE_SIZE, GuildRptMapper::getGMAnchorCommission, guildRptMapper, io);
    }


    public Paging<AnchorPerfAssessmentVO> getAnchorPerfAssessment(GuildRptIO io) {
        GuildRptValidator.validateReport(io);

        return Paging.getInstance(io.getPageInt(), PAGE_SIZE, GuildRptMapper::getAnchorPerfAssessment, guildRptMapper, io);
    }

    public void approveAnchorPerfAssessment(String guildId, PerfAssessmentIApprovalIO io) throws APIException {
        GuildRptValidator.validatePerfAssessment(io);
        Integer gid = Integer.parseInt(guildId);
        if (!guildMapper.isAnchorMemberOf(gid, io.getUid()))
            throw APIException.getInstance(200104, "anchor " + io.getUid() + " is not a member of guild " + guildId);

        io.setGuildId(gid);
        guildRptMapper.approveAnchorPerfAssessment(io);
    }

    public Paging<GuildCommissionVO> getGuildCommission(GuildRptIO io) {
        GuildRptValidator.validateReport(io);

        return Paging.getInstance(io.getPageInt(), PAGE_SIZE, GuildRptMapper::getGuildCommission, guildRptMapper, io);
    }
}
