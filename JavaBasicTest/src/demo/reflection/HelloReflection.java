package demo.reflection;

import demo.hero.Hero;

public class HelloReflection {
	public static void main(String[] args) {
        String className = "demo.hero.Hero";//使用反射获取类对象
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
}
}
