# Simple E‑commerce by Java

A console‑based e‑commerce system demonstrating key OOP concepts, SOLID design principles, Organized into clear layers (model, service, exception), built with Java 21 in IntelliJ IDEA.

## 🛠️ Technologies & Tools

- **Java 21**
- **IntelliJ IDEA**
- Build with **Maven** (or switch to Gradle if you prefer)
- No external dependencies

## 📦 Project Structure

## 🎯 Object‑Oriented Design

This project uses OOP to keep responsibilities clear:
- *Encapsulation*: Each class (e.g., Product, Cart, Customer) manages its own state and exposes only what’s necessary via methods.
- *Abstraction*: The Shippable interface hides shipping implementation details so services work against a simple contract.
- *Modularity*: Classes have single, focused roles (see SOLID below), making code easier to maintain.
- *Reusability*: New product types or shipping behaviors simply implement Shippable without touching existing logic.

## 🔧 SOLID Principles
1. Single Responsibility
- Product handles product data/expiry logic.
- Cart tracks item selections and enforces quantity limits.
- ECommerce orchestrates checkout flow.
- ShippingService only prints shipment summaries.

2. Open/Closed
- You can extend shipping logic by adding new Shippable implementations (e.g. DigitalProduct) without modifying existing classes.

3. Liskov Substitution
- Any Shippable (current or future) works in ShippingService and ECommerce without surprises.

4.  Interface Segregation
- Only products that ship implement Shippable; no “fat” interfaces.

5. Dependency Inversion
- High‑level code in ECommerce depends on the Shippable abstraction, not on concrete Product details.



