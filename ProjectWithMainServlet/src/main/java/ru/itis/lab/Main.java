package ru.itis.lab;

import ru.itis.lab.servlets.MainServlet;

public class Main {
    public static void main(String[] args) {
        String[] s = "/signUp".split("/");

//        try {
//            System.out.println(Class.forName("ru.itis.lab.servlets.MainServlet"));
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }

        String st = "mainServlet";
        String s1 = st.substring(0, 1).toUpperCase() + st.substring(1);
        System.out.println(s1);


    }
}
