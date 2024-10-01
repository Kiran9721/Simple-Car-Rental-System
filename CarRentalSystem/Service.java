package CarRentalSystem;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Service {
	private List<Car> cars;
	private List<Customer> customers;
	private List<booking> bookedCarInformations;

	
	public Service() {
		
		cars = new ArrayList<Car>();
		customers = new ArrayList<Customer>();
		bookedCarInformations = new ArrayList<>();
		
	}
	public void bookedCar(Car car, Customer customer, int days) {
		if (car.getnoofcaravail() > 0) {
			car.setnoofcaravail(car.getnoofcaravail() - 1);
			bookedCarInformations.add(new booking(car, customer, days));
		} else {
			System.out.println("Car is not available for rent.");
		}
	}

	public void returnCar(Car car, booking bookedCarInformation) {
		car.setnoofcaravail(car.getnoofcaravail() + 1);
		bookedCarInformations.remove(bookedCarInformation);
	}
	
	public void addCars(Car car) {
		cars.add(car);
	}
	public void addCustomers(Customer customer) {
		customers.add(customer);
	}
	
	public void options() {
		
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		
		while(true) {
			System.out.println("---Hello Custome, Welcome to our Car Rental System---");
			System.out.println("How may I Help You?. Please Select the Options Below");
			System.out.println("1. Rent a Car");
			System.out.println("2. Return a Car");
			System.out.println("3. Car Availablity");
			System.out.println("4.	Exit the Session");
			System.out.println("Enter: ");
			
			int choice = sc.nextInt();
			sc.nextLine();
			
			if(choice ==1) {
				System.out.println("---Provide a Details to Make the Further Process");
				System.out.println("Enter your name: ");
				
				String cusName = sc.nextLine();
				
				System.out.println("Enter Car ID: ");
				String carID = sc.nextLine();
				
				System.out.println("Enter How many days you want: ");
				int rdays = sc.nextInt();
			
				Customer customer = new Customer("CUSTOMER-" + (customers.size()+ 1), cusName);
				addCustomers(customer);
				Optional<Car> optionalCar = cars.stream()
						.filter(c -> c.getCarID().equalsIgnoreCase(carID) && c.getnoofcaravail() > 0).findAny();

				if (optionalCar.isEmpty()) {
					System.out.println("Car is not available. Please try to book another car.");
					options();
					return;
				}

				Car selectedCar = optionalCar.get();

				System.out.println("=== Bill Receipt ===");
				System.out.println("Customer ID: " + customer.getId());
				System.out.println("Customer Name: " + customer.getName());
				System.out.println("Car Brand: " + selectedCar.getBrand() + " Model: " + selectedCar.getModdel());
				System.out.println("Rental Days: " + rdays);
				System.out.println("Total Price: " + selectedCar.calculatePrice(rdays));

				System.out.println("Confirm rental (Y/N): ");
				String confirmation = sc.next();

				if (confirmation.equalsIgnoreCase("Y")) {
					// Booked a Car
					bookedCar(selectedCar, customer, rdays);
					System.out.println("Car booking is done successfully.");
				} else {
					System.out.println("Car booking is canceled.");
				}
			} else if (choice == 2) {
				System.out.println("== Return a Car ==");
				System.out.println("Enter the car ID you want to return: ");
				String carId = sc.nextLine();

				Optional<Car> optionalCar = cars.stream().filter(c -> c.getCarID().equals(carId)).findAny();

				if (optionalCar.isEmpty()) {
					System.out.println("Please provide valid car details.");
					options();
					return;
				}

				Car carToReturn = optionalCar.get();

				booking bookedCarInformation = bookedCarInformations.stream()
						.filter(b -> b.getCar() == carToReturn).findFirst().orElse(null);

				if (bookedCarInformation == null) {
					System.out.println("Car information not available. Please provide valid details.");
					options();
					return;
				}

				Customer cust = bookedCarInformation.getCustomer();

				returnCar(carToReturn, bookedCarInformation);
				System.out.println("Car returned successfully by " + cust.getName());
			} else if (choice == 3) {
				System.out.println("== Available Cars ==");

				cars.stream().filter(c -> c.getnoofcaravail() > 0).forEach(car -> System.out.println(car.getCarID()
						+ " - " + car.getBrand() + " " + car.getModdel() + " " + car.getnoofcaravail()));
			} else if (choice == 4) {
				System.out.println("Thank you for choosing us.");
				break;
			} else {
				System.out.println("Please provide valid options.");
			}
			
			}
		}
	}


