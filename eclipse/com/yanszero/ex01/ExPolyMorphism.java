package com.yanszero.ex01;

/*
 * 多型
 * 多型(PolyMorphism) 一詞是指生物學中一個生物或物種可以有不同的形式或階段。 
 * 在物件導向程式設計的概念中，利用父類別提供的方法呼叫，子類別可以有自己特有的行為。  
 * 將子類用父類的方式來看待
 * 然後顯示出多種子類的方法結果 稱為多型
 */
public class ExPolyMorphism {

	public static void main(String[] args) {
		
		Animal A1 = new cat();		
		Animal A2 = new dog();	
		
		showMove(A1);
		showMove(A2);
	
	}
	
	// 公用的方法 用父類的方法去調用 但顯示的是子類改寫的方法
	static void showMove(Animal animal) {
		animal.voice();
	}

}



class cat extends Animal {		
	@Override
	void voice() {
		System.out.println("cat is meow meow!");
	}
}

class dog extends Animal {		
	@Override
	void voice() {
		System.out.println("dog is wow wow!");
	}
}

abstract class Animal {
	private int foot;
	private int head;
	
	abstract void voice() ;
	
	public int getFoot() {
		return foot;
	}
	public void setFoot(int foot) {
		this.foot = foot;
	}
	public int getHead() {
		return head;
	}
	public void setHead(int head) {
		this.head = head;
	}
	
}
