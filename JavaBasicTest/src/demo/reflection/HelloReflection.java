package demo.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import demo.hero.Hero;

public class HelloReflection {
	public static void main(String[] args) {
        String className = "demo.hero.Hero";//ʹ�÷����ȡ�����
        try {
            Class pClass1=Class.forName(className);
            Class pClass2=Hero.class;
            Class pClass3=new Hero().getClass();
            //System.out.println(pClass1==pClass2);
            //System.out.println(pClass1==pClass3);
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        try {
            //ʹ�÷���ķ�ʽ��������
        	className = "demo.hero.Hero";
            //�����
            Class pClass=Class.forName(className);
            //������
            Constructor c= pClass.getConstructor();
            //ͨ��������ʵ����
            Hero h2= (Hero) c.newInstance();//ͨ������ȡ�õĹ������������
            h2.name="gareen";
            System.out.println(h2);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        //03������hero���е�setname������set��ô��һ����������String����
        Hero h = new Hero();
        
        try {
            // ��ȡ������ֽ���setName������������String�ķ���
            Method m = h.getClass().getMethod("setName", String.class);
            // ��h���󣬵����������
            m.invoke(h, "����");
            // ʹ�ô�ͳ�ķ�ʽ������getName����
            /*h.getName();*/
            Method m1 = h.getClass().getMethod("getName");
            System.out.println(m1.invoke(h));//ͨ������������getname����
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
}
}
