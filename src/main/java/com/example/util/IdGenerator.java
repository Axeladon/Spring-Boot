package com.example.util;

import java.util.concurrent.atomic.AtomicLong;

public class IdGenerator {
    private final AtomicLong nextId = new AtomicLong(1);

    public long getNextId() {
        return nextId.getAndIncrement();
    }
}
