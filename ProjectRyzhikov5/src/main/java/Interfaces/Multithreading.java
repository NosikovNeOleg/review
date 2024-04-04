package Interfaces;

import java.util.concurrent.ExecutionException;

public interface Multithreading {

    void ParallelGenerationRandoms (int threadsQuantity, int numbersQuantity) throws ExecutionException, InterruptedException;

    void ThreadSafelyCounter (int threadsQuantity, int numbersQuantity) throws InterruptedException;

    void ParallelFactorialCalculation (int threadsQuantity, int numbersQuantity) throws ExecutionException, InterruptedException;

}
