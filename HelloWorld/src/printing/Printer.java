package printing;

public class Printer {
	public boolean isOn;
	public String modelNumber;
	public void print()
	{
		System.out.println(modelNumber);
	}
	public void print(String text)
	{
		System.out.println(text);
	}
}
