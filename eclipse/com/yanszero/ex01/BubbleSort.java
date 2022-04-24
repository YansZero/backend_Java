package com.yanszero.ex01;

public class BubbleSort {
    public static void main(String[] args) {
		
    	int[] arr = new int[]{4,-1,0,5,8,2,10,32,35,23};
    	
    	//冒泡排序
    	for(int i=0;i<arr.length-1;i++ ) {
    		for(int j=0;j<arr.length-1-i ;j++) {
    			if (arr[j]>arr[j+1]) {
    				int tmp = arr[j];
    				arr[j]=arr[j+1];
    				arr[j+1]=tmp;
;    			}
    		}    		
    	}
    	
    	for(int i=0;i<arr.length-1;i++ ) {
    		System.out.print(arr[i]);
    	}
	}
}
