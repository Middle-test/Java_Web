package com.itheima;



import com.itheima.pojo.User;
import com.mysql.cj.protocol.Resultset;
import org.junit.jupiter.api.Test;

import javax.xml.transform.Result;
import java.sql.*;

public class JdbcTest {
    /**
     * Jdbc的入门程序
     */
    @Test
    public void testUpdate() throws Exception {
        //1、注册驱动


        //2、获取数据库连接
        String url = "jdbc:mysql://localhost:3306/web01";
        String username = "root";
        String password = "1234";
        Connection connection = DriverManager.getConnection(url, username, password);

        //3、获取SQL语句执行对象（静态SQL）
        Statement statement = connection.createStatement();

        //4、执行SQL（DML语句）,返回int型数据，代表影响记录数
        int i = statement.executeUpdate("update user set age = 25 where id = 1");
        System.out.println("SQL语句执行完毕影响的记录数为="+i);

        //5、释放资源
        statement.close();
        connection.close();
    }


    @Test
    public void testSelect() throws Exception {
        String url = "jdbc:mysql://localhost:3306/web01";
        String username = "root";
        String password = "1234";
        Connection connection = DriverManager.getConnection(url, username, password);

//        //静态SQL对象
//        Statement statement = connection.createStatement();

        //动态SQL对象(预编译)
        PreparedStatement preparedStatement = connection.prepareStatement("select id,username,password,name,age from user where username = ? and password = ?");
        preparedStatement.setString(1,"daqiao");
        preparedStatement.setString(2,"123456");

        //执行SQL（DQL）语句，返回ResultSet对象，代表结果集
        ResultSet rs = preparedStatement.executeQuery();
        //遍历结果集
        while (rs.next()) {
            User user = new User(rs.getInt("id"),
                    rs.getString("username"),
                    rs.getString("password"),
                    rs.getString("name"),
                    rs.getInt("age"));
            System.out.println(user);
        }
        rs.close();
        preparedStatement.close();
        connection.close();
    }
}

