package demo.hero;

import java.io.Serializable;

/**
 * 
 * @author suxin
 *
 */
public class Hero implements Serializable{
	/**
	 * ��ʾ��ǰ�����İ汾����������������ԣ���һ�����޸�����汾��
	 */
	private static final long serialVersionUID = 1L;
	public String name;
    public float hp;
    
    public Hero() {
    	  
    }
  
    // ����һ����ʼ��name�Ĺ��췽��
    public Hero(String name) {
  
        this.name = name;
    }
  
    // ��дtoString����
    public String toString() {
        return name;
    }
}
