# Spring Boot Application

This Spring Boot application interacts with Azure Cosmos MongoDB to store critical data and Azure Blob Storage for media storage. It appends data to MongoDB based on a configuration and performs various file operations with Azure Blob Storage.

## Prerequisites

Before running the application, ensure that you have the following dependencies set up:

- Java 17 (Open JDK 22)
- Spring Boot
- MongoDB (Azure Cosmos DB with MongoDB API)
- Azure Blob Storage

## Configuration

The application requires certain configurations to be set in the `application.properties` file.

### 1. MongoDB Connection

You need to specify the connection string to your Azure Cosmos DB (with MongoDB API).

Add the following property in your `application.properties` file:

```properties
spring.data.mongodb.uri=<YOUR_COSMOS_DB_CONNECTION_STRING>
```

#### How to Get Azure Cosmos MongoDB Connection String

1. Go to your Azure Portal.
2. Navigate to your Azure Cosmos DB resource.
3. Under the **Settings** section, click on **Connection String**.
4. Copy the **Primary Connection String** and replace `<YOUR_COSMOS_DB_CONNECTION_STRING>` in the above configuration.

### 2. Azure Blob Storage Connection

Similarly, you need to add the connection string for your Azure Storage account.

```properties
azure.storage.connection-string=<YOUR_STORAGE_CONNECTION_STRING>
```

#### How to Get Azure Storage Account Connection String

1. Go to your Azure Portal.
2. Navigate to your Storage Account resource.
3. Under the **Security + Networking** section, click on **Access keys**.
4. Copy the **Connection String** for either key1 or key2 and replace `<YOUR_STORAGE_CONNECTION_STRING>` in the above configuration.

### 3. Data Append Configuration

The application has a feature to append data to MongoDB upon startup if a specific property is set to `true`. Add the following configuration:

```properties
data.append.enabled=true
```
* When `data.append.enabled=true`, the application will append data to MongoDB upon startup.
* If set to `false`, no data will be appended.

### Running the Application
Once you have added the required configurations in your `application.properties`, you can run the application using the following command:

```bash
./mvnw spring-boot:run
```

### Features

* **MongoDB Integration**: Stores and retrieves data from Azure Cosmos DB (MongoDB API).
* **Azure Blob Storage**: Handles file uploads and retrievals from Azure Blob Storage.
* **Data Append**: Automatically appends data to MongoDB on startup if the `data.append.enabled` property is `true`.
