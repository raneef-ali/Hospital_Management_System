package com.org.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.org.entity.User;
import com.org.helper.ConnectionHelper;

public class UserDao {
	
	public boolean registerUser(User u)
	{
		try {
			
			Connection con =ConnectionHelper.getConnection();
			String query="INSERT INTO user (fullname,email,password) VALUES(?,?,?)";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, u.getFullname());
			ps.setString(2, u.getEmail());
			ps.setString(3, u.getPassword());
			
			int res=ps.executeUpdate();
			if(res==1)
			{
				return true;
			}
		} catch (Exception e) {
			 e.printStackTrace();
		}
		return false;
	}
	
	public  User logIn(String email,String password)
	{
		 User u=null;
		 
		 try {
			 
			 Connection con= ConnectionHelper.getConnection();
			 String query="SELECT * FROM user WHERE email=? AND password=?";
			 PreparedStatement ps = con.prepareStatement(query);
			 ps.setString(1, email);
			 ps.setString(2, password);
			 ResultSet res = ps.executeQuery();
			 if(res.next())
			 {
				 u=new User();
				 u.setId(res.getInt(1));
				 u.setFullname(res.getString(2));
				 u.setEmail(res.getString(3));
				 u.setPassword(res.getString(4));
			 }
			 
			
		} catch (Exception e) {
			 e.printStackTrace();
		}
		 return u;
	}
	
	
	public boolean checkOldPassword(int userid, String oldPassword) {
		boolean f = false;
		Connection con =ConnectionHelper.getConnection();

		try {
			String sql = "select * from user where id=? and password=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, userid);
			ps.setString(2, oldPassword);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				f = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return f;

	
	}	
	
	public boolean changePassword(int userid, String newPassword) {
		boolean f = false;

		Connection con =ConnectionHelper.getConnection();
		try {
			String sql = "update user set password=? where id=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, newPassword);
			ps.setInt(2, userid);

			int i = ps.executeUpdate();
			if (i == 1) {
				f = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return f;
	}
	
	
	
	
}



