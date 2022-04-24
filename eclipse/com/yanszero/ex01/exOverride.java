package com.yanszero.ex01;

public class exOverride {
	public static void main(String[] args) {
		
	}
}

class Man extends People {
	
}

class People {
	private int age;
	private int sexy;
	private String name;
	private String type;
	
	public People() {
		// TODO Auto-generated constructor stub
	}

	// overload 多載
	// 同個方法名 但參數個數不同 參數型別不一樣  
	public People(int age, int sexy, String name, String type) {
		super();
		this.age = age;
		this.sexy = sexy;
		this.name = name;
		this.type = type;
	}

	public People(int age, int sexy, String name) {
		super();
		this.age = age;
		this.sexy = sexy;
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getSexy() {
		return sexy;
	}

	public void setSexy(int sexy) {
		this.sexy = sexy;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	

		 
	
}


