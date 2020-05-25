package com.factory;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 一个创建Bean对象的工厂
 * Bean:可重用组件
 * JavaBean:Java语言编写的可重用组件
 * JavaBean != 实体类 （JavaBean范围远大于实体类，业务层、持久层都可以是可重用组件）
 *
 * 用于创建service和dao对象。
 * 第一个：需要一个配置文件来配置我们的servic和dao
 *        配置的内容：唯一标识-全限定类名 （即key-value）
 * 第二个：通过读取配置文件中的内容，反射创建对象
 *
 * 配置文件类型：
 *      可以是xml 也可以是properties
 */
public class BeanFactory {
    // 定义一个properti对象
    private static Properties props;

    // 定义一个Map 用于存放我们要创建的对象  将其称之为容器
    private static Map<String,Object>beans;


    // 使用静态代码块为Properti对象赋值
    static{
        try {
            // 实例化对象
            props = new Properties();
            // 获取properties文件的流对象 通过类加载器的方式来获取
            InputStream in = BeanFactory.class.getClassLoader().getResourceAsStream("bean.properties");
            props.load(in);
            // 实例化容器
            beans = new HashMap<String, Object>();
            // 取出配置文件中所有的key
            Enumeration  keys = props.keys();
            // 遍历枚举
            while (keys.hasMoreElements()){
                // 取出每个key
                String key = keys.nextElement().toString();
                // 根据key获取value
                String beanPath = props.getProperty(key);
                // 反射创建对象
                Class<?> class1  = Class.forName(beanPath);
                Object value = class1.getDeclaredConstructor().newInstance();
                // 把key和value存入容器中
                beans.put(key,value);

            }
        }catch (Exception e){
            throw new ExceptionInInitializerError("初始化properties失败");
        }
    }

    public static Object getBean(String beanName){
        return beans.get(beanName);
    }

    /**
     * 根据Bean的名称获取bean对象
     * @param
     * @return
     */
    /**
    public static Object getBean(String beanName){
        Object bean = null;
        //String string="abc";
        //Class<?> class1=string.getClass();
        //Class<?> class1= String.class;
        try {
            String beanPath = props.getProperty(beanName);
           // System.out.println(beanPath);
            Class<?> class1 = Class.forName(beanPath);
            // 每次都会调用默认构造函数创建新对象
            // 许多时候可能只需要一个对象就够了 无需重复创建
            // 引入单例
            bean = class1.getDeclaredConstructor().newInstance();

        }catch (Exception e){
            e.printStackTrace();
        }
        return  bean;

    }*/
}
