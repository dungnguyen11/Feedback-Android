package com.example.android.feedback;

/**
 * Created by Sam on 16-Jul-17.
 */

public class Feedback {
    String id;
    User user;
    String content;
    Agency agency;

    public Feedback(Agency seletedAgency, String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return content;
    }
}
