package com.jms.time;

import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/7/2 11:13
 */
public class JudgmentTime {

    public static void main(String[] args) {
        LocalTime start = LocalTime.parse("03:00:00");
        LocalTime end = LocalTime.parse("22:00:00");

//        LocalTime now = LocalTime.now();
//        LocalTime now = LocalTime.parse("23:00:00");
//        LocalTime now = LocalTime.parse("22:00:01");
        LocalTime now = LocalTime.parse("03:00:01");
//        LocalTime now = LocalTime.parse("00:00:00");
        boolean after = now.isAfter(start);
        boolean before = now.isBefore(end);

        System.out.println(!(after && before) ? "是" : "不是");

    }
}
