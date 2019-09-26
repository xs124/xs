package com.util;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * 连接MySQL数据库工具类
 * @author Administrator
 * @date 2016年08月01日
 */
public class DBHelper {
   
	//数据库驱动
	private static final String driver = "com.mysql.jdbc.Driver"; 
	//连接数据库的URL地址
	private static final String url="jdbc:mysql://localhost:3306/xs?useUnicode=true&characterEncoding=UTF-8"; 
	//数据库的用户名
	private static final String username="root";
	//数据库的密码
	private static final String password="gyldhd1234";
    //Connection连接对象conn
	private static Connection conn=null;
	
	//静态代码块负责加载驱动
	static {
		try{
			Class.forName(driver);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	//单例模式返回数据库连接对象
	public static Connection getConnection() throws Exception{
		if(conn==null){
			conn = DriverManager.getConnection(url, username, password);
			return conn;
		}else{
			return conn;
		}
	}
	
	public static void main(String[] args) {
		//测试数据库是否连接正常
		try{
		   Connection conn = DBHelper.getConnection();
		   if(conn!=null){
			   System.out.println("数据库连接正常！");
		   }else{
			   System.out.println("数据库连接异常！");
		   }
		}catch(Exception e){
			e.printStackTrace();
		}

	}
}											


