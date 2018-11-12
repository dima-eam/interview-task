package com.epages.interview.domain;

import javax.annotation.Nullable;

/**
 * Maps boolean values to some string values.
 */
public enum Event {
    ON_SALE("ON SALE");

    private final String name;

    Event(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Nullable
    public static Event fromBoolean(boolean eventFlag) {
        return eventFlag ? ON_SALE : null;
    }

}
