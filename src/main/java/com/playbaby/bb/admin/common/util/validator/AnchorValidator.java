package com.playbaby.bb.admin.common.util.validator;

import com.playbaby.bb.admin.common.entity.io.GuildAnchorTierIO;
import com.playbaby.bb.admin.common.entity.io.GuildAnchorSettingIO;
import com.playbaby.bb.admin.common.entity.po.GuildAnchorTierPO;
import com.playbaby.bb.admin.common.entity.po.GuildAnchorSettingPO;
import com.playbaby.bb.admin.common.exception.APIException;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static com.playbaby.bb.admin.common.util.Rationalizer.rationalizeCommission;

public class AnchorValidator {

    public static GuildAnchorSettingPO validateSetting(GuildAnchorSettingIO io) throws APIException {
        Integer performance = io.getPerformance();
        if (performance == null || performance <= 0)
            throw APIException.getInstance(200001, "anchor performance must be defined and greater than 0");

        List<GuildAnchorTierIO> anchorTiers = io.getAnchorTiers();
        if (CollectionUtils.isEmpty(anchorTiers))
            throw APIException.getInstance(200002, "anchor tier setting cannot be empty");

        List<GuildAnchorTierPO> tierPOList = new ArrayList<>(anchorTiers.size());
        for (GuildAnchorTierIO tierIO : anchorTiers) {
            Integer goalCount = tierIO.getGoalCount();
            if (goalCount == null || goalCount <= 0)
                throw APIException.getInstance(200010, "goal count must be greater than 0");

            GuildAnchorTierPO po = GuildAnchorTierPO.builder().id(tierIO.getId()).goalCount(goalCount).build();
            checkAndFillCommissions(tierIO, po);
            tierPOList.add(po);
        }

        return GuildAnchorSettingPO.builder().performance(performance).anchorTiers(tierPOList).build();
    }

    private static void checkAndFillCommissions(GuildAnchorTierIO io, GuildAnchorTierPO po) throws APIException {
        BigDecimal giftCommission = rationalizeCommission(io, "gift", 200003);
        BigDecimal lotteryCommission = rationalizeCommission(io, "lottery", 200004);
        BigDecimal privateStreamCommission = rationalizeCommission(io, "privateStream", 200005);
        BigDecimal streamTicketCommission = rationalizeCommission(io, "streamTicket", 200006);
        BigDecimal ugcCommission = rationalizeCommission(io, "ugc", 200007);
        BigDecimal datingCommission = rationalizeCommission(io, "dating", 200008);
        BigDecimal msgCommission = rationalizeCommission(io, "message", 200009);

        po.setGiftCommission(giftCommission);
        po.setLotteryCommission(lotteryCommission);
        po.setPrivateStreamCommission(privateStreamCommission);
        po.setStreamTicketCommission(streamTicketCommission);
        po.setUgcCommission(ugcCommission);
        po.setDatingCommission(datingCommission);
        po.setMessageCommission(msgCommission);
    }
}
