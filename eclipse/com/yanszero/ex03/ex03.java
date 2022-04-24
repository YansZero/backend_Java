package com.yanszero.ex03;

//定義一個Circle類 包含一個double類型的radius 代表圓的半徑
//一個findArea()方法回傳圓的面積
//
//定義一個PassObject 在類中定義一個方法printAreas()
//方法宣告如下:printAreas(Circle c ,int time)
//在printAreas 方法中輸出1到time之間每個整數半徑以及對應的面積
//EX: time=5, 印出1,2,3,4,5 以及對應的面積
public class ex03 {
	public static void main(String[] args) {
		PassObject testObject = new PassObject();
		Circle c =new Circle();
		testObject.printAreas(c, 5);
		System.out.println("now radius is "+ c.radius);

	}
}
