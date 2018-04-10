package xyz.sethy.woutobot;

import xyz.sethy.woutobot.dto.OptimizedString;

/**
 * Created by Seth on 30/08/2017.
 */
public class Main {
    public static void main(String[] args) {
//        new WoutoBot();

        int i = 0;
        long start = System.currentTimeMillis();
        char[] same = new char[]{"a".toCharArray()[0], "b".toCharArray()[0]};
        while(i < 100000) {
            OptimizedString string = OptimizedString.valueOf(same);
            i++;
        }
        System.out.println("toke " + (System.currentTimeMillis() - start) + "ms for 100000 optimized strings");

        int i1 = 0;
        long start1 = System.currentTimeMillis();
        while(i1 < 100000) {
            String string = String.valueOf(same);
            i1++;
        }

        System.out.println("toke " + (System.currentTimeMillis() - start1) + "ms for 100000 strings");

    }
}
