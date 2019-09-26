package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.util.DBHelper;
import com.entity.Items;

/**
 * ��Ʒ��ҵ���߼���
 * @author Administrator
 * @date 2016��08��01��
 */
public class ItemsDAO {

	// ������е���Ʒ��Ϣ
	public ArrayList<Items> getAllItems() {
		Connection conn = null;//���ݿ����Ӷ���
		PreparedStatement stmt = null;//SQL������
		ResultSet rs = null;//���ݼ�����
		
		ArrayList<Items> list = new ArrayList<Items>(); // ��Ʒ����
		
		try {
			conn = DBHelper.getConnection();
			String sql = "select * from items;"; // ��ѯSQL���
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();//��ȡ���ݼ�
			
			while (rs.next()) {
				Items item = new Items();
				item.setId(rs.getInt("id"));
				item.setName(rs.getString("name"));
				item.setCity(rs.getString("city"));
				item.setNumber(rs.getInt("number"));
				item.setPrice(rs.getInt("price"));
				item.setPicture(rs.getString("picture"));
				list.add(item);// ��һ����Ʒ���뼯��
			}
			return list; // ���ؼ��ϡ�
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			// �ͷ����ݼ�����
			if (rs != null) {
				try {
					rs.close();
					rs = null;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			// �ͷ�SQL������
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

	// ������Ʒ���ID�����Ʒ��ϸ����
	public Items getItemsById(int id) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBHelper.getConnection();
			String sql = "select * from items where id=?;"; // SQL��ѯ���
			stmt = conn.prepareStatement(sql);
			//��id��ֵ����SQL��ѯ����е�һ���ʺ�
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
			// �ͷ����ݼ�����
			if (rs != null) {
				try {
					rs.close();
					rs = null;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			// �ͷ�������
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
	
	
	//��ȡ��������ǰ������Ʒ��Ϣ
	public ArrayList<Items> getViewList(String list){
		System.out.println("list:"+list);
		ArrayList<Items> itemlist = new ArrayList<Items>();
		int iCount=5; //ÿ�η���ǰ������¼
		if(list!=null&&list.length()>0){
		    String[] arr = list.split("#");
		    System.out.println("arr.length="+arr.length);
		    //�����Ʒ��¼���ڵ���5��
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
