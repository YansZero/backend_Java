package com.yanszero.ex04;

public class RecursionTest {

	public static void main(String[] args) {
		RecursionTest rTest= new RecursionTest();
		
		int sum=rTest.getSum(100);
		System.out.println("sum is "+ sum);
		int muti=rTest.getMuti(4);
		System.out.println("muti is "+ muti);
		
		int f=rTest.f(10);
		System.out.println("f is "+ f);
		
		int fibonnacci=rTest.fibonnacci(6);
		System.out.println("fibonnacci is "+ fibonnacci);
	}
	
	// 1+n SUM
	public int getSum(int n) {
		if (n<=1) {
			return n; 
		}
		else {
	        return n + getSum(n-1); 
		}
	}
	
	// 計算1~n計算的乘積 n!
	public int getMuti(int n) {
		if (n<=1) {
			return n; 
		}
		else {
	        return n * getMuti(n-1); 
		}
	}
	
	// 已知有一個數列 f(0)= 1,f(1)=4,f(n+2)=2*f(n+1) + f(n)
	// 其中N是大於0的整數 求f(10)=?
	public int f(int n) {
		if (n==0) {
			return 1;
		}
		else if (n==1) {
			return 4;
		}
		else {
			return 2*f(n-1) + f(n-2);
		}
	}
	
	// 費波納喜數列
	// 1 1 2 3 5 8 13 21
	public int fibonnacci(int n) {
		if (n==1 || n==2) {
			return 1;
		}
		else {
			return fibonnacci(n-2) + fibonnacci(n-1);
		}
	}
	
}
