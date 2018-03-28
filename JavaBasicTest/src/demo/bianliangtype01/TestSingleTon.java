package demo.bianliangtype01;

public class TestSingleTon {
	public static void main(String[] args) {
		// 通过new实例化会报错
		// SingelTon g = new SingelTon();

		// 只能通过getInstance得到对象，且由于构造函数被私有化，且传出的为以一个类属性，
		//所以只有一个实例

		SingelTon g1 = SingelTon.getInstance();
		SingelTon g2 = SingelTon.getInstance();
		SingelTon g3 = SingelTon.getInstance();

		// 都是同一个对象
		System.out.println(g1 == g2);
		System.out.println(g1 == g3);
	}
}
