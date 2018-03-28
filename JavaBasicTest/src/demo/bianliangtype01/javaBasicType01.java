package demo.bianliangtype01;

public class javaBasicType01 {
	public static void main(String[] args) {
		/*java的基础类型有八种，分别为四种整形，一种字符型，两种浮点型，一种布尔型*/
		//01 整形 四种对象有对应的范围，超出范伟赋值就会报错
		byte b = 1;
		short s = 200;
		int i = 300;
		long l = 400;
		
		/*02 字符型*/
		char c = 'a';
		
		/*03 浮点型*/
		float f = 532.123f;
		double d = 532.123;
		
		/*04 布尔型*/
		boolean e = true;
		
		/*05 String类型 注意，这个不是基本类型，这个是Immutable类型*/
		String string = "abc";
		
		//正常我们创建一个对象在java中是采用new给他赋值，但是给基本变量类型赋值却不是采用这个，因为
		//基础变量是内置在java中的，直接赋值即可，这种方式叫做字面值
		long val = 26L;//大写L结尾表示字面值为长整型
		int decVal = 26;//这里默认就是int型
		int hexVal = 0x1a;//此处表示16进制
		int oxVal = 032;//此处表示八进制,0开头即可
		int binVal = 0b11010;//2进制
		System.out.println(oxVal);
	}
}
