package ru.itis;

import h.Zxcv;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;

public class Main {
    public static void main(String[] args) {
        MyContext context = new MyReflectionContext("h");
        Object component = context.getComponent(Zxcv.class.getName(), "/first");
        System.out.println();
    }
}
