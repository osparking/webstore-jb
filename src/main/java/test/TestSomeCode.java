package test;

import com.jbpark.webstore.util.ValueFormat;

public class TestSomeCode {

	public static void main(String[] args) {
		long df = 1000l;
		int settings = ValueFormat.COMMAS;
		String formatted = ValueFormat.format(df, ValueFormat.COMMAS);
		System.out.println(formatted);
	}

}
