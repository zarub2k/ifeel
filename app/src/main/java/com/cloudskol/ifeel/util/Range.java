package com.cloudskol.ifeel.util;

/**
 * Created by tham on 03-11-2016.
 */

public class Range<T> {
    private T start;
    private T end;

    public Range(T start, T end) {
        this.start = start;
        this.end = end;
    }

    public T getStart() {
        return start;
    }

    public T getEnd() {
        return end;
    }
}
