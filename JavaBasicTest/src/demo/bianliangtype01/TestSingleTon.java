package demo.bianliangtype01;

public class TestSingleTon {
	public static void main(String[] args) {
		// ͨ��newʵ�����ᱨ��
		// SingelTon g = new SingelTon();

		// ֻ��ͨ��getInstance�õ����������ڹ��캯����˽�л����Ҵ�����Ϊ��һ�������ԣ�
		//����ֻ��һ��ʵ��

		SingelTon g1 = SingelTon.getInstance();
		SingelTon g2 = SingelTon.getInstance();
		SingelTon g3 = SingelTon.getInstance();

		// ����ͬһ������
		System.out.println(g1 == g2);
		System.out.println(g1 == g3);
	}
}
