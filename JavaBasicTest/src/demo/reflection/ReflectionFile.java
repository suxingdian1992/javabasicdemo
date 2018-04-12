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
 * ͨ���ļ���ȡ����������
 * @author suxin
 *
 */
public class ReflectionFile {
	public static void main(String[] args) throws NoSuchMethodException, SecurityException, ClassNotFoundException, FileNotFoundException, IOException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		//��spring.txt�л�ȡ�����ƺͷ�������
	    File springConfigFile = new File("C:\\Users\\suxin\\git\\javabasic\\JavaBasicTest\\src\\spring.txt");
	    Properties springConfig= new Properties();
	    springConfig.load(new FileInputStream(springConfigFile));
	    //���ļ�����Ҫ���ض���ʽ������һ������
	    String className = (String) springConfig.get("class");
	    String methodName = (String) springConfig.get("method");
	     
	    //���������ƻ�ȡ�����
	    Class clazz = Class.forName(className);
	    //���ݷ������ƣ���ȡ��������
	    Method m = clazz.getMethod(methodName);
	    //��ȡ������
	    Constructor c = clazz.getConstructor();
	    //���ݹ�������ʵ����������
	    Object service = c.newInstance();
	    //���ö����ָ������
	    m.invoke(service);
	}
	
}
