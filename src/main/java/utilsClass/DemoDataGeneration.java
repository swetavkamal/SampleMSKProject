package utilsClass;


import java.util.TreeMap;


/**
 * 
 * This class has the TreeMap which contains key with integer and value as company name 
 * This is to be used to generate random data..
 * @author swetavk
 *
 */



public class DemoDataGeneration {

	
	public String DataGeneration(int key)
	{
		TreeMap<Integer, String> company_tree_map = new TreeMap<Integer, String>();
		
		company_tree_map.put(1, "AMZ");
		company_tree_map.put(2, "GGLE");
		company_tree_map.put(3, "FCB");
		company_tree_map.put(4, "MSF");
		company_tree_map.put(5, "YAH");
		
		
		return company_tree_map.get(key);
		
		
	}
	
	
	
	
}
