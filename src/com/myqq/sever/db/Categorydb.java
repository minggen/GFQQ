package com.myqq.sever.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.myqq.client.assistui.Category;

public class Categorydb extends Base {
	
	/**
	 *
	 * @param ownerId 谁的分组
	 * @return  分组的集合
	 */
	public List<Category> getCategoryList(String ownerId){
		List<Category> list = new ArrayList<Category>();
		try {
			String sql = "select * from gfqq_category fc where fc.owner_id = "+ Integer.valueOf(ownerId);
			
			ResultSet result = select(sql);
			while (null != result && result.next()) {
				Category category = assembleCategory(result);
				list.add(category);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	private Category assembleCategory(ResultSet result) {
		try {
			String id = String.valueOf(result.getInt("id"));
			String cateName = result.getString("name");
			String cateOwnerId  = result.getString("owner_id");
			String cateType = result.getString("category_type");
			return new Category(id, cateName, cateOwnerId, cateType);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
}
