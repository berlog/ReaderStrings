import java.util.HashMap;
import java.util.Map;

public class Main {

	public static void main(String[] args) {
		Reader reader = new Reader();
		Map<String, Integer> unsortedMap = getMap(reader);
		//System.out.println(unsortedMap);

		//Sort the collection and display the "top 5" of frequently encountered string
		unsortedMap.entrySet().stream()
				.sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
				.limit(5)
				.forEach(e -> System.out.println(e.getKey()));
	}
	
	
	/**
	 * Method return of collection strings and counters
	 * 
	 * @param reader the instance of Reader object for get some next text
	 * @return the map of string and counter
	*/
	public static Map<String, Integer> getMap(Reader reader) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		
		while(true) {
			String str = reader.getNextText();
			
			if (str != null) {
				Integer i = map.get(str);
				if (i != null) {
					++i;
					map.put(str, i);
				} else {
					map.put(str, 1);
				}
			} else {
				break;
			}
		}
		
		return map;
	}
}