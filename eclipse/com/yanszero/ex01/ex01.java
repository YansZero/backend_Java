package com.yanszero.ex01;

//定義一個INT型的陣列 讓數組每個元素去除以首位元素，
//將結果作為該位置上的新的結果
//EX :int[] a = new int[]{2,3,3,34,56,77,432}
//EX: RESULT = [1,1,1,17,28,36,216]
public class ex01 {
	 public static void main(String[] args) {

	        int[] tmpArr= new int[]{2,3,3,34,56,77,432};
	        count(tmpArr);

	        for (int i = 0; i < tmpArr.length; i++) {
	            System.out.println("tmpArr["+ i +"]="+ tmpArr[i]);
	        }
	    }

	    public static void count(int[] arr) {
	        int temp = arr[0];
	        for (int i=0;i<arr.length;i++){
	            arr[i]=arr[i]/temp;
	        }
	    }
}
