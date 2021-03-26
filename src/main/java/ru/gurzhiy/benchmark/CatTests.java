package ru.gurzhiy.benchmark;

import ru.gurzhiy.annotations.AfterTestSuite;
import ru.gurzhiy.annotations.BeforeSuite;
import ru.gurzhiy.annotations.Test;

import static org.junit.Assert.assertEquals;


/**
 * 1. Создать класс, который может выполнять «тесты», в качестве тестов выступают классы с наборами методов
 * с аннотациями @Test. Для этого у него должен быть статический метод start(), которому в качестве параметра
 * передается или объект типа Class, или имя класса. Из «класса-теста» вначале должен быть запущен метод с аннотацией
 * @BeforeSuite, если такой имеется, далее запущены методы с аннотациями @Test, а по завершению всех тестов – метод с
 * аннотацией @AfterSuite. К каждому тесту необходимо также добавить приоритеты (int числа от 1 до 10), в соответствии
 * с которыми будет выбираться порядок их выполнения, если приоритет одинаковый, то порядок не имеет значения. Методы
 * с аннотациями @BeforeSuite и @AfterSuite должны присутствовать в единственном экземпляре, иначе необходимо бросить
 * RuntimeException при запуске «тестирования».
 * Это домашнее задание никак не связано с темой тестирования через JUnit и использованием этой библиотеки,
 * то есть проект пишется с нуля.
 */
public class CatTests {

    Cat orange = null;
    @BeforeSuite
    public void init(){
        System.out.println("Before suite init");
        orange = new Cat("Рыжик", "Мяяяяу");
        System.out.println(orange.getName() + " "+orange.getVoice());
    }




    @Test(3)
    public void testCatEquality1(){
        System.out.println("тест 3");
        Cat c1 = new Cat("Барсик");
        if (c1.equals(new Cat("Барсик"))){
            System.out.println("Котики равны");
        }else{
            System.out.println("Котики не равны");
        }

    }

    @Test(2)
    public void testCatEquality2(){
        System.out.println("тест 2");
        Cat c1 = new Cat("Барсик");
        if (c1.equals(new Cat("Рыжик"))){
            System.out.println("Котики равны");
        }else{
            System.out.println("Котики не равны");
        }

    }

    @Test(1)
    public void testCatEquality3() {
        System.out.println("тест 1");
        Cat c1 = new Cat("Барсик");
        if (c1.equals(null)) {
            System.out.println("Котики равны");
        }else{
            System.out.println("Котики не равны");
        }

    }



    @AfterTestSuite
    public void deleteAllStuff(){
        orange = null;
        System.out.println("Cleaning test context");
    }
}
