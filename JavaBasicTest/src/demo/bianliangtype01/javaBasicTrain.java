package demo.bianliangtype01;

/**
 * @author suxin
 *
 *         1. 属性通常使用private封装起来 2. 方法一般使用public用于被调用 3. 会被子类继承的方法，通常使用protected
 *         4. package用的不多，一般新手会用package,因为还不知道有修饰符这个东西
 */
public class javaBasicTrain {
	public static void main(String[] args) {
		// 01求离黄金分割点最近的两个树
		// goldDevide();
		// 02找到水仙花树
		shuiXian();

	}

	/**
	 * 找到离黄金分割点最近的两个树
	 */
	public static void goldDevide() {
		double x = 0.618;// 最终结果比对
		double temp = 2;// 最小差值暂存
		double q = 0, p = 0;// 存储差值最小的除数和被除数
		for (double i = 1; i < 21; i++) {
			for (double j = 1; j < i; j++) {
				if (Math.abs(x - (j / i)) < temp) {
					temp = Math.abs(x - (j / i));
					q = j;
					p = i;
				}
			}
		}
		System.out.println("01距离黄金分割点最近的两个数是：" + q + "," + p);
	}

	//
	/**
	 * 水仙花树，一定三位，三位的立方加起来等于自己
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
