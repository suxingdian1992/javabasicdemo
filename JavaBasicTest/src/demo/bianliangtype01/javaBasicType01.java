package demo.bianliangtype01;

public class javaBasicType01 {
	public static void main(String[] args) {
		/*java�Ļ��������а��֣��ֱ�Ϊ�������Σ�һ���ַ��ͣ����ָ����ͣ�һ�ֲ�����*/
		//01 ���� ���ֶ����ж�Ӧ�ķ�Χ��������ΰ��ֵ�ͻᱨ��
		byte b = 1;
		short s = 200;
		int i = 300;
		long l = 400;
		
		/*02 �ַ���*/
		char c = 'a';
		
		/*03 ������*/
		float f = 532.123f;
		double d = 532.123;
		
		/*04 ������*/
		boolean e = true;
		
		/*05 String���� ע�⣬������ǻ������ͣ������Immutable����*/
		String string = "abc";
		
		//�������Ǵ���һ��������java���ǲ���new������ֵ�����Ǹ������������͸�ֵȴ���ǲ����������Ϊ
		//����������������java�еģ�ֱ�Ӹ�ֵ���ɣ����ַ�ʽ��������ֵ
		long val = 26L;//��дL��β��ʾ����ֵΪ������
		int decVal = 26;//����Ĭ�Ͼ���int��
		int hexVal = 0x1a;//�˴���ʾ16����
		int oxVal = 032;//�˴���ʾ�˽���,0��ͷ����
		int binVal = 0b11010;//2����
		System.out.println(oxVal);
	}
}
