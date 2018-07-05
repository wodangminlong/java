package com.test.api.index;

import com.test.util.MysqlDataBaseUtil;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * index页面加载
 * Created by dml on  2018/7/5
 */
@WebServlet({"","/index"})
public class index extends HttpServlet{

    private static final Logger LOGGER = Logger.getLogger(index.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            try {
                //连接到数据库
                Connection conn=MysqlDataBaseUtil.openConnection();
                Statement stmt=conn.createStatement();
                ResultSet rs=stmt.executeQuery("select *from test");
                             // 利用while循环将数据库中的信息输出
                            while(rs.next()) {
                                String id = rs.getString("test_id");
                                String name = rs.getString("test_name");
                                HttpSession session = req.getSession();
                                session.setAttribute("id",id);
                                session.setAttribute("name",name);
                            }
                if(stmt!=null){
                    stmt.close();
                }
                if (rs!=null){
                    rs.close();
                }
                if(conn!=null){
                    MysqlDataBaseUtil.closeConnection();
                }
                req.getRequestDispatcher("/index.jsp").forward(req, resp);
            } catch (Exception e){
                LOGGER.error("index页面加载发生异常！",e);
            }
    }
}
