package com.jdk;

import java.util.concurrent.RecursiveTask;

public class TaskFork extends RecursiveTask<Long> {
    @Override
    protected Long compute() {
        System.out.println(111);
        return null;
    }
}
