package com.yanszero.ex03;


//定義一個Circle類 包含一個double類型的radius 代表圓的半徑
//一個findArea()方法回傳圓的面積
//
public class Circle {
    double radius;

    public Circle () {
		
	}
    
	public Circle(double radius) {
		super();
		this.radius = radius;
	}
    
	
	public double getRadius() {
		return radius;
	}


	public void setRadius(double radius) {
		this.radius = radius;
	}


	public double findArea() {
		return this.radius * this.radius * Math.PI;
	}
}
