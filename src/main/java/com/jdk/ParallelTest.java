package com.jdk;

import java.util.function.Function;
import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 * @author jiahangchun
 */
public class ParallelTest {

    /**
     * 计算时间
     *
     * @param adder
     * @param n
     */
    public static void measureTimeRef(Function<Long, Long> adder, long n) {
        Long start = System.currentTimeMillis();
        adder.apply(n);
        Long end = System.currentTimeMillis();
        System.out.println("方法执行时间耗时： " + (end - start));
    }

    /**
     * 一堆计算
     * @param n
     * @return
     */
    public static long sequentialSum(long n) {
        return Stream.iterate(1L, i -> i + 1)
                .limit(n)
                .reduce(0L, Long::sum);
    }

    public static long unBoxSequentialSum(long n) {
        return LongStream.rangeClosed(1, n)
                .parallel()
                .reduce(0L, Long::sum);
    }

    public static long unBoxIteraticeSum(long n) {
        return LongStream.rangeClosed(1, n)
                .reduce(0L, Long::sum);
    }

    public static long iterativeSum(long n) {
        long result = 0;
        for (long i = 1L; i <= n; i++) {
            result += i;
        }
        return result;
    }

    public static void main(String[] args) {
        measureTimeRef(ParallelTest::sequentialSum, 10_000_000);
        measureTimeRef(ParallelTest::unBoxSequentialSum, 10_000_000);
        measureTimeRef(ParallelTest::iterativeSum, 10_000_000);
        measureTimeRef(ParallelTest::unBoxIteraticeSum, 10_000_000);
    }
}
