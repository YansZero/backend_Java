package com.yanszero.pj01.util;

import com.alibaba.druid.pool.DruidAbstractDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * 功能1: 從數據源連接數據庫
 *功能2:將數據綁定到本地線程
 *功能3:釋放線程時和本地線程解除綁定
 */
public class JDBCUtils {
    // 從數據成員變量設置為靜態資源，保證對象的單例性，同時保證靜態方法中可以訪問
    private static DataSource dataSource;

    //由於 ThreadLoacl 對象須作為綁定數據時K-V 對中的K 所以要保證唯一性
    // 加上 static 聲明為靜態資源即可保證唯一性
    private static ThreadLocal<Connection> threadLocal = new ThreadLocal<>();;

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

    /**
     *功能: 獲取資料庫連接並返回
     * @return
     */
    public static Connection getConnection() {
      Connection connection = null;
      try {
        //1.嘗試從當前線程檢查是否有綁定的Connection
        connection = threadLocal.get();

        //2.檢查連接 是否為NULL
        if (connection == null) {

          //3.如果為NULL 則獲取新的數據連接
          connection = dataSource.getConnection();

          //4.綁定到 threadLocal 讓來源固定
          threadLocal.set(connection);
        }
      }
      catch (SQLException e) {
        e.printStackTrace();
        // 為了避免在真正拋出錯誤後 Catch到異常後掩蓋真正問題
        // 將得到的錯誤訊息 丟出去
        throw  new RuntimeException(e);
      }
      return connection;
    }

  /**
   *功能: 釋放資料庫連接
   * void
   */
  public static void releaseConnection(Connection connection) {
    if (connection != null) {
      try {
        connection.close();
        threadLocal.remove();
      }
      catch (SQLException e) {
        e.printStackTrace();
        // 為了避免在真正拋出錯誤後 Catch到異常後掩蓋真正問題
        // 將得到的錯誤訊息 丟出去
        throw  new RuntimeException(e);
      }
    }
  }

  /**
   * 功能:交易先鎖住
   * 避免其他資料被異動
   * @return SQLException
   */
  public static void beginTran() {
    Connection connection = getConnection();
    try {
      if (connection != null) {
        connection.setAutoCommit(false);
      }
    }
    catch (SQLException e) {
      e.printStackTrace();
      // 為了避免在真正拋出錯誤後 Catch到異常後掩蓋真正問題
      // 將得到的錯誤訊息 丟出去
      throw  new RuntimeException(e);
    }
  }

  /**
   * 功能:交易失敗要回滾
   * @return SQLException
   */
  public static void rollBackTran() {
    Connection connection = getConnection();
    try {
      if (connection != null) {
        connection.rollback();
      }
    }
    catch (SQLException e) {
      e.printStackTrace();
      // 為了避免在真正拋出錯誤後 Catch到異常後掩蓋真正問題
      // 將得到的錯誤訊息 丟出去
      throw  new RuntimeException(e);
    }
  }

  /**
   * 功能:交易成功要提交
   * @return SQLException
   */
  public static void commitTran() {
    Connection connection = getConnection();
    try {
      if (connection != null) {
        connection.commit();
      }
    }
    catch (SQLException e) {
      e.printStackTrace();
      // 為了避免在真正拋出錯誤後 Catch到異常後掩蓋真正問題
      // 將得到的錯誤訊息 丟出去
      throw  new RuntimeException(e);
    }
  }
}
