package com.madjava.sort;

import java.util.Arrays;

/**
 * 折半插入排序
 * 利用i-1索引前面数据有序的特点用二分法查找
 */
public class InsertSort2 {
    public static void insertSort(int[] arr){
        int length = arr.length;
        for(int i = 1;i < length;i++){
            int temp = arr[i];
            int left = 0;
            int right = i - 1;
            while (left <= right){
                int mid = (left + right)/2;
                if(arr[mid] > arr[i]){
                    left = mid - 1;
                }else {
                    right = mid + 1;
                }
            }
            //将left到i的数据向后移一位
            for(int j = i;j > left;j--){
                arr[j] = arr[j-1];
            }
            arr[left] = temp;
        }
    }
    public static void main (String[] args){
        int a[] = { 38,65,97,76,13,27,49 };
        insertSort(a);
        System.out.println(Arrays.toString(a));
    }
}
