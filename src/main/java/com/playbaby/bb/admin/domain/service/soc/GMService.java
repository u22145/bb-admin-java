package com.playbaby.bb.admin.domain.service.soc;

import com.playbaby.bb.admin.common.db.mapper.GuildAnchorMapper;
import com.playbaby.bb.admin.common.db.mapper.UserMapper;
import com.playbaby.bb.admin.common.entity.io.GuildAnchorSettingIO;
import com.playbaby.bb.admin.common.entity.io.GuildRptIO;
import com.playbaby.bb.admin.common.entity.po.GuildAnchorSettingPO;
import com.playbaby.bb.admin.common.entity.vo.guild.soc.GMAnchorVO;
import com.playbaby.bb.admin.common.exception.APIException;
import com.playbaby.bb.admin.common.util.Paging;
import com.playbaby.bb.admin.common.util.validator.AnchorValidator;
import com.playbaby.bb.admin.common.util.validator.GuildRptValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.playbaby.bb.admin.common.constant.CommonConst.PAGE_SIZE;

@Slf4j
@Service
public class GMService {
    private UserMapper userMapper;
    private GuildAnchorMapper guildAnchorMapper;

    public GMService(UserMapper userMapper,
                     GuildAnchorMapper guildAnchorMapper) {
        this.userMapper = userMapper;
        this.guildAnchorMapper = guildAnchorMapper;
    }

    public Paging<GMAnchorVO> getGuildAnchor(GuildRptIO io) {
        GuildRptValidator.validateReport(io);

        return Paging.getInstance(io.getPageInt(), PAGE_SIZE, GuildAnchorMapper::getGuildAnchor, guildAnchorMapper, io);
    }

    @Transactional(rollbackFor = Exception.class)
    public void createGuildAnchor(GuildAnchorSettingIO io) throws APIException {
        GuildAnchorSettingPO po = AnchorValidator.validateSetting(io);
        final Integer uid = io.getUid();
        // FIXME: use real gid
        final Integer gid = 1;

        if (uid == null)
            throw APIException.getInstance(101, "uid must be present");
        if (!userMapper.isAnchor(uid))
            throw APIException.getInstance(102, "user " + uid + " either does not exist or is not an anchor");
        if (guildAnchorMapper.guildAnchorExists(gid, uid))
            throw APIException.getInstance(200011, "anchor " + uid + " already exists in guild " + gid);

        po.setGid(gid);
        po.setUid(uid);
        po.getAnchorTiers().forEach(tier -> tier.setUid(uid));
        guildAnchorMapper.upsertGuildAnchorSetting(po);
        guildAnchorMapper.upsertGuildAnchorTiers(po.getAnchorTiers());
    }

    @Transactional(rollbackFor = Exception.class)
    public void updateGuildAnchor(GuildAnchorSettingIO io) throws APIException {
        GuildAnchorSettingPO po = AnchorValidator.validateSetting(io);
        final Integer uid = io.getUid();
        // FIXME: use real gid
        final Integer gid = 1;

        if (!guildAnchorMapper.guildAnchorExists(gid, uid))
            throw APIException.getInstance(200012, "anchor " + uid + " does not exist in guild " + gid);

        po.setGid(gid);
        po.setUid(uid);
        po.getAnchorTiers().forEach(tier -> tier.setUid(uid));
        guildAnchorMapper.upsertGuildAnchorSetting(po);
        guildAnchorMapper.updateGuildAnchorTiers(uid, po.getAnchorTiers());
    }
}
