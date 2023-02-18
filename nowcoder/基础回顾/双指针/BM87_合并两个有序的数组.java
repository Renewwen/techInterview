import java.util.*;

public class Solution {
    // A[1,2,3,0,0,0]
    //       i
    // B[4,5,6]
    //       j
    public void merge(int A[], int m, int B[], int n) {
        int indexA = m - 1;
        int indexB = n - 1;
        for (int i = A.length - 1; i >= 0; i--) {
            if (indexB < 0 || (indexA >= 0 && A[indexA] > B[indexB])) {
                A[i] = A[indexA];
                indexA--;
            } else {
                A[i] = B[indexB];
                indexB--;
            }
        }
    }
}