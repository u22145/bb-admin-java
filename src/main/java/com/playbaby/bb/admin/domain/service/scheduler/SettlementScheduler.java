package com.playbaby.bb.admin.domain.service.scheduler;

import com.playbaby.bb.admin.domain.service.SettlementService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Slf4j
@Service
public class SettlementScheduler {
    private SettlementService settlementService;

    @Autowired
    public SettlementScheduler(SettlementService settlementService) {
        this.settlementService = settlementService;
    }

    @Scheduled(cron = "0 0 3 * * ?")
    private void invokeGuildSettlement() {
        LocalDate yesterday = LocalDate.now().minusDays(1L);

        settlementService.invokeGuildSettlementFor(yesterday);
        settlementService.invokeGuildAnchorSettlementFor(yesterday);
    }
}
