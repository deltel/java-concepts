package test;

import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CompletableFutureTest {

    @Test
    void supplyAsync() throws ExecutionException, InterruptedException {
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> "Hello");
        String greeting = completableFuture.get();

        assertEquals("Hello", greeting);
    }

    @Test
    void thenApply() throws ExecutionException, InterruptedException {
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> "Hello");
        CompletableFuture<String> future = completableFuture.thenApply(str -> str + " World");
        String greeting = future.get();

        assertEquals("Hello World", greeting);
    }

    @Test
    void thenApplyChain() throws ExecutionException, InterruptedException {
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> "Hello");
        CompletableFuture<String> future = completableFuture.thenApply(str -> str + " World");
        CompletableFuture<String> finalFuture = future.thenApply(str -> str + "!");
        String greeting = finalFuture.get();

        assertEquals("Hello World!", greeting);
    }

}
