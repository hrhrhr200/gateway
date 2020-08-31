package com.example.gateway.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;

/**
 * Created by hrhrh on 2020/8/21 14:44
 */
public class SortMethod {

    public static void bubbleSort(int[] array) {

        //边界
        int sortSide = array.length - 1;
        int lastChangeIndex = 0;

        for(int i = 0; i < array.length; i++) {
            boolean changed = false;
            for(int j = 0; j < sortSide; j++) {
                if(array[j] > array[j+1]) {
                    int amp = array[j];
                    array[j]  = array[j + 1];
                    array[j + 1] = amp;
                    changed = true;
                    lastChangeIndex = j;
                }
            }

            sortSide = lastChangeIndex;

            if(!changed) {
                break;
            }
        }
    }

    public static void quickSort(int[] array, int startIndex, int endIndex) {

        if(startIndex >= endIndex) {
            return;
        }

        int partition = partition2(array, startIndex, endIndex);

        quickSort(array, startIndex, partition - 1);

        quickSort(array, partition + 1, endIndex);

    }

    /**
     * 双边循环
     * @param array
     * @param startIndex
     * @param endIndex
     * @return
     */
    public static int partition(int[] array, int startIndex, int endIndex) {
        int amp = array[startIndex];
        int left = startIndex;
        int right = endIndex;
        while(left != right) {
            while(left < right && array[right] > amp) {
                right--;
            }

            while (left < right && array[left] <= amp) {
                left++;
            }
            if(left < right) {
                int ramp = array[right];
                array[right] = array[left];
                array[left] = ramp;
            }
        }
        array[startIndex] = array[left];
        array[left] = amp;

        return left;

    }

    public static int partition2(int[] array, int startIndex, int endIndex) {
        int amp = array[startIndex];
        int mark = startIndex;
        for(int i = startIndex + 1; i <= endIndex; i++) {
            if(array[i] >= amp) {
                mark++;
                int ampi = array[i];
                array[i] = array[mark];
                array[mark] = ampi;
            }
        }
        array[startIndex] = array[mark];
        array[mark] = amp;

        return mark;
    }

    public static void main(String[] args) {
        int[] arrays = {9,4,2,6,2,7,8,11,42,77,32};
        bubbleSort(arrays);
        System.out.println(Arrays.toString(arrays));
        int[] arrays2 = {9,4,2,6,2,7,8,11,42,77,32};
        quickSort(arrays2, 0, arrays.length - 1);
        System.out.println(Arrays.toString(arrays2));

        int[] arrays3 = {9,3,5,4,9,1,2,7,8,1,3,6,5,3,4,0,10,9,7,9};
        int[] ints = countSort(arrays3);
        System.out.println(Arrays.toString(ints));

        int[] arrays4 = {9,4,2,6,2,7,8,11,42,77,32};
        int[] ints2 = countSort2(arrays4);
        System.out.println(Arrays.toString(ints2));

        double[] arrays5 = {0.12,0.34,0.5,1.34,2.43,3.56,5.6,7.43,6.32,0.32,1.2,8.4,3.4,9.4,2.7};
        double[] ints3 = bucketSort(arrays5);
        System.out.println(Arrays.toString(ints3));
    }

    public static int[] countSort(int[] array) {
        int max = array[0];

        for (int a : array) {
            if(a > max) {
                max = a;
            }
        }

        int[] countArray = new int[max + 1];

        for (int a : array) {
            countArray[a] = countArray[a] + 1;
        }

        int[] sortArray = new int[array.length];
        int index = 0;
        for(int i = 0;i<countArray.length;i++) {
            for(int j = 0;j<countArray[i];j++) {
                sortArray[index++] = i;
            }
        }

        return sortArray;
    }

    public static int[] countSort2(int[] array) {
        int max = array[0];
        int min = array[0];

        for (int i : array) {
            if(i > max) {
                max = i;
            }

            if(i< min) {
                min = i;
            }
        }

        int[] countArray =  new int[max - min + 1];

        for (int i : array) {
            countArray[i - min]++;
        }

        int[] sortArray = new int[array.length];

        for (int i = 1; i < countArray.length; i++) {
            countArray[i] += countArray[i - 1];
        }
        for(int i = array.length -1;i>=0;i--) {
            sortArray[countArray[array[i] - min] - 1] = array[i];
            countArray[array[i] - min]--;
        }

        return sortArray;
    }


    public static double[] bucketSort(double[] array) {
        double max = array[0];
        double min = array[0];

        for (double d : array) {

            if(d > max) {
                max = d;
            }

            if(d < min) {
                min = d;
            }
        }


        double a = max - min;

        //初始化桶
        int bucketNum = array.length;
        ArrayList<LinkedList<Double>> totalBucket = new ArrayList<>(bucketNum);
        for(int i = 1;i<=bucketNum;i++) {
            totalBucket.add(new LinkedList<>());
        }

        //放入桶中
        for (double v : array) {

            int index = (int) ((int)(v - min) * (bucketNum - 1) / a);
            totalBucket.get(index).add(v);
        }

        //桶排序
        totalBucket.forEach(Collections::sort);

        //结果输出
        double[] sortArray = new double[array.length];
        int index = 0;
        for(LinkedList<Double> ll : totalBucket) {
            for (Double v : ll) {
                sortArray[index] = v;
                index++;
            }
        }

        return sortArray;
    }
}
