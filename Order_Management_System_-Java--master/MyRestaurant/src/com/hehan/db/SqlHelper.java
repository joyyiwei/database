/**
 * operate the database
 * CRUD
 */

package com.hehan.db;
import java.util.*;
import java.sql.*;
import java.sql.Driver;
import java.util.concurrent.ExecutionException;
//import com.microsoft.sqlserver.jdbc.SQLServerDriver
// ;

public class SqlHelper {
	
	PreparedStatement ps = null;
	ResultSet rs = null;
	Connection ct = null;
	/*String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	String url = "jdbc:sqlserver://127.0.0.1:1433;databaseName=RestaurantDB";
	String user = "hehan";
	String passwd = "hehan";
	*/
    private static String URL = "jdbc:mysql://localhost:3306/first?user=root&password=123456";
    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public SqlHelper() {

		/*try {
			//load driver
			Class.forName(driverName);
			ct = DriverManager.getConnection(url,user,passwd);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}*/
    }
    //Constructor, initialize ct
	public SqlHelper(String name, String pass)throws Exception {
        String url = "jdbc:mysql://localhost:3306/first?user=root&password=123456";
        Connection conn = DriverManager.getConnection(url);
        PreparedStatement table = conn.prepareStatement("select * from user where name=? and pass=?");
        table.setString(1, name);
        table.setString(2, pass);
        ResultSet haha = table.executeQuery();
        if (haha.next()){
            System.out.println("query success");
            String Name =  haha.getString("name");
            System.out.println(Name+"yes");
        }
		/*try {
			//load driver
			Class.forName(driverName);
			ct = DriverManager.getConnection(url,user,passwd);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}*/
	}

    public static void main(String arge[])throws Exception{
        // 获得数据库的连接
        Connection conn = DriverManager.getConnection(URL);
        PreparedStatement hehe = conn.prepareStatement("select * from mytable where sex=?");
        hehe.setString(1, "f");

        ResultSet haha = hehe.executeQuery();
        while (haha.next()){
            String name =  haha.getString("name");
            System.out.println(name);
        }
    }

	//add, delete, update
	public boolean exeUpdate(String sql, String[] paras) {
		boolean b = true;
		try {
            //每次都要获得一个新的连接
			ps = ct.prepareStatement(sql);
			//give values to arguments
			for(int i=0; i<paras.length;i++) {
				ps.setString(i+1, paras[i]);
			}
			ps.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception
			b = false;
			e.printStackTrace();
		}
		
		return b;
	}
	
	public ResultSet query(String sql, String []paras) {
		try {
			ps = ct.prepareStatement(sql);
			//give values to arguments
			for(int i=0; i<paras.length;i++) {
				ps.setString(i+1, paras[i]);
			}
			rs = ps.executeQuery();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return rs;
		
	}
	
	//close the resource
	public void close() {
		try {
			if(rs!=null) rs.close();
			if(ps!=null) ps.close();
			if(ct!=null) ct.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}



