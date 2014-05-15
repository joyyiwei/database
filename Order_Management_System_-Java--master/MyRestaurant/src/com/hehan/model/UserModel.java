/**
 * User Table Model, control the users, mainly write business logic operation
 * 
 */
package com.hehan.model;
import com.hehan.db.*;
import java.sql.*;
public class UserModel {
	/**
	 * 
	 * @param uid userid
	 * @param p userpassword
	 * @return userposition, if not exist, return ""
	 */
	public String checkUser(String uid, String p) {
		String position = "";
		SqlHelper sh = null;

		try {
            String url = "jdbc:mysql://localhost:3306/first?user=root&password=123456";
            Connection conn = DriverManager.getConnection(url);
            PreparedStatement table = conn.prepareStatement("select * from user where name=? and pass=?");
            table.setString(1, uid);
            table.setString(2, p);
            ResultSet haha = table.executeQuery();
            if (haha.next()){
                System.out.println("query success");
                String Name =  haha.getString("name");
                System.out.println(Name+"yes");
                position = Name;
            }
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
		//	sh.close();
		}
		return position;
	}
}
