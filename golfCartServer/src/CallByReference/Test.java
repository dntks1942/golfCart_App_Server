package CallByReference;

public class Test {
	public static void main(String[] args) {
		String input = "Testing";
		StringPass pass = new StringPass(input);
		
		pass.modify("MAYBE NOT");
		System.out.println(input);
	}
}
class StringPass{
	String s;
	StringPass(String input){
		s = new String(input);
		input = "changing";
	}
	
	void modify(String input) {
		s = input;
		System.out.println(s);
	}
}
