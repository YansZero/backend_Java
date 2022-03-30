package com.yanszero.pj01.util;

import com.alibaba.druid.pool.DruidAbstractDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Properties;

/**
 * 功能1: 從數據源連接數據庫
 *功能2:將數據綁定到本地線程
 *功能3:釋放線程時和本地線程解除綁定
 */
public class JDBCUtils {
    // 從數據成員變量設置為靜態資源，保證對象的單例性，同時保證靜態方法中可以訪問
    private static DataSource dataSource;

    // 從靜態代碼塊中初始化數據源
    static {
      try {
        // 從jdbc.properties文件中讀取數據度的資料
        // 類路徑的根目錄 會打包到WEB-INF/classes 目錄下
        //類路徑無論本地運行或者服務氣運行都會視同一個基準
        ClassLoader classLoader = JDBCUtils.class.getClassLoader();

        //2.透過類加載器對象從類
        InputStream stream = classLoader.getResourceAsStream("jdbc.properties");

        //3.使用properties 讀取封裝後的數性質
        Properties properties = new Properties();
        properties.load(stream);

        //4.根據properties 獲取的資訊 來建立資料庫連線
          dataSource = DruidDataSourceFactory.createDataSource(properties);
      } catch (Exception e) {
        e.printStackTrace();

        // 為了避免在真正拋出錯誤後 Catch到異常後掩蓋真正問題
        // 將得到的錯誤訊息 丟出去
        throw  new RuntimeException(e);
      }
    }

   public static Connection getConnection() {
        return null;
   }
}
