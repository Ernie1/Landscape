public class test {
	public static void main(String[] args) {
		Boolean arriving = true; 
		Integer peopleInRoom = 0; 
		int maxCapacity = 100;
		while (peopleInRoom < maxCapacity) { 
			if (arriving) {
				System.out.println("很高兴见到你"+peopleInRoom+"号先生" );
				peopleInRoom++;
			}
			else { 
				peopleInRoom--;
			}
		}
	}
}
