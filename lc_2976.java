package leetcode;

class Solution2976 {
    public long minimumCost(String inputText, String desiredText, char[] fromLetters, char[] toLetters, int[] transformationCost) {
        int[][] matrix = new int[26][26];

        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < 26; j++) {
                if (i == j) {
                    matrix[i][j] = 0;
                } else {
                    matrix[i][j] = Integer.MAX_VALUE;
                }
            }
        }

        for (int i = 0; i < fromLetters.length; i++) {
            int fromLetter = fromLetters[i] - 'a';
            int toLetter = toLetters[i] - 'a';
            int cost = transformationCost[i];

            matrix[fromLetter][toLetter] = Math.min(matrix[fromLetter][toLetter], cost);
        }

        for (int k = 0; k < 26; k++) {
            for (int i = 0; i < 26; i++) {
                for (int j = 0; j < 26; j++) {
                    if (matrix[i][k] != Integer.MAX_VALUE && matrix[k][j] != Integer.MAX_VALUE) {
                        matrix[i][j] = Math.min(matrix[i][j], matrix[i][k] + matrix[k][j]);
                    }
                }
            }
        }

        long res = 0;
        for (int i = 0; i < inputText.length(); i++) {
            int startChar = inputText.charAt(i) - 'a';
            int endChar = desiredText.charAt(i) - 'a';

            if (matrix[startChar][endChar] == Integer.MAX_VALUE) {
                return -1;
            } else {
                res += matrix[startChar][endChar];
            }
        }

        return res;
    }
}