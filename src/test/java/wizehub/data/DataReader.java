package wizehub.data;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataReader {
	
	
	public List<HashMap<String, String>> getJsonDataToMap() throws IOException
	{
		//reading json to string
	String jsonData=FileUtils.readFileToString(new File (System.getProperty("user.dir")+"SeleniumFramework2024\\src\\test\\java\\wizehub\\data\\PurchaseOrder.json"),StandardCharsets.UTF_8);
	
		 
		 //String content to hashmap  using jackson databind
		 ObjectMapper mapper=new ObjectMapper();
		 List<HashMap<String,String>> data= mapper.readValue(jsonData, new TypeReference<List<HashMap<String,String>>> (){ });
		 return data;
		//created hashmpaps with two indexes (two hashmaps) and stored in  a list as {0},{1} 
	}

}
