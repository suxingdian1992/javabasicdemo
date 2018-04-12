package demo.reflection;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Properties;

/**
 * 通过文件读取类名方法名
 * @author suxin
 *
 */
public class ReflectionFile {
	public static void main(String[] args) throws NoSuchMethodException, SecurityException, ClassNotFoundException, FileNotFoundException, IOException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		//从spring.txt中获取类名称和方法名称
	    File springConfigFile = new File("C:\\Users\\suxin\\git\\javabasic\\JavaBasicTest\\src\\spring.txt");
	    Properties springConfig= new Properties();
	    springConfig.load(new FileInputStream(springConfigFile));
	    //该文件中需要用特定格式保存这一对属性
	    String className = (String) springConfig.get("class");
	    String methodName = (String) springConfig.get("method");
	     
	    //根据类名称获取类对象
	    Class clazz = Class.forName(className);
	    //根据方法名称，获取方法对象
	    Method m = clazz.getMethod(methodName);
	    //获取构造器
	    Constructor c = clazz.getConstructor();
	    //根据构造器，实例化出对象
	    Object service = c.newInstance();
	    //调用对象的指定方法
	    m.invoke(service);
	}
	
}
