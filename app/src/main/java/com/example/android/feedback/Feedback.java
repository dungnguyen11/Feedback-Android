package com.example.android.feedback;

/**
 * Created by Sam on 16-Jul-17.
 */

public class Feedback {
    String id;
    User user;
    String content;
    String agencyId;
    String agencyName;

    public Feedback(String agencyName, String content) {
        this.content = content;
        this.agencyName = agencyName;
    }

}
