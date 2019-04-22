package com.sixtyrobbers.GSQ.fourm.common.util.oid;

import java.security.SecureRandom;

/**
 * <pre>
 * Explain: oid生产工具
 * Author: holennnnnn_
 * Create_Time: 2019/3/28 15:01
 * Version: V1.0
 * </pre>
 */
public class OIDGennerator {

    private static SecureRandom seeder;

    static {
        seeder = new SecureRandom();
    }

    private OIDGennerator() {
    }

    public static String getOID() {
        long lngTime = System.currentTimeMillis() + (int) (Math.random() * 100);
        int i = (int) lngTime & -1;
        int j = seeder.nextInt();
        String strOID = hexFormat(i, 10) + hexFormat(j, 10);
        return strOID.toUpperCase();
    }

    private static String hexFormat(int i, int j) {
        String s = Integer.toHexString(i);
        return padHex(s, j) + s;
    }

    private static String padHex(String s, int i) {
        StringBuffer stringbuffer = new StringBuffer();
        if (s.length() < i) {
            for (int j = 0; j < i - s.length(); j++) {
                stringbuffer.append("0");
            }
        }
        return stringbuffer.toString();
    }
}
