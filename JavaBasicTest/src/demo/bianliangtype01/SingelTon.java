package demo.bianliangtype01;

/**
 * @author suxin 单例模式 :该类，只能被实例化一次 通过私有化其构造方法，使得外部无法通过new 得到新的实例。
 * 什么是单例模式？
1. 构造方法私有化
2. 静态属性指向实例
3. public static的 getInstance方法，返回第二步的静态属性 
 */
public class SingelTon {
	// 私有化构造方法使得该类无法在外部通过new 进行实例化
	private SingelTon() {

	}

	// 以下为饿汉单例模式，无论如何都会创建一个实例
	// 准备一个类属性，指向一个实例化对象。 因为是类属性，所以只有一个
	private static SingelTon instance = new SingelTon();

	// public static 方法，提供给调用者获取12行定义的对象
	public static SingelTon getInstance() {
		return instance;
	}

	 /*以下为懒汉单例模式，只有当外部类调用getinstance方法之后才会实例化 
	
	 * //准备一个类属性，用于指向一个实例化对象，但是暂时指向null private static SingelTon instance;
	 * 
	 * //public static 方法，返回实例对象 public static SingelTon getInstance(){
	 * //第一次访问的时候，发现instance没有指向任何对象，这时实例化一个对象
	 *  if(null==instance){ instance = new
	 * SingelTon(); } //返回 instance指向的对象 
	 * return instance; }
*/	 
}
