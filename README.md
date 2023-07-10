# dapr-state-management-examples

This repo contains examples of how to use Dapr and the State Management component through the HTTP API and language specific SDK's.

This example is configured to use Azure Blob Storage for it's state management.

**How to run and use these examples**:
- **Prerequisites**: An already existing Azure Storage Account and Storage Container - as well as an Azure Container Registry service is expected.

- **Local**:
    - To run this on a local k8s cluster - you must have Dapr installed into that cluster
    - Build the image with the `Dockerfile` present in the example you want to run
    - Change the values of `accountName` (Storage Account Name), `accountKey` (Storage Account Key), `containerName` (Storage Container Name) in the `statestore.yaml` file in the example you want to run
    - Run `kubectl apply -f statestore.yaml` and then `kubectl apply -f k8s.yaml` to apply the state store component to the cluster and then create the deployment for k8s with your application
    - You can then access the example over `localhost:[nodePort]` which is specified in `k8s.yaml`

- **Deploying to Azure**:
    - Build the image with the `Dockerfile` present in the example you want to run
    - Set the required environment variables in `arm-deploy-command.sh`. If using Bash, do this by `export FOO="bar"` or in a Windows based terminal, via `set FOO="bar"`
    - Required environment variables are:
        - `RESOURCE_GROUP`: Resource Group to deploy to
        - `LOCATION`: Region the Resource Group is in
        - `CONTAINERAPPS_ENVIRONMENT`: Container App Environment name to create or deploy to (if it exists)
        - `AZURE_CONTAINER_REGISTRY_SERVER_URL`: Format of "myacr.azurecr.io"
        - `AZURE_CONTAINER_REGISTRY_PASSWORD`: Your ACR password
        - `AZURE_CONTAINER_REGISTRY_USERNAME`: Your ACR username
        - `AZURE_CONTAINER_REGISTRY_IMAGE_NAME`: The name of the image you're deploying, eg. "daprdotnet"
        - `AZURE_CONTAINER_REGISTRY_IMAGE_TAG`: Tag of the image you're deploying, eg. "latest"
        - `STORAGE_ACCOUNT_KEY`: Your Storage Account key
        - `STORAGE_ACCOUNT_NAME`: Your Storage Account name
        - `STORAGE_ACCOUNT_CONTAINER`: Your Storage Account Container name

**Endpoints**:
- `/order/create` - To create state, make a **POST** request with the following body (this includes the outer brackets):

```json
{
    "data": {
        "orderId": "someOrderId"
    }
}
```

- `/order/get/00000000-0000-0000-0000-000000000000` - Get state with the UID from the created state. This endpoint accepts a **GET** request. Eg., `/order/get/5ed162f4-c3c6-45d6-8445-2d325660fa4d`

- `/order/delete/00000000-0000-0000-0000-000000000000` - Delete state with the UID from the created state. This endpoint accepts a **DELETE** HTTP verb. Eg., `/order/delete/5ed162f4-c3c6-45d6-8445-2d325660fa4d`