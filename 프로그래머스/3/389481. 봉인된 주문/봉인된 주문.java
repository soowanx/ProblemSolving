/*
진법 변환. 문자열 비교 or 배열 비교

*/

import java.util.*;

class Solution {
    public String solution(long n, String[] bans) {
//         // bans를 순번으로 반환
//         long[] banRanks = new long[bans.length];
//         for (int i = 0; i < bans.length; i++) {
//             banRanks[i] = getRank(bans[i]);
//         }
        
//         Arrays.sort(banRanks);
        
//         // n 보정
//         for (long ban : banRanks) {
//             if (ban <= n) n++;
//             else break;
//         }
        
//         return getString(n);
        
        Arrays.sort(bans, (a, b) -> {
            if (a.length() != b.length()) return a.length() - b.length();
            return a.compareTo(b);
        });
        
        for (String ban : bans) {
            if (compare(ban, getString(n)) <= 0) n++;
            else break;
        }
        
        return getString(n);
    }
    
    int compare(String a, String b) {
        if (a.length() != b.length()) {
            return a.length() - b.length();
        }
        return a.compareTo(b);
    }
    
    long getRank(String s) {
        long rank = 0;
        for (int i = 0; i < s.length(); i++) rank = rank * 26 + (s.charAt(i) - 'a' + 1);
        return rank;
    }
    
    String getString(long n) {
        StringBuilder sb = new StringBuilder();
        
        while (n > 0) {
            n--;
            sb.append((char) ('a' + (n % 26)));
            n /= 26;
        }
        
        return sb.reverse().toString();
    }
}