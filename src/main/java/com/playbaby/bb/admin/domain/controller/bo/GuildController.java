package com.playbaby.bb.admin.domain.controller.bo;

import com.playbaby.bb.admin.common.entity.io.GuildIO;
import com.playbaby.bb.admin.common.entity.io.GuildQueryIO;
import com.playbaby.bb.admin.common.entity.vo.guild.bo.GuildVO;
import com.playbaby.bb.admin.common.entity.vo.Result;
import com.playbaby.bb.admin.common.exception.APIException;
import com.playbaby.bb.admin.common.util.Paging;
import com.playbaby.bb.admin.domain.service.bo.GuildService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("guild")
public class GuildController {
    private GuildService guildService;

    @Autowired
    public GuildController(GuildService guildService) {
        this.guildService = guildService;
    }

    @GetMapping
    public Result<Paging<GuildVO>> findGuilds(@RequestParam(required = false) String guild,
                                              @RequestParam(required = false) String gm,
                                              @RequestParam(required = false) String status,
                                              @RequestParam(required = false) String page) {
        GuildQueryIO queryIO = GuildQueryIO.builder().guild(guild).gm(gm).status(status).page(page).build();

        return Result.of(guildService.findGuilds(queryIO));
    }

    @GetMapping("{id}")
    public String findGuildById(@PathVariable("id") String id) {
        return String.valueOf(id);
    }

    @PostMapping
    public Result<?> createGuild(@RequestBody GuildIO guildIO) {
        try {
            guildService.createGuild(guildIO);

            return Result.OK;
        } catch (APIException e) {
            return Result.getInstance(e);
        }
    }

    @PutMapping
    public Result<?> updateGuild(@RequestBody GuildIO guildIO) {
        try {
            guildService.updateGuild(guildIO);

            return Result.OK;
        } catch (APIException e) {
            return Result.getInstance(e);
        }
    }
}
