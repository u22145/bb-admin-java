package com.playbaby.bb.admin.domain.controller.soc;

import com.playbaby.bb.admin.common.entity.io.GuildRptIO;
import com.playbaby.bb.admin.common.entity.io.PerfAssessmentIApprovalIO;
import com.playbaby.bb.admin.common.entity.vo.Result;
import com.playbaby.bb.admin.common.entity.vo.guild.bo.*;
import com.playbaby.bb.admin.common.exception.APIException;
import com.playbaby.bb.admin.common.util.Paging;
import com.playbaby.bb.admin.domain.service.auth.GMSessionService;
import com.playbaby.bb.admin.domain.service.bo.GuildRptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/gm/rpt")
public class GMRptController {
    private GMSessionService gmSessionService;
    private GuildRptService guildRptService;

    @Autowired
    public GMRptController(GMSessionService gmSessionService,
                           GuildRptService guildRptService) {
        this.gmSessionService = gmSessionService;
        this.guildRptService = guildRptService;
    }

    @GetMapping("/live")
    public Result<Paging<LiveRptVO>> getLiveReport(@RequestParam(value = "uid", required = false) String uid,
                                                   @RequestParam(value = "from", required = false) String from,
                                                   @RequestParam(value = "to", required = false) String to,
                                                   @RequestParam(value = "guildless", required = false) String guildless,
                                                   @RequestParam(value = "page", required = false) String page) {
        String guild = getGuildId();
        GuildRptIO io = GuildRptIO.builder().uid(uid).from(from).to(to).guild(guild).guildless(guildless).page(page).build();

        return Result.of(guildRptService.getLiveReport(io));
    }

    @GetMapping("/live/detail")
    public Result<Paging<LiveAnchorVO>> getLiveAnchorReport(@RequestParam(value = "uid", required = false) String uid,
                                                            @RequestParam(value = "from", required = false) String from,
                                                            @RequestParam(value = "to", required = false) String to,
                                                            @RequestParam(value = "page", required = false) String page) {
        String guild = getGuildId();
        GuildRptIO io = GuildRptIO.builder().uid(uid).from(from).to(to).guild(guild).page(page).build();

        return Result.of(guildRptService.getLiveAnchorReport(io));

    }

    @GetMapping("/ps")
    public Result<Paging<PSRptVO>> getPSReport(@RequestParam(value = "uid", required = false) String uid,
                                               @RequestParam(value = "from", required = false) String from,
                                               @RequestParam(value = "to", required = false) String to,
                                               @RequestParam(value = "page", required = false) String page) {
        String guild = getGuildId();
        GuildRptIO io = GuildRptIO.builder().uid(uid).from(from).to(to).guild(guild).page(page).build();

        return Result.of(guildRptService.getPSReport(io));
    }

    @GetMapping("/ps/detail")
    public Result<Paging<PSAnchorVO>> getPSAnchorReport(@RequestParam(value = "uid", required = false) String uid,
                                                        @RequestParam(value = "from", required = false) String from,
                                                        @RequestParam(value = "to", required = false) String to,
                                                        @RequestParam(value = "page", required = false) String page) {
        String guild = getGuildId();
        GuildRptIO io = GuildRptIO.builder().uid(uid).from(from).to(to).guild(guild).page(page).build();

        return Result.of(guildRptService.getPSAnchorReport(io));
    }

    @GetMapping("/pm")
    public Result<Paging<PMRptVO>> getPMReport(@RequestParam(value = "uid", required = false) String uid,
                                               @RequestParam(value = "from", required = false) String from,
                                               @RequestParam(value = "to", required = false) String to,
                                               @RequestParam(value = "guildless", required = false) String guildless,
                                               @RequestParam(value = "page", required = false) String page) {
        String guild = getGuildId();
        GuildRptIO io = GuildRptIO.builder().uid(uid).from(from).to(to).guild(guild).guildless(guildless).page(page).build();

        return Result.of(guildRptService.getPMReport(io));
    }

    @GetMapping("/pm/detail")
    public Result<Paging<PMAnchorVO>> getPMAnchor(@RequestParam(value = "uid", required = false) String uid,
                                                  @RequestParam(value = "from", required = false) String from,
                                                  @RequestParam(value = "to", required = false) String to,
                                                  @RequestParam(value = "page", required = false) String page) {
        String guild = getGuildId();
        GuildRptIO io = GuildRptIO.builder().uid(uid).from(from).to(to).guild(guild).page(page).build();

        return Result.of(guildRptService.getPMAnchor(io));
    }

    @GetMapping("/perf")
    public Result<Paging<GMGuildPerfVO>> getGuildPerf(@RequestParam(value = "from", required = false) String from,
                                                      @RequestParam(value = "to", required = false) String to,
                                                      @RequestParam(value = "page", required = false) String page) {
        String guild = getGuildId();
        GuildRptIO io = GuildRptIO.builder().from(from).to(to).guild(guild).page(page).build();

        return Result.of(guildRptService.getGMGuildPerf(io));
    }

    @GetMapping("/perf/commission")
    public Result<Paging<GuildCommissionVO>> getAnchorPerf(@RequestParam(value = "from", required = false) String from,
                                                           @RequestParam(value = "to", required = false) String to,
                                                           @RequestParam(value = "uid", required = false) String uid,
                                                           @RequestParam(value = "sort", required = false) String sort,
                                                           @RequestParam(value = "page", required = false) String page) {
        String guild = getGuildId();
        GuildRptIO io = GuildRptIO.builder().from(from).to(to).uid(uid).guild(guild).sort(sort).page(page).build();

        return Result.of(guildRptService.getGMAnchorCommission(io));
    }

    @GetMapping("/perf/assessment")
    public Result<Paging<AnchorPerfAssessmentVO>> getAnchorPerfAssessment(@RequestParam(value = "from", required = false) String from,
                                                                          @RequestParam(value = "uid", required = false) String uid,
                                                                          @RequestParam(value = "sort", required = false) String sort,
                                                                          @RequestParam(value = "page", required = false) String page) {
        String guild = getGuildId();
        GuildRptIO io = GuildRptIO.builder().from(from).uid(uid).guild(guild).sort(sort).page(page).build();

        return Result.of(guildRptService.getAnchorPerfAssessment(io));
    }

    @PutMapping("/perf/assessment")
    public Result<?> approveAnchorPerfAssessment(@RequestBody PerfAssessmentIApprovalIO io) {
        try {
            String guild = getGuildId();
            guildRptService.approveAnchorPerfAssessment(guild, io);

            return Result.OK;
        } catch (APIException e) {
            return Result.getInstance(e);
        }
    }

    private String getGuildId() {
        return gmSessionService.getGuildId();
    }
}
