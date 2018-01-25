package com.summer.context.xml;

import com.summer.context.entity.Person;
import junit.framework.TestCase;
import org.junit.Test;

public class ClassPathXmlApplicationContextTest extends TestCase {

    @Test
    public void testClassPathXml () {

        String fileName = "test-beans.xml";
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(fileName);
        Person person = (Person) context.getBean("person");
        System.out.println(person.toString());
    }
}
