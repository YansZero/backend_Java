package com.yanszero.pj01.dao;

import com.yanszero.pj01.util.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *  BaseDao class :所有Dao 實現類的基本類型
 * @param <T> 實體類的類型
 *  class BaseDao<T> => 聲明泛型<T> 含意是代表實體類類型
 */

public class BaseDao<T> {

  // DBUtils 工具包提供的資料庫操作對象
  private QueryRunner runner =new QueryRunner();

  /**
   *查詢返回多個對象的方法
   * @param sql 質詢查詢的SQL語法
   * @param entityClass 實現類對應的CLASS對象
   * @param parameters 傳給SQL語法的參數
   * @return 查詢到結果
   * 使用泛型 回傳T 以及 Class<T>
   */
  public List<T> getBeanList(String sql, Class<T> entityClass, Object ... parameters ) {
    try {
      Connection connection = JDBCUtils.getConnection();
      return runner.query(connection, sql, new BeanListHandler<>(entityClass), parameters);
    }
    catch (SQLException e) {
      e.printStackTrace();
      new RuntimeException(e);
    }
    return null;
  }

  /**
   *查詢單個SQL
   * @param sql 質詢查詢的SQL語法
   * @param entityClass 實現類對應的CLASS對象
   * @param parameters 傳給SQL語法的參數
   * @return 查詢到的實體類對象
   * 使用泛型 回傳T 以及 Class<T>
   */
  public T getSingleBean(String sql,Class<T> entityClass,Object ... parameters ) {
//    List<T> result = new ArrayList<>();
    try {
      Connection connection = JDBCUtils.getConnection();
      return runner.query(connection, sql, new BeanHandler<>(entityClass), parameters);
    }
    catch (SQLException e) {
      e.printStackTrace();
      new RuntimeException(e);
    }

    return null;
  }

  /**
   *通用的增刪改方法 insert update delete 都可以用這個
   * @param sql 的SQL語法
   * @param parameters 傳給SQL語法的參數
   * @return 受影響的行數
   * 使用泛型 回傳T 以及 Class<T>
   */
  public int update(String sql,Object ... parameters) {
    try {
      Connection connection = JDBCUtils.getConnection();
      Integer affect_rows= runner.update(connection,sql, parameters);
      return affect_rows;
    }
    catch (SQLException e) {
      e.printStackTrace();
      new RuntimeException(e);
    }
    return 0;
  }
}
