package cn.tycoding.scst.component.chat.config;


/**
 * 定时任务
 *
 * @author tycoding
 * @date 2019-06-17
 */
//@Slf4j
//@Component
//@Configuration
//@EnableScheduling
public class ScheduleTaskConfig {
//    private static final Long MINUTE_30 = 1000 * 60 * 30L;
//
//    @Autowired
//    private StringRedisTemplate redisTemplate;
//
//    @Autowired
//    private ChatSessionService chatSessionService;
//
//    @Scheduled(cron = "0 */30 * * * ?")
//    private void clearUser() {
//        log.info("定时任务 >>>>>>>>>> 清除注册时间超过30分钟的账户，以及其会话信息");
//
//        List<User> userList = chatSessionService.onlineList();
//        userList.forEach(user -> {
//            if ((new Date().getTime() - user.getId()) >= MINUTE_30) {
//                chatSessionService.delete(user.getId().toString());
//                if (redisTemplate.boundValueOps(ChatConstant.CHAT_COMMON_PREFIX + user.getId()).get() != null) {
//                    redisTemplate.delete(ChatConstant.CHAT_COMMON_PREFIX + user.getId());
//                }
//                if (redisTemplate.boundValueOps(ChatConstant.CHAT_FROM_PREFIX + user.getId()).get() != null) {
//                    redisTemplate.delete(ChatConstant.CHAT_FROM_PREFIX + user.getId());
//                }
//            }
//        });
//    }
}
