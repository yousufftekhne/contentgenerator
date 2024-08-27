# Marketing Content Generator Backend API

This is a backend API for generating marketing content, built using Spring Boot and integrated with Azure OpenAI.

## Features

- Generate marketing content using Azure OpenAI GPT models.
- Backend API for content generation, customizable for different marketing needs.

## Prerequisites

- Java 17
- Maven
- Azure account with OpenAI deployed
- Azure OpenAI API Key and Endpoint

## Getting Started

1. **Clone the Repository**

   ```bash
   git clone https://github.com/terawe/contentgenerator.git
   cd middleware
   ```
   
2. **Configure Azure OpenAI**

    * Go to [Azure OpenAI](https://ai.azure.com) and deploy the GPT model.
    * Retrieve your **Azure OpenAI Endpoint URL** from your OpenAI service.
    * Retrieve your **Azure OpenAI API Key** from the Azure portal.

3. **Update Application Properties**
   
   Open the `application.properties` file ( path `src/main/resources/application.properties`) and update the following values:
   
    ```properties
   azure.openai.endpoint_url=<Your Azure OpenAI Endpoint URL>
   azure.openai.key=<Your Azure OpenAI API Key>
   ```

4. **Run the Application**

   Use Maven to run the application:

    ```bash
    ./mvnw spring-boot:run
   ```
