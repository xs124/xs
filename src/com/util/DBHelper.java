package com.util;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * ����MySQL���ݿ⹤����
 * @author Administrator
 * @date 2016��08��01��
 */
public class DBHelper {
   
	//���ݿ�����
	private static final String driver = "com.mysql.jdbc.Driver"; 
	//�������ݿ��URL��ַ
	private static final String url="jdbc:mysql://localhost:3306/xs?useUnicode=true&characterEncoding=UTF-8"; 
	//���ݿ���û���
	private static final String username="root";
	//���ݿ������
	private static final String password="gyldhd1234";
    //Connection���Ӷ���conn
	private static Connection conn=null;
	
	//��̬����鸺���������
	static {
		try{
			Class.forName(driver);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	//����ģʽ�������ݿ����Ӷ���
	public static Connection getConnection() throws Exception{
		if(conn==null){
			conn = DriverManager.getConnection(url, username, password);
			return conn;
		}else{
			return conn;
		}
	}
	
	public static void main(String[] args) {
		//�������ݿ��Ƿ���������
		try{
		   Connection conn = DBHelper.getConnection();
		   if(conn!=null){
			   System.out.println("���ݿ�����������");
		   }else{
			   System.out.println("���ݿ������쳣��");
		   }
		}catch(Exception e){
			e.printStackTrace();
		}

	}
}											


