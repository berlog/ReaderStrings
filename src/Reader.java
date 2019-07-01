import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Reader implements IReader {

	private String [] arrayText = {
			"Василий",
			"Алим",
			"Ким",
			"Макар",
			"Василий",
			"Василий",
			"Владимир",
			"Алим",
			"Владимир",
			"Генрих",
			"Тагир",
			"Василий",
			"Генрих",
			"Василий",
			"Владимир",
			"Ким",
			"Генрих",
			"Генрих",
			"Владимир",
			"Василий",
			"Ким",
			"Василий",
			"Владимир"};

	private int i = 0;
	
	@Override
	public String getNextText() {
		if (i != arrayText.length) {
			String s = arrayText[i];
			++i;
			return s;
		} else {
			return null;
		}
	}

	@Override
	public String getNextTextFromInputStream() throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String str = reader.readLine();
		if ("".equals(str)) {
			return null;
		} else {
			return str;
		}
	}
}
