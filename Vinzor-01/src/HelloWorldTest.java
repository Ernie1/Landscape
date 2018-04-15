import org.junit.Test;
import org.junit.Ignore;
import static org.junit.Assert.assertEquals;

public class HelloWorldTest {
	public HelloWorld h = new HelloWorld();
	// 元数据
	@Test
	public void testHelloWorld() {
		System.out.println("Inside testHelloWorld()"); 
		assertEquals("HelloWorld", h.say());
	}
	@Test
	public void testgetInt() {
		System.out.println("Inside testgetInt()");
		assertEquals(13, h.getInt());
	}
}

// javac HelloWorldTest.java
// 用-ea 可打开断言机制，如果希望只运行某些包或类中的断言，可将包名或类名加到-ea之后
// java -ea org.junit.runner.JUnitCore HelloWorldTest