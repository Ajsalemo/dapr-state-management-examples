package dapr

import (
	dapr "github.com/dapr/go-sdk/client"
)

func DaprClient() dapr.Client {
	client, err := dapr.NewClient()
	if err != nil {
		panic(err)
	}

	return client
}


