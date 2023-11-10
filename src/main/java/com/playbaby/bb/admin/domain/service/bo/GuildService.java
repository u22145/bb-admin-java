package com.playbaby.bb.admin.domain.service.bo;

import com.playbaby.bb.admin.common.db.mapper.GuildMapper;
import com.playbaby.bb.admin.common.db.mapper.UserMapper;
import com.playbaby.bb.admin.common.entity.io.GuildIO;
import com.playbaby.bb.admin.common.entity.io.GuildQueryIO;
import com.playbaby.bb.admin.common.entity.po.GuildPO;
import com.playbaby.bb.admin.common.entity.vo.guild.bo.GuildVO;
import com.playbaby.bb.admin.common.exception.APIException;
import com.playbaby.bb.admin.common.util.validator.GuildValidator;
import com.playbaby.bb.admin.common.util.Paging;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.playbaby.bb.admin.common.constant.CommonConst.PAGE_SIZE;

@Slf4j
@Service
public class GuildService {
    private GuildMapper guildMapper;
    private UserMapper userMapper;

    @Autowired
    public GuildService(GuildMapper guildMapper,
                        UserMapper userMapper) {
        this.guildMapper = guildMapper;
        this.userMapper = userMapper;
    }

    /**
     * 公會列表
     */
    public Paging<GuildVO> findGuilds(GuildQueryIO io) {
        // make sure all the fields in guildQueryIO are sane
        GuildValidator.rationalize(io);

        return Paging.getInstance(io.getPageInt(), PAGE_SIZE, GuildMapper::findGuilds, guildMapper, io);
    }

    /**
     * 創建公會
     * @param guildIO 公會資訊
     * @throws APIException 如無法創建公會丟出的錯誤訊息
     */
    public void createGuild(GuildIO guildIO) throws APIException {
        GuildPO guildPO = GuildValidator.validate(guildIO);
        Integer masterId = guildIO.getMasterId();
        Integer cnt = masterId != null ? userMapper.userExists(masterId) : 0;

        if (cnt == null || cnt == 0)
            throw APIException.getInstance(100001, "user " + guildIO.getMasterId() + " does not exist");

        // new guild is active by default
        if (guildIO.getStatus() == null)
            guildIO.setStatus(1);
        guildPO.setMasterId(masterId);
        try {
            guildMapper.createGuild(guildPO);
        } catch (Exception e) {
            log.error("cannot create guild {}", e.getMessage(), e);
            throw APIException.getInstance(100011, e.getMessage());
        }
    }

    /**
     * 編輯公會
     * @param guildIO 新的公會資訊
     * @throws APIException 如無法編輯公會丟出的錯誤訊息
     */
    public void updateGuild(GuildIO guildIO) throws APIException {
        // even though we will not be updating the guild name,
        // the name still has to be set in order to pass the validator
        guildIO.setName("DUMMY");

        Integer id = guildIO.getId();
        if (id == null)
            throw APIException.getInstance(100011, "guild id cannot be empty");

        if (!guildMapper.guildExists(id))
            throw APIException.getInstance(100012, "guild " + id + " does not exist");

        GuildPO guildPO = GuildValidator.validate(guildIO);
        guildPO.setId(id);
        try {
            guildMapper.updateGuild(guildPO);
        } catch (Exception e) {
            log.error("cannot update guild {}", e.getMessage(), e);
            throw APIException.getInstance(100010, e.getMessage());
        }
    }

//    @Override
//    public void run(String... args) {
//        Scanner sc = new Scanner(System.in);
//        // BigDecimal commission = new BigDecimal("0.15");
//        String commission = "0.09";
//
//        for (;;) {
//            System.out.print("Say something > ");
//            String line = sc.nextLine().trim();
//            if (line.length() == 0)
//                continue;
//            if (line.equalsIgnoreCase("quit"))
//                break;
//            System.out.println("You said > " + line);
//            int id;
//            try {
//                id = Integer.parseInt(line);
//            } catch (NumberFormatException e) {
//                e.printStackTrace();
//                continue;
//            }
//
//            GuildIO guildIO = GuildIO.builder()
//                .id(id)
//                .name("HELLO Guild")
//                .giftCommission(commission)
//                .lotteryCommission(commission)
//                .privateStreamCommission(commission)
//                .streamTicketCommission(commission)
//                .ugcCommission(commission)
//                .datingCommission(commission)
//                .messageCommission(commission)
//                .masterNickname("钵钵鸡123")
//                .build();
//            try {
//                // updateGuild(guildIO);
//                createGuild(guildIO);
//                System.out.println("OK");
//            } catch (APIException e) {
//                System.out.println(e);
//            }
//        }
//    }



//    @Override
//    public void run(String... args) {
//        Scanner sc = new Scanner(System.in);
//
//        while (true) {
//            System.out.print("Say something > ");
//            String line = sc.nextLine().trim();
//            if (line.length() == 0)
//                continue;
//            if ("quit".equals(line))
//                break;
//            System.out.println("You said " + line);
//            findGuilds(line);
//        }
//    }
//
//    private void findGuilds(String line) {
//        GuildQueryIO io = GuildQueryIO.builder().guildId(null).masterId(null).status(null).build();
//        List<GuildVO> list = guildMapper.findGuilds(io);
//        list.forEach(System.out::println);
//    }
//
//    private void pagination(String line) {
//        PageHelper.startPage(3, 4, true);
//        GuildQueryIO io = GuildQueryIO.builder().guildId(null).masterId(null).status(null).build();
//        PageInfo<GuildVO> pi = PageInfo.of(guildMapper.findGuilds(io));
//        System.out.println(pi);
//        PageHelper.clearPage();
//    }
}
