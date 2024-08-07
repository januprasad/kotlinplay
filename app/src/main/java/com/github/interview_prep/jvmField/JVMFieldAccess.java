package com.github.interview_prep.jvmField;
import java.util.Date;

public class JVMFieldAccess {
    public static void main(String[] args) {
        Session session = new Session("Session", new Date());
        String name = session.name;

        AppUtils.INSTANCE.install2();
    }
}

