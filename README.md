
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

