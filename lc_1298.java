package leetcode;

import java.util.LinkedList;

class Solution1298 {
    public boolean canOpen(int box, int[] status, boolean[] canBeOpened) {
        if (status[box] == 0 && !canBeOpened[box]) {
            return false;
        }

        return true;
    }

    public int maxCandies(int[] status, int[] candies, int[][] keys, int[][] containedBoxes, int[] initialBoxes) {
        boolean[] canBeOpened = new boolean[status.length];
        LinkedList<Integer> queue = new LinkedList<>();
        for(int box : initialBoxes){
            queue.offer(box);
        }
        int totalCandies = 0;
        while(!queue.isEmpty()){
            boolean atleastOneOpened = false;
            for(int i=0; i<queue.size(); i++){
                int curBox = queue.poll();
                if(canOpen(curBox, status, canBeOpened)){
                    atleastOneOpened = true;
                    totalCandies += candies[curBox];
                    int[] key = keys[curBox];
                    for(int k : key){
                        canBeOpened[k] = true;
                    }
                    int[] boxes = containedBoxes[curBox];
                    for(int box : boxes){
                        queue.offer(box);
                    }
                } else{
                    queue.offer(curBox);
                }
            }
            if(!atleastOneOpened){
                break;
            }
        }

        return totalCandies;
    }
}
