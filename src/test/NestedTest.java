package test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class NestedTest {
    @BeforeEach
    void beforeEach() {
        System.out.println("Before all tests");
    }

    @Test
    void baseTest() {
        System.out.println("Base test method");
    }

    @Nested
    class FirstNestedClass {
        @BeforeEach
        void beforeEach() {
            System.out.println("Before all > first nested class");
        }

        @Test
        void firstNestedTest() {
            System.out.println("First nested test");
        }
    }

    @Nested
    class SecondTestClass {
        @BeforeEach
        void beforeEach() {
            System.out.println("Before all > second nested class");
        }

        @Test
        void secondNestedTest() {
            System.out.println("Second nested test");
        }
    }
}
