package util;

import java.util.HashMap;
import java.util.Map;

public class CustomJavaUtils {
	
	public static void printMap(Map map) {
		HashMap<String,String> mp = (HashMap<String, String>) map;
		for(Map.Entry<String, String> entry : mp.entrySet()) {
			System.out.println("Key = " + entry.getKey() +
                    ", Value = " + entry.getValue());
			
		}	
	}
	
	public static boolean verifyItemFromMapKey(Map map,String searchValue) {
		boolean searchMap = false;
		HashMap<String,String> mp = (HashMap<String, String>) map;
		for(Map.Entry<String, String> entry : mp.entrySet()) {
			String key = entry.getKey().trim();
			if(key.equalsIgnoreCase(searchValue.trim())) {
				searchMap = true;
				break;
			}else {
				searchMap = false;
			}
			
		}
		return searchMap;
	}

}
