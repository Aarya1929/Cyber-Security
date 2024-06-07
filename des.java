package cs;

import java.util.Scanner;

public class des {
    static int[] P10 = {3, 5, 2, 7, 4, 10, 1, 9, 8, 6};
    static int[] P8 = {6, 3, 7, 4, 8, 5, 10, 9};
    static int[] Exp = {4, 1, 2, 3, 2, 3, 4, 1};
    static int[] P4 = {2, 4, 3, 1};
    static int[] IP = {2, 6, 3, 1, 4, 8, 5, 7};
    static int[] IP_inv = {4, 1, 3, 5, 7, 2, 8, 6};
    static int[][] S0 = {
            {1, 0, 3, 2},
            {3, 2, 1, 0},
            {0, 2, 1, 3},
            {3, 1, 3, 2}
    };
    

    static int[][] S1 = {
            {0, 1, 2, 3},
            {2, 0, 1, 3},
            {3, 0, 1, 0},
            {2, 1, 0, 3}
    };
    static int[] ct = new int[8];
    static int[] k1 = new int[8];
    static int[] k2 = new int[8];
    static int[] plain_text = {1, 0, 1, 0, 0, 1, 0, 1};
    static int[] key = {0, 0, 1, 0, 0, 1, 0, 1, 1, 1};
    static int row = 0, col = 0;
    static int[] s0_binary = new int[2];
    static int[] s1_binary = new int[2];
    static int[] result = new int[2];
    static int s0, s1;

    public static int[] leftShiftArray(int[] array) {
        int length = array.length;
        int temp = array[0];
        for (int i = 0; i < length - 1; i++) {
            array[i] = array[i + 1];
        }
        array[length - 1] = temp;
        return array;
    }

    public static void keys_gen(int[] key) {
        int[] new_k = new int[10];
        int[] new_k2 = new int[10];
        for (int i = 0; i < 10; i++) {
            int ind = P10[i] - 1;
            new_k[i] = key[ind];
        }
        int[] ls1 = new int[5];
        int[] ls2 = new int[5];
        for (int i = 0; i < 5; i++) {
            ls1[i] = new_k[i];
        }
        int j = 0;
        for (int i = 5; i < 10; i++) {
            while (j < 5) {
                ls2[j] = new_k[i];
                j++;
                break;
            }
        }
        ls1 = leftShiftArray(ls1);
        ls2 = leftShiftArray(ls2);

        new_k = concatenateArrays(ls1, ls2);

        System.out.printf("K1: ");
        for (int i = 0; i < 8; i++) {
            int ind = P8[i] - 1;
            k1[i] = new_k[ind];
            System.out.printf("%d", k1[i]);
        }

        int[] ls3 = ls1;
        int[] ls4 = ls2;

        ls3 = leftShiftArray(ls3);
        ls3 = leftShiftArray(ls3);
        ls4 = leftShiftArray(ls4);
        ls4 = leftShiftArray(ls4);

        new_k2 = concatenateArrays(ls3, ls4);
        System.out.println();
        System.out.printf("K2: ");
        for (int i = 0; i < 8; i++) {
            int ind = P8[i] - 1;
            k2[i] = new_k2[ind];
            System.out.printf("%d", k2[i]);
        }
    }

    public static int[] concatenateArrays(int[] array1, int[] array2) {
        int length1 = array1.length;
        int length2 = array2.length;
        int[] result = new int[length1 + length2];
        System.arraycopy(array1, 0, result, 0, length1);
        System.arraycopy(array2, 0, result, length1, length2);
        return result;
    }

    static int[] to_binary(int num) {
        int[] result = new int[2];
        if (num == 3) {
            result[0] = 1;
            result[1] = 1;
        } else if (num == 1) {
            result[0] = 0;
            result[1] = 1;
        } else if (num == 2) {
            result[0] = 1;
            result[1] = 0;
        } else if (num == 0) {
            result[0] = 0;
            result[1] = 0;
        }
        return result;
    }

    static int to_digit(int a, int b) {
        int output = 0;
        if (a == 1 && b == 1)
            output = 3;

        else if (a == 0 && b == 1)
            output = 1;

        else if (a == 1 && b == 0)
            output = 2;

        else if (a == 0 && b == 0)
            output = 0;

        return output;
    }

    public static int [] swap(int[] array) {
    	int[] swappedArray = new int[8];
        swappedArray[0] = array[4];
        swappedArray[1] = array[5];
        swappedArray[2] = array[6];
        swappedArray[3] = array[7];
        swappedArray[4] = array[0];
        swappedArray[5] = array[1];
        swappedArray[6] = array[2];
        swappedArray[7] = array[3];

        return swappedArray;
    }

    public static void decryption(int[] ct) {
        int[] ek = new int[10];
        int[] arr1 = new int[8];
        System.out.println();
        for (int i = 0; i < 8; i++) {
            int ind = IP[i] - 1;
            ek[i] = ct[ind];
        }
        arr1 = funk(k2, ek);
        arr1 = swap(arr1);
        arr1=funk(k1,arr1);
        System.out.print("PT: ");
        for (int i = 0; i < 8; i++) {
            int ind = IP_inv[i] - 1;
            ct[i] = arr1[ind];
            System.out.printf("%d",ct[i]);
        }
    }
    
    public static void encryption(int[] plain_text) {
        int[] ek = new int[10];
        int[] arr1 = new int[8];
        System.out.println();
        for (int i = 0; i < 8; i++) {
            int ind = IP[i] - 1;
            ek[i] = plain_text[ind];
        }
        arr1 = funk(k1, ek);
        arr1 = swap(arr1);
        arr1=funk(k2,arr1);
        System.out.print("CT: ");
        for (int i = 0; i < 8; i++) {
            int ind = IP_inv[i] - 1;
            ct[i] = arr1[ind];
            System.out.printf("%d",ct[i]);
        }
    }

    public static int [] funk(int[] fk, int[] ek) {
        int[] left = new int[4];
        int[] right = new int[4];
        for (int i = 0; i < 4; i++) {
            left[i] = ek[i];
        }
        int j = 0;
        for (int i = 4; i < 8; i++) {
            while (j < 4) {
                right[j] = ek[i];
                j++;
                break;
            }
        }
        int[] extend = new int[8];
        for (int i = 0; i < 8; i++) {
            int ind = Exp[i] - 1;
            extend[i] = right[ind];
        }
        int[] res = new int[8];
        for (int i = 0; i < 8; i++) {
            res[i] = extend[i] ^ fk[i];
        }
        int[] l = new int[4];
        int[] r = new int[4];
        for (int i = 0; i < 4; i++) {
            l[i] = res[i];
        }
        j = 0;
        for (int i = 4; i < 8; i++) {
            while (j < 4) {
                r[j] = res[i];
                j++;
                break;
            }
        }
        row = to_digit(l[0], l[3]);
        col = to_digit(l[1], l[2]);
        s0 = S0[row][col];
        s0_binary = to_binary(s0);


        row = to_digit(r[0], r[3]);
        col = to_digit(r[1], r[2]);
        s1 = S1[row][col];
        s1_binary = to_binary(s1);

        int [] for_P4 = new int [4];
        int [] next = new int [4];
        for_P4 = concatenateArrays(s0_binary, s1_binary);
        for (int i = 0; i < 4; i++) {
            int ind = P4[i] - 1;
            next[i] = for_P4[ind];
        }
        int[] last = new int[4];
        for (int i = 0; i < 4; i++) {
            last[i] = next[i] ^ left[i];
        }
        
        extend = concatenateArrays(last, right);
        
        return extend;
    }

    public static void main(String[] args) {
        keys_gen(key);
        encryption(plain_text);
        decryption(ct);
    }
}
