package demo.bianliangtype01;

/**
 * @author suxin ����ģʽ :���ֻ࣬�ܱ�ʵ����һ�� ͨ��˽�л��乹�췽����ʹ���ⲿ�޷�ͨ��new �õ��µ�ʵ����
 * ʲô�ǵ���ģʽ��
1. ���췽��˽�л�
2. ��̬����ָ��ʵ��
3. public static�� getInstance���������صڶ����ľ�̬���� 
 */
public class SingelTon {
	// ˽�л����췽��ʹ�ø����޷����ⲿͨ��new ����ʵ����
	private SingelTon() {

	}

	// ����Ϊ��������ģʽ��������ζ��ᴴ��һ��ʵ��
	// ׼��һ�������ԣ�ָ��һ��ʵ�������� ��Ϊ�������ԣ�����ֻ��һ��
	private static SingelTon instance = new SingelTon();

	// public static �������ṩ�������߻�ȡ12�ж���Ķ���
	public static SingelTon getInstance() {
		return instance;
	}

	 /*����Ϊ��������ģʽ��ֻ�е��ⲿ�����getinstance����֮��Ż�ʵ���� 
	
	 * //׼��һ�������ԣ�����ָ��һ��ʵ�������󣬵�����ʱָ��null private static SingelTon instance;
	 * 
	 * //public static ����������ʵ������ public static SingelTon getInstance(){
	 * //��һ�η��ʵ�ʱ�򣬷���instanceû��ָ���κζ�����ʱʵ����һ������
	 *  if(null==instance){ instance = new
	 * SingelTon(); } //���� instanceָ��Ķ��� 
	 * return instance; }
*/	 
}
