package cn.ac.yhao.java8;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.cookie.Cookie;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.stream.Stream;

/**
 * @description:
 * @author: Daniel Young
 * @create: 2021-10-07 16:40
 */
public class StreamExamples {

    public static int addUp(Stream<Integer> numbers) {
        return numbers.reduce(0, (acc, x) -> {if(acc>0) return acc + x; else return x;});
    }

    @Test
    public  void testfilter() {
        Cookie[] cookies = new Cookie[0];
        String value = Arrays.stream(cookies).filter(cookie -> StringUtils.equals(cookie.getName(), "X-Tag")).findAny().orElse(null).getValue();
        System.out.println(value);
    }
}
