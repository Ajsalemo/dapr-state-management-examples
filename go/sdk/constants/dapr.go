package constants

import (
	"context"

	dapr "github.com/dapr/go-sdk/client"
)

func d() (dapr.Client) {
	client, err := dapr.NewClient()
	if err != nil {
		panic(err)
	}

	defer client.Close()
	return client
}


func DaprCreateState(uuid string, t []byte) {
	ctx := context.Background()
	if err := d().SaveState(ctx, "statestore", uuid, t, nil); err != nil {
		panic(err)
	}
}