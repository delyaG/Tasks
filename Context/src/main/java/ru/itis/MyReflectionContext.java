package ru.itis;

import org.reflections.Reflections;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MyReflectionContext implements MyContext {

    private String packageName;
    private Set<? extends Class<?>> needClassesImplComponent;
    private Map<Class<?>, Object> instancesImplComponent;
    private Set<? extends Class<?>> needClassesImplController;
    private Map<Class<?>, Object> instancesImplController;

    private Reflections reflections;

    public MyReflectionContext(String packageName) {
        this.packageName = packageName;
        this.reflections = new Reflections(packageName);
        this.needClassesImplComponent = reflections.getSubTypesOf(Component.class);
        this.instancesImplComponent = getInstancesImplComponent();
        this.needClassesImplController = reflections.getSubTypesOf(Controller.class);
        this.instancesImplController = getInstances(needClassesImplController);
    }

    private Map<Class<?>, Object> getInstancesImplComponent() {
        Map<Class<?>, Object> instances = getInitialInstances(this.needClassesImplComponent);
        Set<? extends Class<?>> collect = clone(this.needClassesImplComponent);

        while (!collect.isEmpty()) {
            Class<?> clazz = collect.iterator().next();
            Field[] fields = clazz.getDeclaredFields();

            for (Field thisField : fields) {
                boolean ac = thisField.isAccessible();
                thisField.setAccessible(true);
                Class<?> fieldClass = getFieldClass(thisField.getType());
                if (instances.containsKey(fieldClass)) {
                    try {
                        thisField.set(instances.get(clazz), instances.get(fieldClass));
                    } catch (IllegalAccessException e) {
                        throw new IllegalArgumentException(e);
                    }
                }
                thisField.setAccessible(ac);
            }
            collect.remove(clazz);
        }
        return instances;
    }

    private Map<Class<?>, Object> getInstances(Set<? extends Class<?>> needClasses) {
        Map<Class<?>, Object> instances = getInitialInstances(needClasses);
        Set<? extends Class<?>> collect = clone(needClasses);

        while (!collect.isEmpty()) {
            Class<?> clazz = collect.iterator().next();
            Field[] fields = clazz.getDeclaredFields();

            for (Field thisField : fields) {
                boolean ac = thisField.isAccessible();
                thisField.setAccessible(true);
                Class<?> fieldClass = getFieldClass(thisField.getType());
                if (this.instancesImplComponent.containsKey(fieldClass)) {
                    try {
                        thisField.set(instances.get(clazz), this.instancesImplComponent.get(fieldClass));
                    } catch (IllegalAccessException e) {
                        throw new IllegalArgumentException(e);
                    }
                }
                thisField.setAccessible(ac);
            }
            collect.remove(clazz);
        }
        return instances;
    }

    private Map<Class<?>, Object> getInitialInstances(Set<? extends Class<?>> needClasses) {
        Map<Class<?>, Object> needInstances = new HashMap<>();
        Set<? extends Class<?>> classes = clone(needClasses);

        while (!classes.isEmpty()) {
            Class<?> clazz = classes.iterator().next();
            Constructor<?>[] constructors = clazz.getConstructors();

            for (Constructor<?> thisCons : constructors) {
                if (thisCons.getParameterCount() == 0) {
                    try {
                        needInstances.put(clazz, thisCons.newInstance());
                    } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
                        throw new IllegalArgumentException(e);
                    }
                }
            }
            classes.remove(clazz);
        }
        return needInstances;
    }

    private <T> Set<T> clone(Set<T> original) {
        return new HashSet<>(original);
    }

    private Class<?> getFieldClass(Class<?> fieldClass) {
        Reflections reflections = this.reflections;
        Class<?> fieldClassImpl = null;
        if (!isClass(fieldClass)) {
            Set<? extends Class<?>> classes = reflections.getSubTypesOf(fieldClass);
            for (Class implClass: classes) {
                fieldClassImpl = implClass;
            }
        } else  {
            fieldClassImpl = fieldClass;
        }
        return fieldClassImpl;
    }

    private boolean isClass(Class tClass) {
        String[] parts = tClass.toString().split(" ");
        return parts[0].equals("class");
    }

    public <T> T getComponent(String name) {
        T component = null;
        try {
            component = (T) instancesImplComponent.get(Class.forName(name));
        } catch (ClassNotFoundException e) {
            throw new IllegalArgumentException(e);
        }
        return component;
    }

    @Override
    public <T> T getComponent(String name, String url) {
        T component = null;

        Class<?> clazz = null;
        try {
            clazz = Class.forName(name);
        } catch (ClassNotFoundException e) {
            throw new IllegalArgumentException(e);
        }

        if (instancesImplComponent.containsKey(clazz)) {
            return getComponent(name);
        }

        if (getMapping(clazz).equals(url) && instancesImplController.containsKey(clazz)) {
            return (T) instancesImplController.get(clazz);

        }
        return null;
    }

    private <T> String getMapping(Class<? extends T> tClass) {
        String mapping = null;

        Annotation[] annotations = tClass.getDeclaredAnnotations();
        for (Annotation annotation : annotations) {
            String[] fullAnnotationName = annotation.annotationType().getName().split("\\.");
            if (fullAnnotationName[fullAnnotationName.length - 1].equals("Mapping")) {
                Class<? extends Annotation> annotationType = annotation.annotationType();
                try {
                    mapping = (String) annotationType.getDeclaredMethod("value").invoke(annotation, (Object[])null);
                } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                    throw new IllegalArgumentException(e);
                }
            }
        }
        return mapping;
    }
}
