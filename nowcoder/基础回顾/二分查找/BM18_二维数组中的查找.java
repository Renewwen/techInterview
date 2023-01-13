public class Solution {
    public boolean Find(int target, int [][] array) {
        if (array == null || array.length == 0) {
            return false;
        }
        // 算法核心：杨氏矩阵
        int row = 0;
        int col = array[0].length - 1;
        while (row < array.length && col >= 0) {
            if (array[row][col] == target) {
                return true;
            } else if (array[row][col] > target) {
                col--;
            } else {
                row++;
            }
        }
        return false;
    }
}