package com.controller;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.dto.OrderDetailDto;
import com.model.Customer;
import com.model.Product;
import com.service.CustomerService;
import com.service.InventoryService;
import com.service.OrderDetailService;
import com.service.OrderService;
import com.service.ProductService;

public class MainController {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		CustomerService customerService = new CustomerService();
		InventoryService inventoryService = new InventoryService();
		OrderDetailService orderDetailService = new OrderDetailService();
		OrderService orderService = new OrderService();
		ProductService productService = new ProductService();

		while (true) {
			System.out.println("\nWelcome to the TechShop");
			System.out.println("\n============================================");
			System.out.println("Customer Module Operation");
			System.out.println("============================================\n");

			System.out.println("1. Total Orders Placed By a Specific Customer");
			System.out.println("2. Display Information about a specific customer");
			System.out.println("3. Update Information of a specific customer");

			System.out.println("\n============================================");
			System.out.println("Product Module Operation");
			System.out.println("============================================\n");

			System.out.println("4. Display Detailed Information of a specific product");
			System.out.println("5. Update information about a specific product");
			System.out.println("6. Check Product is current in stock or not");

			System.out.println("\n============================================");
			System.out.println("Order Module Operation");
			System.out.println("============================================\n");

			System.out.println("7. Total Amount of a specific order");
			System.out.println("8. Display details of a specific order");

			System.out.println("\n============================================");
			System.out.println("Order Details Module Operation");
			System.out.println("============================================\n");

			System.out.println("9. Calculate sub total");
			System.out.println("10. Display details of a specific orderdetails");
			System.out.println("11. Update quantity of a specific orderdetails");

			System.out.println("\n============================================");
			System.out.println("Inventory Details Module Operation");
			System.out.println("============================================\n");

			System.out.println("12. Get Product using inventory id");
			System.out.println("13. Get quantity of product ");
			System.out.println("14. Update stock quantity of a specific product");
			System.out.println("15. Total value of the specific product in the inventory");
			System.out.println("16. List low stock Products");
			System.out.println("17. List Out of stock Products");

			try {
				int input = sc.nextInt();

				if (input == 0) {
					System.out.println("Exiting the application");
					break;
				}

				switch (input) {
				case 1: {
					int customerId = 1;
					
					int totalOrders = customerService.allOrders(customerId);
					System.out.println("Total orders placed by this customer : " + totalOrders);
					System.out.println();
					break;
				}

				case 2: {
					int customerId = 1;
					Customer c = customerService.displayInfo(customerId);
					System.out.println(c);
					System.out.println();
					break;
				}

				case 3: {
					sc.nextLine();
					Customer c = new Customer();
					int customerId = 1;

					c.setCustomerId(customerId);
					System.out.print("Enter first name : ");
					c.setFirstName(sc.next().trim());
					System.out.print("Enter last name : ");
					c.setLastName(sc.next().trim());
					System.out.print("Enter phone number : ");
					c.setPhone(sc.nextInt());
					System.out.print("Enter address : ");
					c.setAddress(sc.nextLine());
					boolean status = customerService.udpateInfo(c);
					if (status)
						System.out.println("Updated successfully");
					else
						System.out.println("Not updated");
					System.out.println();
					break;
				}

				case 4: {
					int productId = 1;
					Product p = productService.displayInfo(productId);
					System.out.println(p);
					System.out.println();
					break;
				}

				case 5: {
					sc.nextLine();
					int productId = 1;
					Product p = new Product();
					p.setProductId(productId);
					System.out.print("Enter product Name: ");
					p.setProductName(sc.nextLine().trim());
					System.out.print("Enter description: ");
					p.setDescription(sc.nextLine().trim());
					System.out.print("Enter price: ");
					p.setPrice(sc.nextDouble());

					boolean status = productService.udpateInfo(p);
					if (status)
						System.out.println("Updated successfully");
					else
						System.out.println("Not updated");
					System.out.println();
					break;
				}

				case 6: {
					sc.nextLine();
					int productId = 1;
					boolean isAvailable = productService.isAvailableInStock(productId);
					if (isAvailable)
						System.out.println("Product is available in stock");
					else
						System.out.println("Product is not in stock");

					System.out.println();
					break;
				}

				case 7: {
					int orderId = 1;
					double totalAmount = orderService.totalAmount(orderId);
					System.out.println("Total amount : " + totalAmount);

					break;
				}

				case 8: {
					int orderId = 1;
					List<OrderDetailDto> order = orderService.displayInfo(orderId);
					for (OrderDetailDto o : order)
						System.out.println(o);
					System.out.println();
					break;
				}

				case 9: {
					int orderDetailId = 1;

					double amount = orderDetailService.getAmount(orderDetailId);
					System.out.println("Your amount for this order is: " + amount);
					System.out.println();
					break;
				}
				case 10: {
					int orderDetailId = 1;
					OrderDetailDto order = orderDetailService.displayInfo(orderDetailId);
					System.out.println(order);
					System.out.println();
					break;
				}

				case 11: {
					int orderDetailId = 1;
					System.out.print("Enter the quantity: ");
					int quantity = sc.nextInt();
					boolean status = orderDetailService.updateQuantity(quantity, orderDetailId);

					if (status)
						System.out.println("Updated successfully");
					else
						System.out.println("Not updated");
					System.out.println();
					break;
				}

				case 12: {
					int inventoryId = 1;
					Product p = inventoryService.getProduct(inventoryId);
					System.out.println(p);

					System.out.println();
					break;
				}
				case 13: {
					int productId = 1;
					int quantity = inventoryService.getQuantity(productId);
					System.out.println("The Quantity of this product in inventory is: " + quantity);
					System.out.println();
					break;
				}
				case 14: {
					int productId = 1;
					System.out.print("Enter the quantity to be upated: ");
					int quantity = sc.nextInt();

					boolean status = inventoryService.updateQuantity(quantity, productId);
					if (status)
						System.out.println("Updated successfully");
					else
						System.out.println("Not updated");
					System.out.println();
					break;
				}
				case 15: {
					int productId = 1;

					double totalValue = inventoryService.calculateTotalValue(productId);
					System.out.println("Total value : " + totalValue);
					System.out.println();
					break;
				}
				case 16: {
					System.out.println("Enter the threshold: ");
					int threshold = sc.nextInt();
					List<Product> p = inventoryService.lowStockProduct(threshold);
					if(p.size()!=0)
					{
						for (Product product : p)
							System.out.println(product);
						System.out.println();
					}else {
						System.out.println("No product found");
					}
					break;
				}
				case 17: {
					List<Product> p = inventoryService.outOfStockProduct();
					if(p.size()!=0)
					{
						for (Product product : p)
							System.out.println(product);
					}
					else {
						System.out.println("No product Found");
					}
					System.out.println();
					break;
				}
				default:
					System.out.println("Invalid Input.Please press correct number");
				}
			} catch (SQLException e) {

				System.out.println(e.getMessage());
			} catch (InputMismatchException e) {
				System.out.println("Invalid Input. Please press correct input type value");
				sc.next();
				continue;
			}
		}
		sc.close();
	}
}
