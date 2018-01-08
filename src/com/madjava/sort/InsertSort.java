package com.madjava.sort;

import java.util.Arrays;

/**
 * 插入排序法
 */
public class InsertSort {
    public static void insertSort(int[] arr){
        for(int i = 1;i < arr.length;i++){
            int temp = arr[i];//保存值不丢失
            int j = i - 1;
            //i-1 索引前的数据是有序的，如果第i-1数据小于第i个数据，说明无需插入
            if(arr[i-1] < arr[i]) continue;
            while (j >= 0 && temp < arr[j]){
                arr[j+1] = arr[j];
                j--;
            }
            arr[j+1] = temp;
        }
    }

    public static void main (String[] args){
        int a[] = { 38,65,97,76,13,27,49 };
        insertSort(a);
        System.out.println(Arrays.toString(a));
    }
}
