
# **BFI Bank Project**

BFI Bank is a comprehensive banking system designed to provide users with the ability to create professional bank accounts, manage user data, and verify accounts through email and phone. The project integrates several microservices, including account management, user services, and notifications, ensuring a seamless and secure user experience.

## **Features**

- **User Registration**: 
  - Users can register with their email, phone number, and other personal information.
  
- **Email and Phone Verification**: 
  - Verification via email and OTP (One-Time Password) for phone numbers using Twilio integration.

- **Professional Bank Accounts**: 
  - Create and manage professional bank accounts with auto-generated account numbers and secure PIN codes.

- **Document Management**: 
  - Users can upload and store documents, such as identification cards.

- **Appointment Scheduling**: 
  - Schedule meetings with bank representatives.

- **Admin Approval**: 
  - Admins approve user account requests. Upon approval, a user account, bank account, and associated documents are created.

- **Spring Cloud Gateway**: 
  - Centralized routing and filtering for microservices.

## **Tech Stack**

- **Backend**:
  - Spring Boot (Java)
  - Spring Data JPA
  - Spring Security
  - Spring Cloud Gateway
  - OpenFeign (for inter-service communication)

- **Frontend**:
  - Angular 17

- **Database**:
  - PostgreSQL

- **API Documentation**:
  - Swagger (for easy API exploration)

- **SMS Integration**:
  - Twilio (for OTP verification)

- **Version Control**:
  - Git/GitHub

Here’s a properly formatted version for the **Installation and Setup** section of your README:

---

## **Installation and Setup**

### **Clone the Repository:**

```bash
git clone https://github.com/yourusername/bfi-bank.git
cd bfi-bank
```

### **Backend Setup:**

1. Navigate to the Spring Boot application directory.
2. Update the `application.properties` or `application.yml` file with your local database, email service, and Twilio credentials.
3. Run the application:

```bash
./mvnw spring-boot:run
```

### **Frontend Setup:**

1. Navigate to the Angular project directory.
2. Install the necessary dependencies:

```bash
npm install
```

3. Run the Angular application:

```bash
ng serve
```

### **Database Setup:**

1. Ensure you have **PostgreSQL** running locally.
2. Create the necessary databases as defined in your `application.properties` file.
3. The database schema will be automatically generated using **Spring Data JPA**.

### **Twilio Configuration:**

1. Sign up on **Twilio** and get your account credentials (SID, Auth Token, and phone number).
2. Add these credentials to your `application.properties` file.

### **Email Configuration:**

1. Set up your email service (e.g., Gmail) in the `application.properties` file for sending verification tokens.


