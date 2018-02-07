package cn.gree.zz.test;

import com.google.gson.JsonObject;

public class TestJson {
	public static void main(String [] args){
		JsonObject object=new JsonObject();
		object.addProperty("id", 1);

		object.addProperty("name", "Java");

		object.addProperty("ide", "Eclipse");
		
		System.out.println(object.toString());
	}
}
