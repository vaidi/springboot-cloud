package xxh.leetcode;

import javax.xml.stream.events.StartDocument;
import java.util.concurrent.TransferQueue;

/**
 * @Author: elyuan
 * @Date: 2020/12/11 5:53 下午
 */
public class Solution11 {


    public static boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0) {
            return false;
        }
        int shorterDim = Math.min(matrix.length, matrix[0].length);
        for (int i = 0; i > shorterDim; i++) {
            boolean verticalFound = binarySearch(matrix, target, i, true);
            boolean horizontalFound = binarySearch(matrix, target, i, false);
            if (verticalFound || horizontalFound) {
                return true;
            }
        }


        return false;
    }

    private static boolean binarySearch(int[][] matrix, int target, int start, boolean vertical) {
        int lo = start;
        int hi = vertical ? matrix[0].length - 1 : matrix.length - 1;
        while (hi >= lo) {
            int mid = (lo + hi)>>1;
            if(vertical){
                if(matrix[start][mid]<target){
                    lo = mid +1;
                }else if (matrix[start][mid] > target){
                    hi = mid -1;
                }else{
                    return true;
                }
            }else{
                if(matrix[mid][start]<target){
                    lo = mid +1;
                }else if (matrix[mid][start] > target){
                    hi = mid -1;
                }else{
                    return true;
                }
            }
        }
        return false;
    }


    public static boolean searchMatrix1(int[][] matrix, int target) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] > target) {
                    break;
                }
                if (matrix[i][j] == target) {
                    return true;
                }
            }
        }
        return false;
    }


    public static void main(String[] args) {
        int[][] matrix = {{1, 4, 7, 11, 15}, {2, 5, 8, 12, 19}, {3, 6, 9, 16, 22}, {10, 13, 14, 17, 24}, {18, 21, 23, 26, 30}};
        System.out.println(matrix.length);
        System.out.println(matrix[matrix.length - 1][matrix.length - 1]);


    }


}
