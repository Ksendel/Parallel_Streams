package com.company;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) {

        ForkJoinPool forkJoinPool = new ForkJoinPool(4);
        int[] arr = IntStream.range(0, 100).toArray();
//        int[] arr = new int[1000000];
//        for (int i = 0; i < arr.length; i++) {
//            arr[i] = (int) (Math.random() * 100);
//        }

        CustomRecursiveTask task = new CustomRecursiveTask(arr);
        long sum = forkJoinPool.invoke(task);

        System.out.println(sum);
    }
}
