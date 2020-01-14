package mockitoproject01;

import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class BookPrice {
	
	public static void main(String[] args) {
		NaverBookApi pricer = new NaverBookApi();
		try {
			String info = pricer.getBookInfo("12345");
			System.out.println(info);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		BookPrice b = new BookPrice();
		try {
			System.out.println(b.price("¡§¿ŒªÛ"));
		} catch (IOException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private NaverBookApi bookApi = new NaverBookApi();
	
	public void setBookApi(NaverBookApi bookApi) {
		this.bookApi = bookApi;
	}
	
	public int price(String authName) throws IOException, ParseException  {
		String json = bookApi.getBookInfo(authName);
		JSONParser parser = new JSONParser();
		JSONObject obj = (JSONObject)parser.parse(json);

		if ((JSONObject)obj.get("error") == null) {
			JSONArray item = (JSONArray)obj.get("items");
			int total = 0;
			for(int i=0;i<item.size();i++){
				JSONObject cell = (JSONObject)item.get(i);
				String authors = (String)cell.get("author");
				if (authors.contains(authName)) {
					String price = (String)cell.get("price");
					total += Integer.parseInt(price);
				}
			}
			return total;
		}
		else {
			JSONObject error = (JSONObject)obj.get("error") ;
			StringBuffer exMsg = new StringBuffer();
			String errorCode = (String)error.get("errorCode");
			exMsg.append(errorCode);
			String errorMsg = (String)error.get("message");
			exMsg.append(":"+errorMsg);
			throw new BookException(exMsg.toString());
		}
	}
}
