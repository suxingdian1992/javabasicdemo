package demo.hero;

public class Item extends LOL implements Comparable<Item>{
    public String name;
    public int price;
     
    public Item(){
         
    }
     
    //提供一个初始化name的构造方法
    public Item(String name,int price){
        this.name = name;
        this.price = price;
    }
     
    public void effect(){
        System.out.println("物品使用后，可以有效果");
    }

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return name+"="+price;
	}

	@Override
	public int compareTo(Item o) {//实现排序的接口
		// 默认会按照当前所选的排序项目从小到大排序，就是按照正序排列，如果调换返回值的正负则可以调换顺序
		if(o.price>this.price) {
			return 1;
		}else if(o.price<this.price){
			return -1;
		}else {
			return 0;
		}
	}
     
    
}
