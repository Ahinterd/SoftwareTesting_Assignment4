package at.jku.swtesting;

import org.junit.jupiter.api.Test;

import static at.jku.swtesting.utils.TestUtils.*;
import static org.junit.jupiter.api.Assertions.*;

public class RingBufferStateTransitionTest {
    @Test
    void seq_1_queue_until_full_test(){
        var buffer = new RingBuffer<Character>(2);
        assertEmpty(buffer);
        buffer.enqueue('a');
        assertPartiallyFull(buffer);
        buffer.enqueue('b');
        assertFull(buffer);
    }
    @Test
    void seq_2_queue_dequeue_once_test() {
        var buffer = new RingBuffer<Character>(2);
        assertEmpty(buffer);
        buffer.enqueue('a');
        assertPartiallyFull(buffer);
        assertEquals('a', buffer.dequeue());
        assertEmpty(buffer);
    }

    @Test
    void seq_3_enqueue_dequeue_capacity_one_test() {
        var buffer = new RingBuffer<Character>(1);
        assertEmpty(buffer);
        buffer.enqueue('a');
        assertFull(buffer);
        assertEquals('a', buffer.dequeue());
        assertEmpty(buffer);
    }

    @Test
    void seq_4_enqueue_dequeue_test() {
        var buffer = new RingBuffer<Character>(3);
        assertEmpty(buffer);
        buffer.enqueue('a');
        assertPartiallyFull(buffer);
        buffer.enqueue('b');
        assertPartiallyFull(buffer);
        buffer.enqueue('c');
        assertFull(buffer);
        assertEquals('a', buffer.dequeue());
        assertPartiallyFull(buffer);
        buffer.enqueue('d');
        assertFull(buffer);
        assertEquals('b', buffer.dequeue());
        assertPartiallyFull(buffer);
        assertEquals('c', buffer.dequeue());
        assertPartiallyFull(buffer);
        assertEquals('d', buffer.dequeue());
        assertEmpty(buffer);
    }

    @Test
    void seq_5_enqueue_dequeue_test() {
        var buffer = new RingBuffer<Character>(2);
        assertEmpty(buffer);
        buffer.enqueue('a');
        assertPartiallyFull(buffer);
        buffer.enqueue('b');
        assertFull(buffer);
        buffer.enqueue('c');
        assertFull(buffer);
        buffer.enqueue('d');
        assertFull(buffer);
        assertEquals('c', buffer.dequeue());
        assertPartiallyFull(buffer);
        assertEquals('d', buffer.dequeue());
        assertEmpty(buffer);
    }

    @Test
    void seq_6_dequeue_empty_test() {
        var buffer = new RingBuffer<Character>(2);
        assertEmpty(buffer);
        buffer.enqueue('a');
        assertPartiallyFull(buffer);
        assertEquals('a', buffer.dequeue());
        assertEmpty(buffer);
        assertThrows(RuntimeException.class, buffer::dequeue);
        assertEmpty(buffer);
    }
}
