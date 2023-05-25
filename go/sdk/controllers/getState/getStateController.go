package getState

import (
	"context"
	"encoding/json"
	"fmt"
	"net/http"
	"strings"

	constants "github.com/azureossd/dapr-state-management-examples/go/sdk/constants"
	d "github.com/azureossd/dapr-state-management-examples/go/sdk/dapr"
)

func GetStateController(w http.ResponseWriter, r *http.Request) {
	uuid := strings.TrimPrefix(r.URL.Path, "/order/get/")

	state := []constants.State{}

	ctx := context.Background()
	s, err := d.DaprClient().GetState(ctx, "statestore", uuid, nil)

	if err != nil {
		panic(err)
	}

	defer d.DaprClient().Close()
	// Unmarshal the request body into the struct
	json.Unmarshal(s.Value, &state[0])
	fmt.Println(string(s.Value)) 
	e := json.NewEncoder(w)
	e.Encode(s.Value)
}
