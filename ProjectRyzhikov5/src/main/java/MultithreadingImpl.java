import Interfaces.Multithreading;

import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class MultithreadingImpl implements Multithreading {
    public void ParallelGenerationRandoms(int threadsQuantity, int numbersQuantity) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(threadsQuantity);
        Future[] futures = new Future[threadsQuantity];

        for (int i = 0; i < threadsQuantity; i++) {
            futures[i] = executorService.submit(new ParallelGenerationRandomsTask(numbersQuantity));
            System.out.println("Номер потока: " + (i + 1) + " Затраченное время: " + futures[i].get());
        }
        executorService.shutdown();
    }

    public void ThreadSafelyCounter(int threadsQuantity, int numbersQuantity) throws InterruptedException {
        // В каждом потоке (количество: threadsQuantity) по numbersQuantity увеличений счетчика

        ExecutorService executorService = Executors.newFixedThreadPool(threadsQuantity);
        AtomicInteger atomicInteger = new AtomicInteger();

        for (int i = 0; i < threadsQuantity; i++) {
            executorService.submit(new AtomicNumberTask(atomicInteger, numbersQuantity));
        }
        executorService.shutdown();
        executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.HOURS);
        System.out.println("Atomic Integer " + atomicInteger.get());
    }

    public void ParallelFactorialCalculation(int threadsQuantity, int numberForFactorial) throws ExecutionException, InterruptedException {
        String comment = "";
        if (numberForFactorial > 20) {
            comment = "Факториал числа " + numberForFactorial + " выходит за диапазон значений Long. ";
            numberForFactorial = 20; // В Long больше не помещается
        }
        ExecutorService executorService = Executors.newFixedThreadPool(threadsQuantity);
        Future[] futures = new Future[threadsQuantity];
        long factorial = 1L;
        int firstNumber;
        int numbersPartQuantity;
        int numbersAlreadyUsed = 0;
        for (int i = 0; i < threadsQuantity; i++) {
            firstNumber = numbersAlreadyUsed + 1;
            numbersPartQuantity = (numberForFactorial - numbersAlreadyUsed) / (threadsQuantity - i);
            futures[i] = executorService.submit(new ParallelFactorialCalculationTask(firstNumber, numbersPartQuantity));
            numbersAlreadyUsed += numbersPartQuantity;

        }
        for (int i = 0; i < threadsQuantity; i++) {
            factorial = factorial * (long) futures[i].get();
        }
        System.out.println(comment + "Факториал " + numberForFactorial + " равен: " + factorial);

        executorService.shutdown();
    }


    static class ParallelGenerationRandomsTask implements Callable<Long> {

        private final int numbersQuantity;

        public ParallelGenerationRandomsTask(int numbersQuantity) {
            this.numbersQuantity = numbersQuantity;
        }

        @Override
        public Long call() {
            long startTime = System.currentTimeMillis();
            int currentNumber;
            for (int i = 0; i < numbersQuantity; i++) {
                currentNumber = new Random().nextInt();
                //System.out.println(currentNumber);
            }
            long endTime = System.currentTimeMillis();

            // System.out.println("Start: " + startTime + " End: " + endTime + " Кол-во: " + numbersQuantity);
            return endTime - startTime;
        }
    }

    static class AtomicNumberTask implements Callable<Long> {

        private final int numbersQuantity;
        private final AtomicInteger atomicInteger;

        public AtomicNumberTask(AtomicInteger atomicInteger, int numbersQuantity) {
            this.numbersQuantity = numbersQuantity;
            this.atomicInteger = atomicInteger;
        }

        @Override
        public Long call() {

            for (int i = 0; i < numbersQuantity; i++) {
                atomicInteger.incrementAndGet();
            }
            return null;
        }
    }

    static class ParallelFactorialCalculationTask implements Callable<Long> {

        private final int numbersQuantity;
        private final int firstNumber;

        public ParallelFactorialCalculationTask(int firstNumber, int numbersQuantity) {
            this.numbersQuantity = numbersQuantity;
            this.firstNumber = firstNumber;
        }

        @Override
        public Long call() {
            long currentNumber = firstNumber;
            for (int i = 0; i < numbersQuantity - 1; i++) {
                currentNumber *= (firstNumber + i + 1);
            }


            return currentNumber;
        }
    }

}
