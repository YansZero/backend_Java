package com.yanszero.sparsearray;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.OutputStreamWriter;

public class SparesArray {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// 建立 一個原始的二維陣列 11*11
		// 0:表示沒有旗子  1:黑子 2:白子
		int[][] chessArr1 ;
		try {
		 int chessArr2[][] = loadGame();
		 chessArr1 = chessArr2;
		}
		catch (Exception e) {
			// TODO: handle exception
			chessArr1 = new int [11][11];
		}
		
		//輸出原始的二維陣列
		System.out.printf("原始的二維陣列\n");
		for (int[] row: chessArr1) {
			for (int data:row) {
				System.out.printf("%d\t",data);
			}
			System.out.printf("\n");
		}
		
		int sum =0;
		for (int i = 0; i < chessArr1.length; i++) {
			for (int j = 0; j < chessArr1.length; j++) {
				if (chessArr1[i][j]!=0) {
					sum++;
				}
			}
			
		};
		
		int sparesArr[][] = new int [sum+1][3];
		
		// 第0列 棋盤資訊
		sparesArr[0][0]= chessArr1.length;
		sparesArr[0][1]= chessArr1.length;
		sparesArr[0][2]= sum;  // 有幾個棋子
		int count =1;
		//將其他資訊存到 陣列裡
		for (int i = 0; i < chessArr1.length; i++) {
			for (int j = 0; j < chessArr1.length; j++) {
				if (chessArr1[i][j]!=0) {
					sparesArr[count][0]= i;
					sparesArr[count][1]= j;
					sparesArr[count][2]= chessArr1[i][j]; 
					count++;
				}
			}			
		};
		saveGame(sparesArr);
		
		System.out.println();
		System.out.println("得到的稀疏陣列~~~");
		for (int i = 0; i < sparesArr.length; i++) {
			System.out.printf("%d\t%d\t%d\t\n",sparesArr[i][0],sparesArr[i][1],sparesArr[i][2]);
		}
		
	}
	
	// 初始化遊戲讀取
	public static int[][] loadGame() throws Exception {
		int[][] result=new int[11][11];
		FileReader fr = new FileReader("chess.txt");
		BufferedReader br = new BufferedReader(fr);		
        int count=0;
        
        while (br.ready()) {
        	String line = br.readLine();
        	System.out.println("line="+line);
			String[] dataArr=line.split(",");
			if (count==0) {
				result = new int[Integer.parseInt(dataArr[0])][Integer.parseInt(dataArr[1])];
			}
			else {
				result[Integer.parseInt(dataArr[0])][Integer.parseInt(dataArr[1])]=Integer.parseInt(dataArr[2]);
			}
			count++;        	
		}
        fr.close();
		return result;
	}
	
	// 遊戲存檔
	public static void saveGame(int[][] arr) {
		File f = new File("chess.txt");
		try {
			FileOutputStream fOut = new FileOutputStream(f);
			OutputStreamWriter writer = new OutputStreamWriter(fOut, "UTF-8");
			for (int i=0;i<arr.length;i++) {
			  writer.append(arr[i][0]+","+arr[i][1]+","+arr[i][2]+"\n");
			}
			writer.close();
	        // 关闭写入流,同时会把缓冲区内容写入文件,所以上面的注释掉
	 
			fOut.close();
	        // 关闭输出流,释放系统资源
		}
		catch (Exception e) {
			// TODO: handle exception
			System.out.println("SaveFile Error"+ e.getMessage());
		}
		
	}

}
