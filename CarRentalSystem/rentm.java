package CarRentalSystem;

public class rentm {

	public static void main(String[] args) {
		Car suzuki = new Car();
		suzuki.setCarID("No.1");
		suzuki.setBrand("Suzuki");
		suzuki.setModdel("XL16");
		suzuki.setnoofcaravail(4);
		suzuki.setPriceofday(5000);
		
		Car tata = new Car();
		tata.setCarID("No.2");
		tata.setBrand("Indigo");
		tata.setModdel("X2");
		tata.setnoofcaravail(2);
		tata.setPriceofday(2000);

		
		Car benz = new Car();
		benz.setCarID("No.3");
		benz.setBrand("Benz");
		benz.setModdel("X2");
		benz.setnoofcaravail(1);
		benz.setPriceofday(10000);
		
		Service s = new Service();
		s.addCars(suzuki);
		s.addCars(benz);
		s.addCars(tata);
		
		s.options();
	}

}
