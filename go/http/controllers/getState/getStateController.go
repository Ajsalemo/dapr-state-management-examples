package getState

import (
	"encoding/json"
	"fmt"
	"io"
	"log"
	"net/http"
	"strings"

	constants "github.com/azureossd/dapr-state-management-examples/go/http/constants"
)

func GetStateController(w http.ResponseWriter, r *http.Request) {
	uuid := strings.TrimPrefix(r.URL.Path, "/order/get/")
	getStateUrl := fmt.Sprintf("http://localhost:3500/v1.0/state/statestore/%v", uuid)
	res, err := http.Get(getStateUrl)

	if err != nil {
		log.Fatal(err)
	}

	defer res.Body.Close()
	requestBody, err := io.ReadAll(res.Body)
	if err != nil {
		log.Fatal(err)
	}

	state := []constants.State{
		{
			Key: uuid,
		},
	}
	// Unmarshal the request body into the struct
	json.Unmarshal(requestBody, &state[0].Value)
	// If there is an error marhsalling state, return it
	if err != nil {
		log.Fatal(err)
	}

	e := json.NewEncoder(w)
	e.Encode(state)
}
