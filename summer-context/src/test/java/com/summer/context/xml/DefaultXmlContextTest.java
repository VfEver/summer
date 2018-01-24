package com.summer.context.xml;

import com.summer.context.entity.Person;
import junit.framework.TestCase;
import org.junit.Test;

import java.io.File;
import java.lang.reflect.AnnotatedType;
import java.lang.reflect.Field;
import java.net.URL;

/**
 * defaultXmlContext class test
 */

public class DefaultXmlContextTest extends TestCase{


    @Test
    public void testOnFresh() {

        String filePath = "src/test/resources/test-beans.xml";
        DefaultXmlContext context = new DefaultXmlContext(filePath);
        System.out.println(context.containsBean("person"));;
        Person person = (Person) context.getBean("person");
        System.out.println(person.toString());

    }

    @Test
    public void testPerson () throws Exception {

        Class clazz = Class.forName("com.summer.context.entity.Person");
        Person person = (Person) clazz.newInstance();
        Field fieldName = clazz.getDeclaredField("name");
        fieldName.setAccessible(true);
        fieldName.set(person, "zhaoyansheng");
        System.out.println(person.toString());

        Field ageField = clazz.getDeclaredField("age");
        ageField.setAccessible(true);

        int age = 24;
        Object obj = (Object) age;
        ageField.set(person, age);
        AnnotatedType type1 = ageField.getAnnotatedType();
        System.out.println(type1.getType().getTypeName());
        System.out.println(person.toString());
    }

    @Test
    public void testRef () {

        String filePath = "test-beans.xml";
        DefaultXmlContext context = new DefaultXmlContext(filePath);
        Person person = (Person) context.getBean("person");
        System.out.println(person.toString());
    }

    @Test
    public void testClassPath () {

        System.out.println(Thread.currentThread().getContextClassLoader().getResource(""));
        String filePath = "src/test/resources/test-beans.xml";
        File file = new File(filePath);
        System.out.println(file.getAbsolutePath());

        URL url = this.getClass().getClassLoader().getResource("test-beans.xml");
        url.getPath();
        System.out.println(url.getPath());
        System.out.println(this.getClass().getClassLoader().getResource("test-beans.xml"));
    }
}
