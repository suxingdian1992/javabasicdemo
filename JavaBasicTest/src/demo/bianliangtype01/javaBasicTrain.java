package demo.bianliangtype01;

/**
 * @author suxin
 *
 *         1. ����ͨ��ʹ��private��װ���� 2. ����һ��ʹ��public���ڱ����� 3. �ᱻ����̳еķ�����ͨ��ʹ��protected
 *         4. package�õĲ��࣬һ�����ֻ���package,��Ϊ����֪�������η��������
 */
public class javaBasicTrain {
	public static void main(String[] args) {
		// 01����ƽ�ָ�������������
		// goldDevide();
		// 02�ҵ�ˮ�ɻ���
		shuiXian();

	}

	/**
	 * �ҵ���ƽ�ָ�������������
	 */
	public static void goldDevide() {
		double x = 0.618;// ���ս���ȶ�
		double temp = 2;// ��С��ֵ�ݴ�
		double q = 0, p = 0;// �洢��ֵ��С�ĳ����ͱ�����
		for (double i = 1; i < 21; i++) {
			for (double j = 1; j < i; j++) {
				if (Math.abs(x - (j / i)) < temp) {
					temp = Math.abs(x - (j / i));
					q = j;
					p = i;
				}
			}
		}
		System.out.println("01����ƽ�ָ��������������ǣ�" + q + "," + p);
	}

	//
	/**
	 * ˮ�ɻ�����һ����λ����λ�����������������Լ�
	 */
	private static void shuiXian() {
		String temp = "";
		String result = "";
		for (int i = 100; i < 1000; i++) {
			temp = String.valueOf(i);
			if (i == threeSeq(temp)) {
				result += String.valueOf(i) + ",";
			}
		}
		System.out.println(result);
	}

	public static int threeSeq(String x) {
		int a = (int) Math.pow(Integer.parseInt(x.substring(0, 1)), 3);
		int b = (int) Math.pow(Integer.parseInt(x.substring(1, 2)), 3);
		int c = (int) Math.pow(Integer.parseInt(x.substring(2, 3)), 3);
		return a + b + c;
	}
}
