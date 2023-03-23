#!bin/bash

az deployment group create \
  --resource-group "$RESOURCE_GROUP" \
  --template-file ./deployment.json \
  --parameters \
      environment_name="$CONTAINERAPPS_ENVIRONMENT" \
      location="$LOCATION" \
      azureContainerRegistryServerUrl="$AZURE_CONTAINER_REGISTRY_SERVER_URL" \
      azureContainerRegistryPassword="$AZURE_CONTAINER_REGISTRY_PASSWORD" \
      azureContainerRegistryUsername="$AZURE_CONTAINER_REGISTRY_USERNAME" \
      azureContainerRegistryImageName="$AZURE_CONTAINER_REGISTRY_IMAGE_NAME" \
      azureContainerRegistryImageTag="$AZURE_CONTAINER_REGISTRY_IMAGE_TAG" \
      storageAccountKey="$STORAGE_ACCOUNT_KEY" \
      storageAccountName="$STORAGE_ACCOUNT_NAME" \
      storageAccountContainer="$STORAGE_ACCOUNT_CONTAINER" 

