package com.example.android.feedback;

/**
 * Created by Sam on 18-Jul-17.
 */

public class Agency {
    String id;
    String name;

    public Agency(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
