package demo.hero;

public class Item extends LOL implements Comparable<Item>{
    public String name;
    public int price;
     
    public Item(){
         
    }
     
    //�ṩһ����ʼ��name�Ĺ��췽��
    public Item(String name,int price){
        this.name = name;
        this.price = price;
    }
     
    public void effect(){
        System.out.println("��Ʒʹ�ú󣬿�����Ч��");
    }

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return name+"="+price;
	}

	@Override
	public int compareTo(Item o) {//ʵ������Ľӿ�
		// Ĭ�ϻᰴ�յ�ǰ��ѡ��������Ŀ��С�������򣬾��ǰ����������У������������ֵ����������Ե���˳��
		if(o.price>this.price) {
			return 1;
		}else if(o.price<this.price){
			return -1;
		}else {
			return 0;
		}
	}
     
    
}
