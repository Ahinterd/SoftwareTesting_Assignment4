package at.jku.swtesting.utils;

import at.jku.swtesting.RingBuffer;

import java.util.function.Consumer;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestUtils {

    public final static String FISRT_ITEM = "first";
    public final static String SECOND_ITEM = "second";
    public final static String THIRD_ITEM = "third";

    public static void repeat(int n, Consumer<Integer> consumer)  {
        for (int i = 1; i <= n; i++) {
            consumer.accept(i);
        }
    }
    public static void assertFull(RingBuffer buffer){
        assertTrue(buffer.isFull());
        assertFalse(buffer.isEmpty());
    }
    public static void assertEmpty(RingBuffer buffer){
        assertTrue(buffer.isEmpty());
        assertFalse(buffer.isFull());
    }
    public static void assertPartiallyFull(RingBuffer buffer){
        assertFalse(buffer.isEmpty());
        assertFalse(buffer.isFull());
    }
}
