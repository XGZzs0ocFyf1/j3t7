package ru.gurzhiy.handlers;

import ru.gurzhiy.annotations.AfterTestSuite;
import ru.gurzhiy.annotations.BeforeSuite;
import ru.gurzhiy.annotations.Test;
import ru.gurzhiy.benchmark.CatTests;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


/**
 * Custom Test runner like JUnit classes. Handles set of annotations: {@link ru.gurzhiy.annotations.Test},
 * {@link ru.gurzhiy.annotations.AfterTestSuite}, {@link ru.gurzhiy.annotations.BeforeSuite}
 */
public class TestRunner {

    private static Class<?> linkToClazz = null;

    public static void start(Class<?> link) {
        linkToClazz = link;
        doStuff();
    }

    public static void doStuff(){
        beforeHandler();
        handleTestAnnotation();
        afterHandler();
    }


    //@BeforeSuite, если такой имеется, далее запущены методы с аннотациями @Test, а по завершению всех тестов –
    // метод с аннотацией @AfterSuite. К каждому тесту необходимо также добавить приоритеты (int числа от 1 до 10)
    private static void beforeHandler() {
        int counter = 0;
        Method[] methods = linkToClazz.getDeclaredMethods();
        for (Method m : methods) {
            if (m.getAnnotation(BeforeSuite.class) != null) {
                if (counter > 0){
                    throw new RuntimeException("There is two @BeforeSuite annotations in the class "+linkToClazz.getName() );
                }
                counter++;
                methodInvoker(m);
            }
        }
    }

    private static void afterHandler() {
        int counter = 0;
        Method[] methods = linkToClazz.getDeclaredMethods();
        for (Method m : methods) {
            if (m.getAnnotation(AfterTestSuite.class) != null) {
                if (counter > 0){
                    throw new RuntimeException("There is two @AfterTestSuite annotations in the class "+linkToClazz.getName() );
                }
                counter++;
                methodInvoker(m);
            }
        }
    }


    /**
     * create list of @{@link  ru.gurzhiy.annotations.Test}  annotated methods,  arranges
     * list by value of test annotation in ascending order and execute tests in this order.
     */

    public static void handleTestAnnotation() {
        Method[] methods = linkToClazz.getDeclaredMethods();

        List<Method> orderedMethods = Arrays.stream(methods).filter(m -> m.getAnnotation(Test.class) != null)
                .sorted((m1, m2) -> customComparator(m1.getAnnotation(Test.class).value(),
                        m2.getAnnotation(Test.class).value())).collect(Collectors.toList());

        orderedMethods.forEach(TestRunner::methodInvoker);


    }

    private static void methodInvoker(Method m) {
        try {
            m.invoke(linkToClazz.newInstance());
        } catch (IllegalAccessException | InvocationTargetException | InstantiationException e) {
            e.printStackTrace();
        }
    }

    /**
     * Helper method for {@link ru.gurzhiy.handlers.TestRunner}.{@link #handleTestAnnotation()}.
     * Compares its two arguments for order.
     *
     * @param a first parameter
     * @param b second parameter
     * @return Returns a negative integer, zero, or a positive integer as the first argument is less than,
     * //    equal to, or greater than the second.
     */

    private static int customComparator(int a, int b) {
        if (a > b) {
            return 1;
        } else if (a < b) {
            return -1;
        }
        return 0;
    }


    public static void main(String[] args) {


        TestRunner runner = new TestRunner();
        runner.start(CatTests.class);


    }

}
