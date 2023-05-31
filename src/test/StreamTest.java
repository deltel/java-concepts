package test;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StreamTest {
    @Test
    @DisplayName("returns true if string is in stream")
    void anyMatchReturnsTrue() {
        assertTrue(Stream.of("one", "two", "three").anyMatch(str -> str.equalsIgnoreCase("One")));
        assertTrue(Stream.of("one", "two", "three").anyMatch(str -> str.equalsIgnoreCase("Two")));
        assertTrue(Stream.of("one", "two", "three").anyMatch(str -> str.equalsIgnoreCase("Three")));
    }

    @Test
    @DisplayName("returns false if string is not in stream")
    void anyMatchReturnsFalse() {
        assertFalse(Stream.of("one", "two", "three").anyMatch(str -> str.equalsIgnoreCase("Ten")));
    }
}
