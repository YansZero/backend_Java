package com.yanszero.pj01.filter;

import com.yanszero.pj01.util.JDBCUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class TransactionFilter implements Filter {

    private static Set<String> staticResourceExtName;

    // 建立一個靜態資源 Set
    static {
        staticResourceExtName = new HashSet<>();
        staticResourceExtName.add(".jpg");
        staticResourceExtName.add(".png");
        staticResourceExtName.add(".css");
        staticResourceExtName.add(".js");
        staticResourceExtName.add(".bmp");
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException { }

    @Override
    public void destroy() { }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        // 前置操作，先排除靜態資源
        HttpServletRequest httpServReq = (HttpServletRequest) servletRequest;
        String servletPath = httpServReq.getServletPath();
        String extName = servletPath.substring(servletPath.lastIndexOf("."));

        if (staticResourceExtName.contains(extName)) {
            //如果當前請求是靜態資源 直接執行 往後就不做了
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        Connection connection = null;


        try {
            // 1.取得資料庫連接
            connection = JDBCUtils.getConnection();

            // 先關閉自動提交，讓流程自己控制
            connection.setAutoCommit(false);

            // 2. 核心操作 通過filterChain 對象實現當前請求
            //這樣就可以保證當前請求覆蓋的 Servlet方法 , Service方法 ,Dao方法都在同一個事務中
            //同時各個請求都會經過這個filter，整個流程控制在這裡寫一次就好
            //可減少同樣的代碼
            filterChain.doFilter(servletRequest, servletResponse);

            // 3.順利執行 就提交交易
            connection.commit();
        }
        catch (Exception e){
            try {
                // 4.錯誤時回復交易
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            // 頁面提示 將這裡的異常發送到指定頁面
            // 獲取異常訊息
            String message = e.getMessage();
            // 將異常訊息塞入請求
            httpServReq.setAttribute("systemMessage",message);
            // 將請求轉發盪指定位置
            httpServReq.getRequestDispatcher("/").forward(servletRequest, servletResponse);

        }
        finally {
            // 5.最後要釋放連結
            JDBCUtils.releaseConnection(connection);
        }

    }


}
