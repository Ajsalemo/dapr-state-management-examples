package createState

import (
	"bytes"
	"encoding/json"
	"fmt"
	"log"
	"net/http"
	"io"

	"github.com/google/uuid"
)

func CreateStateController(w http.ResponseWriter, r *http.Request) {
	uuid := uuid.New()
	// Create these structs to mimic the shape of the incoming POST request
	// So we can marshal this struct into JSON
	type OrderData struct {
		OrderId string `json:"orderId"`
	}

	type Order struct {
		Data OrderData `json:"data"`
	}

	type State struct {
		Key   string `json:"key"`
		Value Order `json:"value"`
	}

	requestBody, err := io.ReadAll(r.Body)
	if err != nil {
		log.Fatal(err)
	}
	// Give the "Key" a default value of a uuid
	state := []State{
		{
			Key: uuid.String(),
		},
	}
	// Unmarshal the request body into the struct
	json.Unmarshal(requestBody, &state[0].Value)
	// Marshal the Struct Array into JSON to send in the POST request to DApr
	t, err := json.Marshal(state)
	// If there is an error marhsalling state, return it
	if err != nil {
		log.Fatal(err)
	}
	res, err := http.Post("http://localhost:3500/v1.0/state/statestore", "application/json", bytes.NewBuffer(t))

	if err != nil {
		log.Fatal(err)
	}

	defer res.Body.Close()

	m := map[string]string{"msg": fmt.Sprintf("Order created with ID: %v", uuid.String())}
	e := json.NewEncoder(w)
	e.Encode(m)
}
