import java.io.IOException;

public interface IReader {
	public String getNextText();
	public String getNextTextFromInputStream() throws IOException;
}
