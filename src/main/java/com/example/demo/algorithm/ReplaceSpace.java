package com.example.demo.algorithm;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.HashMap;

/**
 * @author LIJIALONG1
 */
public class ReplaceSpace {

    @Autowired
    private RedisTemplate redisTemplate;

    private static HashMap<Integer, Long> temp = new HashMap<>();

    private String replaceSpace(String str) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c == ' ') {
                sb.append("%20");
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    private int[] moveZero() {
        int[] a = new int[]{1, 0, 2, 5, 0, 1};
        int j = 0;
        for (int i = 0; i < a.length; i++) {
            if (a[i] != 0) {
                a[j] = a[i];
                if (i != j) {
                    a[i] = 0;
                }
                j++;
            }
        }
        return a;
    }

    private int[] moveZero1() {
        int[] a = new int[]{1, 0, 2, 5, 0, 1};
        int j = 0;
        for (int i = 0; i < a.length; i++) {
            if (a[i] != 0) {
                int temp = a[j];
                a[j] = a[i];
                a[i] = temp;
                j++;
            }
        }
        return a;
    }

    //斐波那契
    private static long f(int n) {
        if (n < 3) {
            return 1;
        }
        return f(n - 2) + f(n - 1);
    }

    private static long f1(int n) {
        if (n < 3) {
            return 1;
        }
        if (!temp.containsKey(n)) {
            temp.put(n, f1(n - 2) + f1(n - 1));
        }
        return temp.get(n);
    }

    private static long f2(int n) {
        if (n < 3) {
            return 1;
        }
        int[] temp = new int[n + 1];
        for (int i = 3; i < temp.length; i++) {
            temp[i] = temp[i - 2] + temp[n - 1];
        }
        return temp[n];
    }

    private static long f3(int n) {
        if (n < 3) {
            return 1;
        }
        int a = 1;
        int b = 1;
        int c = 0;
        for (int i = 3; i <= n; i++) {
            c = a + b;
            a = b;
            b = c;
        }
        return c;
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        long result = f3(100);

        System.out.println("result :" + result);
        System.out.println("consuming :" + (System.currentTimeMillis() - start));
    }

}
