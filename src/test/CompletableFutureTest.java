package test;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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

    @Test
    void thenAccept() throws ExecutionException, InterruptedException {
        List<String> stringList = new ArrayList<>();
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> "Hello");
        CompletableFuture<Void> future = completableFuture.thenAccept(stringList::add);
        future.get();

        assertEquals(1, stringList.size());
    }

    @Test
    void thenApply_thenAccept() throws ExecutionException, InterruptedException {
        List<String> stringList = new ArrayList<>();
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> "Hello");
        CompletableFuture<String> future = completableFuture.thenApply(s -> s + " World!");
        CompletableFuture<Void> voidFuture = future.thenAccept(stringList::add);
        voidFuture.get();

        assertEquals(1, stringList.size());
    }

    @Test
    void allOf() throws ExecutionException, InterruptedException {
        List<String> stringList = Arrays.asList("Hello", "Good");
        List<String> stringListCopy = new ArrayList<>();

        CompletableFuture<?>[] futures = stringList.stream().map(str -> CompletableFuture.supplyAsync(() -> str + " Hello")
                .thenApply(s -> s + " World!").thenAccept(stringListCopy::add)).toArray(CompletableFuture[]::new);

        CompletableFuture<Void> finalFuture = CompletableFuture.allOf(futures);
        finalFuture.get();

        assertEquals(2, stringListCopy.size());
    }

}
