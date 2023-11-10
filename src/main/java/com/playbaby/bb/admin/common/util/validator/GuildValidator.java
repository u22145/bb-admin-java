package com.playbaby.bb.admin.common.util.validator;

import com.playbaby.bb.admin.common.constant.ValidationConst;
import com.playbaby.bb.admin.common.entity.io.GuildIO;
import com.playbaby.bb.admin.common.entity.io.GuildQueryIO;
import com.playbaby.bb.admin.common.entity.po.GuildPO;
import com.playbaby.bb.admin.common.exception.APIException;
import com.playbaby.bb.admin.common.util.Rationalizer;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;

import static com.playbaby.bb.admin.common.util.Rationalizer.rationalizeCommission;

@Slf4j
public class GuildValidator {

    public static GuildPO validate(GuildIO guildIO) throws APIException {
        if (guildIO == null)
            throw APIException.getInstance(100000, "guild information cannot be empty");

        if (!GuildValidator.checkName(guildIO.getName()))
            throw APIException.getInstance(100002, "guild name cannot be empty or exceed " + ValidationConst.GUILD_NAME_LENGTH_LIMIT + " chars");

        GuildPO guildPO = GuildPO.builder().name(guildIO.getName()).status(1).build();
        checkAndFillCommissions(guildIO, guildPO);
        Integer status = guildIO.getStatus();
        if (status == null || (status != 1 && status != 2))
            throw APIException.getInstance(100010, "guild status must be either 0 or 1");

        return guildPO;
    }

    private static boolean checkName(String name) {
        if (StringUtils.isBlank(name))
            return false;

        return StringUtils.length(name) <= ValidationConst.GUILD_NAME_LENGTH_LIMIT;
    }

    public static BigDecimal checkCommission(String commission) {
        try {
            if (commission == null)
                return null;
            BigDecimal ret = new BigDecimal(commission);
            if (ret.compareTo(BigDecimal.ZERO) <= 0 || ret.compareTo(BigDecimal.ONE) >= 0)
                return null;

            return ret;
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private static void checkAndFillCommissions(GuildIO io, GuildPO guildPO) throws APIException {
        BigDecimal giftCommission = rationalizeCommission(io, "gift", 100003);
        BigDecimal lotteryCommission = rationalizeCommission(io, "lottery", 100004);
        BigDecimal privateStreamCommission = rationalizeCommission(io, "privateStream", 100005);
        BigDecimal streamTicketCommission = rationalizeCommission(io, "streamTicket", 100006);
        BigDecimal ugcCommission = rationalizeCommission(io, "ugc", 100007);
        BigDecimal datingCommission = rationalizeCommission(io, "dating", 100008);
        BigDecimal msgCommission = rationalizeCommission(io, "message", 100009);

        guildPO.setGiftCommission(giftCommission);
        guildPO.setLotteryCommission(lotteryCommission);
        guildPO.setPrivateStreamCommission(privateStreamCommission);
        guildPO.setStreamTicketCommission(streamTicketCommission);
        guildPO.setUgcCommission(ugcCommission);
        guildPO.setDatingCommission(datingCommission);
        guildPO.setMessageCommission(msgCommission);
    }

    public static void rationalize(GuildQueryIO io) {
        Rationalizer.rationalizeInt(io, new String[]{ "guild", "gm", "status", "page" });
        if (io.getPageInt() == null)
            io.setPageInt(1);
    }
}
