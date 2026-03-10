/*
카드를 가지는 경우의 수: 0, 1, 2
*/

import java.util.*;

class Solution {
    public int solution(int coin, int[] cards) {
        int n = cards.length;
        int target = n + 1;
        
        Set<Integer> myCard = new HashSet<>();
        Set<Integer> newCard = new HashSet<>();
        for (int i = 0; i < n / 3; i++) myCard.add(cards[i]);
        
        // 카드 뽑기
        int round = 1;
        
        for (int i = n / 3; i < n; i+=2) {
            newCard.add(cards[i]);
            newCard.add(cards[i + 1]);
            
            boolean played = false;
            
            // 코인 0개
            for (int card : new HashSet<>(myCard)) {
                int pair = target - card;
                
                if (myCard.contains(pair)) {
                    myCard.remove(card);
                    myCard.remove(pair);
                    played = true;
                    break;
                }
            }
            
            // 코인 1개
            if (!played && coin >= 1) {
                for (int card : new HashSet<>(myCard)) {
                    int pair = target - card;
                    
                    if (newCard.contains(pair)) {
                        myCard.remove(card);
                        newCard.remove(pair);
                        coin--;
                        played = true;
                        break;
                    }
                }
            }
            
            // 코인 2개
            if (!played && coin >= 2) {
                for (int card : new HashSet<>(newCard)) {
                    int pair = target - card;
                    
                    if (newCard.contains(pair)) {
                        newCard.remove(pair);
                        newCard.remove(card);
                        coin -= 2;
                        played = true;
                        break;
                    }
                }
            }
            
            if (!played) break;
            round++;
        }
        
        return round;
    }
}
