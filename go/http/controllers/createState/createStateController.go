package createState

import (
	"encoding/json"
	"fmt"
	"io/ioutil"
	"log"
	"net/http"

	"github.com/google/uuid"
)

func CreateStateController(w http.ResponseWriter, r *http.Request) {
	uuid := uuid.New()
	// msg := fmt.Sprintf("Created order with Order ID: %v", uuid)

	// res, err := http.NewRequest(http.MethodPost, "http://localhost:3500/v1.0/state/statestore")
	// if err != nil {
	// 	log.Fatal(err)
	// }
	// Create these structs to mimic the shape of the incoming POST request
	// So we can marshal this struct into JSON
	type Order struct {
		OrderId string `json:"orderId"`
	}

	type State struct {
		Key string `json:"key"`
		Data Order `json:"data"`
	}
	// Initialize State with a default key uuid 
	state := &State{
		Key: uuid.String(),
	}
	// Push the struct into an array
	s := []State{}
	a := append(s, *state)
	requestBody, err := ioutil.ReadAll(r.Body)

	json.Unmarshal([]byte(requestBody), &a)

	if err != nil {
		log.Fatal(err)
	}	

	o, _ := json.Marshal(a)
	fmt.Println(string(o))
	
	m := map[string]string{"msg": fmt.Sprintf("Order created with ID: %v", uuid.String())}
	e := json.NewEncoder(w)
	e.Encode(m)
}
