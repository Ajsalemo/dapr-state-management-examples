package getState

import (
	"encoding/json"
	"fmt"
	"io"
	"log"
	"net/http"
	"strings"
)

func GetStateController(w http.ResponseWriter, r *http.Request) {
	// Create these structs to mimic the shape of the incoming GET request
	// So we can marshal this struct into JSON
	type OrderData struct {
		OrderId string `json:"orderId"`
	}

	type Order struct {
		Data OrderData `json:"data"`
	}

	type State struct {
		Key   string `json:"key"`
		Value Order  `json:"value"`
	}

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

	state := []State{
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
