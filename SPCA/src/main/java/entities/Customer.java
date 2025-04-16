package entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Customer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String username;
	private String password;
	private String shippingAddress;
	private String paymentMethod;
	
	public Customer() {}
	
	private Customer(Builder builder) {
		this.id = builder.id;
		this.username = builder.username;
		this.password = builder.password;
		this.shippingAddress = builder.shippingAddress;
		this.paymentMethod = builder.paymentMethod;
	}

	public int getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getShippingAddress() {
		return shippingAddress;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public static class Builder {
		private final String username;
		private final String password;
		private int id = 0;
		private String shippingAddress = "";
		private String paymentMethod = "";
		
		public Builder(String username, String password) {
			this.username = username;
			this.password = password;
		}
		
		public Builder id(int id) {
			this.id = id;
			return this;
		}
		
		public Builder shippingAddress(String shippingAddress) {
			this.shippingAddress = shippingAddress;
			return this;
		}
		
		public Builder paymentMethod(String paymentMethod) {
			this.paymentMethod = paymentMethod;
			return this;
		}
		
		public Customer build() {
			return new Customer(this);
		}
	}
}
