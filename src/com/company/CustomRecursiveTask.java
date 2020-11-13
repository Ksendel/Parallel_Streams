package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class CustomRecursiveTask extends RecursiveTask<Long> {
    private int[] arr;

    private static final int THRESHOLD = 20;

    public CustomRecursiveTask(int[] arr) {
        this.arr = arr;
    }

    @Override
    protected Long compute() {
        if (arr.length > THRESHOLD) {
            System.out.println("Dividing in thread: " + Thread.currentThread().getId());
            return ForkJoinTask.invokeAll(createSubtasks())
                    .stream()
                    .mapToLong(ForkJoinTask::join)
                    .sum();
        } else {
            return processing(arr);
        }
    }

    private Collection<CustomRecursiveTask> createSubtasks() {
        List<CustomRecursiveTask> dividedTasks = new ArrayList<>();
        dividedTasks.add(new CustomRecursiveTask(
                Arrays.copyOfRange(arr, 0, arr.length/2)));
        dividedTasks.add(new CustomRecursiveTask(
                Arrays.copyOfRange(arr, arr.length/2, arr.length)));
        return dividedTasks;
    }

    private long processing(int[]arr) {
        System.out.println("Computing in thread: " + Thread.currentThread().getId()
                + "\n--- elems: " + Arrays.toString(arr));
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        return Arrays.stream(arr).sum();
    }
}
