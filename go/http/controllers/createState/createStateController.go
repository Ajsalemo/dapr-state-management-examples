package createState

import (
	"bytes"
	"encoding/json"
	"fmt"
	"io/ioutil"
	"log"
	"net/http"

	"github.com/google/uuid"
)

func CreateStateController(w http.ResponseWriter, r *http.Request) {
	uuid := uuid.New()
	// Create these structs to mimic the shape of the incoming POST request
	// So we can marshal this struct into JSON
	type Order struct {
		OrderId string `json:"orderId"`
	}

	type State struct {
		Key  string `json:"key"`
		Data string  `json:"data"`
	}
	// Push the struct into an array
	s := []State{}
	requestBody, err := ioutil.ReadAll(r.Body)

	// Initialize State with a default key uuid
	state := &State{
		Key: uuid.String(),
		Data: string(requestBody),
	}
	a := append(s, *state)
	json.Unmarshal([]byte(requestBody), &a)

	if err != nil {
		log.Fatal(err)
	}

	o, _ := json.Marshal(a)
	res, err := http.Post("http://localhost:3500/v1.0/state/statestore", "application/json", bytes.NewBuffer(o))
	if err != nil {
		log.Fatal(err)
	}

	defer res.Body.Close()

	fmt.Println(a)
	fmt.Println(string(requestBody))

	m := map[string]string{"msg": fmt.Sprintf("Order created with ID: %v", uuid.String())}
	e := json.NewEncoder(w)
	e.Encode(m)
}
