package com.playbaby.bb.admin.domain.service;

import com.playbaby.bb.admin.common.db.mapper.GuildMapper;
import com.playbaby.bb.admin.common.entity.po.GuildSettlementPO;
import com.playbaby.bb.admin.common.util.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Slf4j
@Service
public class SettlementService {
    private GuildMapper guildMapper;

    @Autowired
    public SettlementService(GuildMapper guildMapper) {
        this.guildMapper = guildMapper;
    }

    public void invokeGuildSettlementFor(LocalDate settledFor) {
        GuildSettlementPO po = getGuildSettlementPO(settledFor);

        log.info("Invoking guild settlement for {}", settledFor.toString());
        guildMapper.invokeGuildSettlement(po);
        log.info("Guild settlement for {} has been processed", settledFor.toString());
    }

    public void invokeGuildAnchorSettlementFor(LocalDate settledFor) {
        GuildSettlementPO po = getGuildSettlementPO(settledFor);
        log.info("Invoking guild anchor settlement for {}", settledFor.toString());
        guildMapper.invokeGuildAnchorSettlement(po);
        log.info("Guild anchor settlement for {} has been processed", settledFor.toString());
    }

    private static GuildSettlementPO getGuildSettlementPO(LocalDate localDate) {
        return GuildSettlementPO.builder()
            .settledFor(localDate.toString())
            .from(DateUtils.startOfDay(localDate))
            .to(DateUtils.endOfDay(localDate))
            .build();
    }

//    @Override
//    public void run(String... args) {
//        Scanner sc = new Scanner(System.in);
//
//        for (;;) {
//            System.out.print("say something > ");
//            String line = sc.nextLine().trim();
//            if (line.length() == 0)
//                continue;
//            if (line.equals("quit"))
//                break;
//            System.out.println("You said > " + line);
//            LocalDate ldt = LocalDate.parse(line);
//            invokeGuildAnchorSettlementFor(ldt);
//        }
//    }
}
