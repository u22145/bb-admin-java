package com.playbaby.bb.admin.common.db.redis;

import com.playbaby.bb.admin.common.constant.AdminRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

import static com.playbaby.bb.admin.config.RedisTemplateConfig.STR_TEMPLATE;

@Component
public class LoginRedisOp {
    private static int EXPIRE = 15;
    @SuppressWarnings("unused")
    private static String ADMIN_KEY = AdminRoles.ADMIN_ROLE + "_LOGIN:%s";
    private static String GM_KEY = AdminRoles.GM_ROLE + "_LOGIN:%s";

    private RedisTemplate<String, String> template;

    @Autowired
    public LoginRedisOp(@Qualifier(STR_TEMPLATE) RedisTemplate<String, String> template) {
        this.template = template;
    }

    public void saveGMGuildId(String sessionId, String guildId) {
        String key = String.format(GM_KEY, sessionId);
        template.opsForValue().set(key, guildId, EXPIRE, TimeUnit.MINUTES);
    }

    public String getGMGuildId(String sessionId) {
        String key = String.format(GM_KEY, sessionId);

        String val = template.opsForValue().get(key);
        if (val != null)
            template.expire(key, EXPIRE, TimeUnit.MINUTES);

        return val;
    }

    public void removeGMSession(String sessionId) {
        String key = String.format(GM_KEY, sessionId);
        template.delete(key);
    }
}
