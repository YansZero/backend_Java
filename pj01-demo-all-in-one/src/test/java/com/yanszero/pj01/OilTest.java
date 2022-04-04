package com.yanszero.pj01;

import com.yanszero.pj01.dao.BaseDao;
import com.yanszero.pj01.entity.Oil;
import com.yanszero.pj01.util.JDBCUtils;
import org.junit.Test;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class OilTest {

  private BaseDao<Oil> baseDao = new BaseDao<>();

  @Test
  public void testGetSingleBean() {
    //查詢單個SQL指令
    StringBuilder sql_Query = new StringBuilder();

    sql_Query.append("select oil_92 ,oil_95 ,oil_98 ,diesel_fuel \n ")
             .append(",company,price_date,create_date \n")
             .append("from oil  \n ")
             .append("where 1 =? ");

    Oil oil = baseDao.getSingleBean(sql_Query.toString(),Oil.class,1);

    System.out.println("Oil=" + oil);
  }

  @Test
  public void testGetBeanList() {
    //查詢多筆SQL指令
    StringBuilder sql_Query = new StringBuilder();

    sql_Query.append("select oil_92 ,oil_95 ,oil_98 ,diesel_fuel \n ")
            .append(",company,price_date,create_date \n")
            .append("from oil  \n ")
            .append("where 1 =? ");

    List<Oil> oilList = baseDao.getBeanList(sql_Query.toString(),Oil.class,1);

    for (Oil oil : oilList) {
      System.out.println("Oil=" + oil);
    }
  }

  @Test
  public void testUpdate() {
    StringBuilder sql_Update = new StringBuilder();
    List param = new ArrayList<>();

    sql_Update.append(" update oil set oil_92=? ,oil_95=? ,oil_98=? \n ")
              .append(" where company=? and price_date=? ");
    param.add(30.3);
    param.add(31.8);
    param.add(33.9);
    param.add("cpc");
    param.add("2022-02-21");

    int affrow = baseDao.update(sql_Update.toString(),30.3,31.8,33.9,"cpc","2022-02-21");

    System.out.println("affrow="+ affrow);
  }

  @Test
  public void testGetConnection() {

    //測試連結用
    Connection connection = JDBCUtils.getConnection();
    System.out.println("connection = "+ connection);

    JDBCUtils.releaseConnection(connection);
  }
}
