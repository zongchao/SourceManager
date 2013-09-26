package com.neko.bean;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestSpring
{
    
    private String name;

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }
    
    public void say()
    {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        TestSpring ts = (TestSpring)context.getBean("TestSpring");
        System.out.println(ts.name + "say:" + "HelloWorld");
    }
}
