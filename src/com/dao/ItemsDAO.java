package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.util.DBHelper;
import com.entity.Items;

/**
 * 商品的业务逻辑类
 * @author Administrator
 * @date 2016年08月01日
 */
public class ItemsDAO {

	// 获得所有的商品信息
	public ArrayList<Items> getAllItems() {
		Connection conn = null;//数据库连接对象
		PreparedStatement stmt = null;//SQL语句对象
		ResultSet rs = null;//数据集对象
		
		ArrayList<Items> list = new ArrayList<Items>(); // 商品集合
		
		try {
			conn = DBHelper.getConnection();
			String sql = "select * from items;"; // 查询SQL语句
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();//获取数据集
			
			while (rs.next()) {
				Items item = new Items();
				item.setId(rs.getInt("id"));
				item.setName(rs.getString("name"));
				item.setCity(rs.getString("city"));
				item.setNumber(rs.getInt("number"));
				item.setPrice(rs.getInt("price"));
				item.setPicture(rs.getString("picture"));
				list.add(item);// 把一个商品加入集合
			}
			return list; // 返回集合。
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			// 释放数据集对象
			if (rs != null) {
				try {
					rs.close();
					rs = null;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			// 释放SQL语句对象
			if (stmt != null) {
				try {
					stmt.close();
					stmt = null;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

	}

	// 根据商品编号ID获得商品详细资料
	public Items getItemsById(int id) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBHelper.getConnection();
			String sql = "select * from items where id=?;"; // SQL查询语句
			stmt = conn.prepareStatement(sql);
			//把id的值赋给SQL查询语句中第一个问号
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			
			if (rs.next()) {
				Items item = new Items();
				item.setId(rs.getInt("id"));
				item.setName(rs.getString("name"));
				item.setCity(rs.getString("city"));
				item.setNumber(rs.getInt("number"));
				item.setPrice(rs.getInt("price"));
				item.setPicture(rs.getString("picture"));
				return item;
			} else {
				return null;
			}

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			// 释放数据集对象
			if (rs != null) {
				try {
					rs.close();
					rs = null;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			// 释放语句对象
			if (stmt != null) {
				try {
					stmt.close();
					stmt = null;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		}
	}
	
	
	//获取最近浏览的前五条商品信息
	public ArrayList<Items> getViewList(String list){
		System.out.println("list:"+list);
		ArrayList<Items> itemlist = new ArrayList<Items>();
		int iCount=5; //每次返回前五条记录
		if(list!=null&&list.length()>0){
		    String[] arr = list.split("#");
		    System.out.println("arr.length="+arr.length);
		    //如果商品记录大于等于5条
		    if(arr.length>=5){
		       for(int i=arr.length-1;i>=arr.length-iCount;i--){
		    	  itemlist.add(getItemsById(Integer.parseInt(arr[i])));  
		       }
		    }else{
		    	for(int i=arr.length-1;i>=0;i--){
		    		itemlist.add(getItemsById(Integer.parseInt(arr[i])));
		    	}
		    }
		    return itemlist;
		}else{
			return null;
		}
	}

}
