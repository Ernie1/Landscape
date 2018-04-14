public class HelloWorld {
	public String say() {
		System.out.println("HelloWorld");
		return "HelloWorld";
	}
	public int getInt() {
		System.out.println(13);
		return 13;
	}
	public static void main(String args[]) {
		HelloWorld h = new HelloWorld();
	}
}