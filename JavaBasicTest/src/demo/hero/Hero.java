package demo.hero;

import java.io.Serializable;

/**
 * 
 * @author suxin
 *
 */
public class Hero implements Serializable{
	/**
	 * 表示当前这个类的版本，如果新设置了属性，就一个该修改这个版本号
	 */
	private static final long serialVersionUID = 1L;
	public String name;
    public float hp;
    
    public Hero() {
    	  
    }
  
    // 增加一个初始化name的构造方法
    public Hero(String name) {
  
        this.name = name;
    }
  
    // 重写toString方法
    public String toString() {
        return name;
    }
}
