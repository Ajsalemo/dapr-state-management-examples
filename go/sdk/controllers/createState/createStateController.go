package createState

import (
	"encoding/json"
	"fmt"
	"log"
	"net/http"
	"io"
	"context"

	"github.com/google/uuid"
	constants "github.com/azureossd/dapr-state-management-examples/go/sdk/constants"
	d "github.com/azureossd/dapr-state-management-examples/go/sdk/dapr"
)

func CreateStateController(w http.ResponseWriter, r *http.Request) {
	uuid := uuid.New()
	requestBody, err := io.ReadAll(r.Body)
	if err != nil {
		log.Fatal(err)
	}
	// Give the "Key" a default value of a uuid
	state := []constants.State{
		{
			Key: uuid.String(),
		},
	}
	// Unmarshal the request body into the struct
	json.Unmarshal(requestBody, &state[0].Value)
	// Marshal the Struct Array into JSON to send in the POST request to Dapr
	t, err := json.Marshal(state)
	// If there is an error marhsalling state, return it
	if err != nil {
		log.Fatal(err)
	}

	ctx := context.Background()
	// Create state with the Dapr SDK imported from the "d" package
	d.DaprClient().SaveState(ctx, "statestore", uuid.String(), t, nil)
	defer d.DaprClient().Close()

	m := map[string]string{"msg": fmt.Sprintf("Order created with ID: %v", uuid.String())}
	e := json.NewEncoder(w)
	e.Encode(m)
}
