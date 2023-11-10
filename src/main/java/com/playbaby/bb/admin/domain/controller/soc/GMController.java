package com.playbaby.bb.admin.domain.controller.soc;

import com.playbaby.bb.admin.common.entity.io.GuildAnchorSettingIO;
import com.playbaby.bb.admin.common.entity.io.GuildRptIO;
import com.playbaby.bb.admin.common.entity.vo.Result;
import com.playbaby.bb.admin.common.entity.vo.guild.soc.GMAnchorVO;
import com.playbaby.bb.admin.common.exception.APIException;
import com.playbaby.bb.admin.common.util.Paging;
import com.playbaby.bb.admin.domain.service.auth.GMSessionService;
import com.playbaby.bb.admin.domain.service.soc.GMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("gm")
public class GMController {
    private GMSessionService gmSessionService;
    private GMService gmService;

    @Autowired
    public GMController(GMSessionService gmSessionService,
                        GMService gmService) {
        this.gmSessionService = gmSessionService;
        this.gmService = gmService;
    }

    @GetMapping("anchor")
    public Result<Paging<GMAnchorVO>> getGuildAnchors(@RequestParam(value = "uid", required = false) String uid,
                                                      @RequestParam(value = "from", required = false) String from,
                                                      @RequestParam(value = "to", required = false) String to,
                                                      @RequestParam(value = "page", required = false) String page) {
        String guild = gmSessionService.getGuildId();
        GuildRptIO io = GuildRptIO.builder().uid(uid).from(from).to(to).guild(guild).page(page).build();

        return Result.of(gmService.getGuildAnchor(io));
    }

    @PostMapping("anchor")
    public Result<?> createGuildAnchor(@RequestBody GuildAnchorSettingIO io) {
        try {
            gmService.createGuildAnchor(io);

            return Result.OK;
        } catch (APIException e) {
            return Result.getInstance(e);
        }
    }

    @PutMapping("anchor")
    public Result<?> updateGuildAnchor(@RequestBody GuildAnchorSettingIO io) {
        try {
            gmService.updateGuildAnchor(io);

            return Result.OK;
        } catch (APIException e) {
            return Result.getInstance(e);
        }
    }
}
