public class Hello extends Thread{
	int i;
	public void run(){
	while(true){ System.out.println("Hello "+i++); if(i==10) break;
}}}
