package com.yanszero.ex03;


//定義一個PassObject 在類中定義一個方法printAreas()
//方法宣告如下:printAreas(Circle c ,int time)
//在printAreas 方法中輸出1到time之間每個整數半徑以及對應的面積
//EX: time=5, 印出1,2,3,4,5 以及對應的面積
public class PassObject {
	
//	public static void main(String[] args) {
//		PassObject testObject = new PassObject();
//		Circle c =new Circle();
//		testObject.printAreas(c, 5);
//		System.out.println("now radius is "+ c.radius);
//	}

	public void printAreas(Circle c ,int time) {
	  
		System.out.println("Radius\t\tArea");
		for (int i=1;i<=time;i++) {
			// 設定半徑
			c.radius = i;
			System.out.println(c.radius+"\t\t"+ c.findArea());
		}
		
		c.radius = time+1;
  }	
}
