package com.playbaby.bb.admin.domain.controller.bo;

import com.playbaby.bb.admin.common.entity.io.GuildRptIO;
import com.playbaby.bb.admin.common.entity.vo.Result;
import com.playbaby.bb.admin.common.entity.vo.guild.bo.*;
import com.playbaby.bb.admin.common.util.Paging;
import com.playbaby.bb.admin.domain.service.bo.GuildRptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/guild/rpt")
public class GuildRptController {
    private GuildRptService guildRptService;

    @Autowired
    public GuildRptController(GuildRptService guildRptService) {
        this.guildRptService = guildRptService;
    }

    @GetMapping("/guild")
    public Result<Paging<AggregationVO>> getGuildAggrReport(@RequestParam(value = "from", required = false) String from,
                                                            @RequestParam(value = "to", required = false) String to,
                                                            @RequestParam(value = "guild", required = false) String guild,
                                                            @RequestParam(value = "gm", required = false) String gm,
                                                            @RequestParam(value = "page", required = false) String page) {
        GuildRptIO io = GuildRptIO.builder().from(from).to(to).guild(guild).gm(gm).page(page).build();

        return Result.of(guildRptService.getGuildAggrReport(io));
    }

    @GetMapping("/live")
    public Result<Paging<LiveRptVO>> getLiveReport(@RequestParam(value = "from", required = false) String from,
                                                   @RequestParam(value = "to", required = false) String to,
                                                   @RequestParam(value = "guild", required = false) String guild,
                                                   @RequestParam(value = "guildless", required = false) String guildless,
                                                   @RequestParam(value = "page", required = false) String page) {
        GuildRptIO io = GuildRptIO.builder().from(from).to(to).guild(guild).guildless(guildless).page(page).build();

        return Result.of(guildRptService.getLiveReport(io));
    }

    @GetMapping("/live/detail")
    public Result<Paging<LiveAnchorVO>> getLiveAnchorReport(@RequestParam(value = "uid", required = false) String uid,
                                                            @RequestParam(value = "from", required = false) String from,
                                                            @RequestParam(value = "to", required = false) String to,
                                                            @RequestParam(value = "page", required = false) String page) {
        GuildRptIO io = GuildRptIO.builder().from(from).to(to).uid(uid).page(page).build();

        return Result.of(guildRptService.getLiveAnchorReport(io));

    }

    @GetMapping("/ps")
    public Result<Paging<PSRptVO>> getPSReport(@RequestParam(value = "from", required = false) String from,
                                               @RequestParam(value = "to", required = false) String to,
                                               @RequestParam(value = "guild", required = false) String guild,
                                               @RequestParam(value = "guildless", required = false) String guildless,
                                               @RequestParam(value = "page", required = false) String page) {
        GuildRptIO io = GuildRptIO.builder().from(from).to(to).guild(guild).guildless(guildless).page(page).build();

        return Result.of(guildRptService.getPSReport(io));
    }

    @GetMapping("/ps/detail")
    public Result<Paging<PSAnchorVO>> getPSAnchorReport(@RequestParam(value = "uid", required = false) String uid,
                                                        @RequestParam(value = "from", required = false) String from,
                                                        @RequestParam(value = "to", required = false) String to,
                                                        @RequestParam(value = "page", required = false) String page) {
        GuildRptIO io = GuildRptIO.builder().uid(uid).from(from).to(to).page(page).build();

        return Result.of(guildRptService.getPSAnchorReport(io));
    }

    @GetMapping("/pm")
    public Result<Paging<PMRptVO>> getPMReport(@RequestParam(value = "from", required = false) String from,
                                               @RequestParam(value = "to", required = false) String to,
                                               @RequestParam(value = "guild", required = false) String guild,
                                               @RequestParam(value = "guildless", required = false) String guildless,
                                               @RequestParam(value = "page", required = false) String page) {
        GuildRptIO io = GuildRptIO.builder().from(from).to(to).guild(guild).guildless(guildless).page(page).build();

        return Result.of(guildRptService.getPMReport(io));
    }

    @GetMapping("/pm/detail")
    public Result<Paging<PMAnchorVO>> getPMAnchor(@RequestParam(value = "uid", required = false) String uid,
                                                  @RequestParam(value = "from", required = false) String from,
                                                  @RequestParam(value = "to", required = false) String to,
                                                  @RequestParam(value = "page", required = false) String page) {
        GuildRptIO io = GuildRptIO.builder().uid(uid).from(from).to(to).page(page).build();

        return Result.of(guildRptService.getPMAnchor(io));
    }

    @GetMapping("/perf/guild")
    public Result<Paging<GuildPerfVO>> getGuildPerf(@RequestParam(value = "from", required = false) String from,
                                                    @RequestParam(value = "to", required = false) String to,
                                                    @RequestParam(value = "sort", required = false) String sort,
                                                    @RequestParam(value = "page", required = false) String page) {
        GuildRptIO io = GuildRptIO.builder().from(from).to(to).sort(sort).page(page).build();

        return Result.of(guildRptService.getGuildPerf(io));
    }

    @GetMapping("perf/anchor")
    public Result<Paging<AnchorPerfVO>> getAnchorPerf(@RequestParam(value = "from", required = false) String from,
                                                      @RequestParam(value = "to", required = false) String to,
                                                      @RequestParam(value = "sort", required = false) String sort,
                                                      @RequestParam(value = "page", required = false) String page) {
        GuildRptIO io = GuildRptIO.builder().from(from).to(to).sort(sort).page(page).build();

        return Result.of(guildRptService.getAnchorPerf(io));
    }

    @GetMapping("perf/commission")
    public Result<Paging<GuildCommissionVO>> getGuildCommission(@RequestParam(value = "from", required = false) String from,
                                   @RequestParam(value = "to", required = false) String to,
                                   @RequestParam(value = "guild", required = false) String guild,
                                   @RequestParam(value = "uid", required = false) String uid,
                                   @RequestParam(value = "page", required = false) String page) {
        GuildRptIO io = GuildRptIO.builder().from(from).to(to).guild(guild).uid(uid).page(page).build();

        return Result.of(guildRptService.getGuildCommission(io));
    }
}
