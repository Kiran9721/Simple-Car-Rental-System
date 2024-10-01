package CarRentalSystem;

public class Car {
	
	private String carID;
	private String moddel;
	private String brand;
	private double priceofday;
	private int noofcaravail;
	
	
	public String getCarID() {
		return carID;
	}
	public void setCarID(String carID) {
		this.carID = carID;
	}
	public String getModdel() {
		return moddel;
	}
	public void setModdel(String moddel) {
		this.moddel = moddel;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public double getPriceofday() {
		return priceofday;
	}
	public void setPriceofday(double priceofday) {
		this.priceofday = priceofday;
	}
	public int getnoofcaravail() {
		return noofcaravail;
	}
	public void setnoofcaravail(int noofcaravail) {
		this.noofcaravail = noofcaravail;
	}
	public double calculatePrice(int days) {
		return priceofday * days;
	}
}
