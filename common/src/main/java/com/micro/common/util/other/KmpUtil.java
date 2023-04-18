package com.micro.common.util.other;

import java.util.Vector;

/**
 * @author XiongJiaMin
 * @apiNote KMP 算法
 * @since 2022-12-02 17:11
 **/
public class KmpUtil {

    /**
     * 获取匹配字符串在指定字符串中的位置
     * @param tarStr 原字符串
     * @param mapStr 匹配的字符串
     * @return 有则返回下标, 没有则返回-1
     */
    public static int kmpMapping(String tarStr, String mapStr) {
        int tarIndex = 0;
        int mapIndex = 0;
        char[] tarArr = tarStr.toCharArray();
        char[] mapArr = mapStr.toCharArray();
        Vector<Integer> pmt = genPmt(mapStr);
        while (tarIndex < tarStr.length()) {
            if (tarArr[tarIndex] == mapArr[mapIndex]) {
                tarIndex++;
                mapIndex++;
            } else if (mapIndex > 0) {
                mapIndex = pmt.get(mapIndex - 1);
            } else {
                tarIndex++;
            }
            if (mapIndex == mapStr.length()) {
                return tarIndex - mapIndex;
            }
        }
        return -1;
    }

    /**
     * 自我匹配, 生成 PMT 序列, 用于加快匹配时的遍历
     * { @link 'https://www.baidu.com/s?ie=utf-8&f=8&rsv_bp=1&tn=baidu&wd=PMT%E5%BA%8F%E5%88%97' }
     * @param p 字符串
     * @return PMT 序列
     */
    private static Vector<Integer> genPmt(String p) {
        Vector<Integer> pmt = new Vector<>(p.length());
        pmt.addElement(0);
        int x = 1;
        int now = 0;
        char[] pChar = p.toCharArray();
        while (x < p.length()) {
            if (pChar[now] == pChar[x]) {
                x++;
                now++;
                pmt.addElement(now);
            } if (now > 0) {
                now = pmt.get(now - 1);
            } else {
                pmt.addElement(0);
                x++;
            }
        }
        return pmt;
    }
}
