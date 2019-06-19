package cn.tycoding.scst.component.chat.utils;


import cn.tycoding.scst.component.chat.entity.Message;

import java.util.Comparator;
import java.util.List;

/**
 * @author tycoding
 * @date 2019-06-15
 */
public class ChatUtil {

    /**
     * 对List集合中的数据按照时间顺序排序
     *
     * @param list List<Message>
     */
    public static void sort(List<Message> list) {
        list.sort(Comparator.comparing(Message::getTime));
    }
}
