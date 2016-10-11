package layout.component;

import java.util.HashMap;

/**
 * @author YuanyuLiang
 *
 * @description 避免重複的keymaps
 */
public class KeyMaps {
	private HashMap<String, String> currentNameMap = new HashMap<String, String>();

	/**
	 * @return the currentNameMap
	 */
	public HashMap<String, String> getCurrentNameMap() {
		return currentNameMap;
	}

	/**
	 * @param currentNameMap the currentNameMap to set
	 */
	public void setCurrentNameMap(HashMap<String, String> currentNameMap) {
		this.currentNameMap = currentNameMap;
	}
	
	public boolean containsKey(String key){
		return this.currentNameMap.containsKey(key);
	}
	
	public void put(String key, String value){
		this.currentNameMap.put(key, value);
	}
	
	public void clear(){
		this.currentNameMap.clear();
	}
}
