package ru.itis.lab.listeners;

import ru.itis.MyContext;
import ru.itis.MyReflectionContext;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener

public class ApplicationContextServletListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        MyContext context = new MyReflectionContext("ru.itis.lab");
        ServletContext servletContext = sce.getServletContext();
        servletContext.setAttribute("context", context);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
