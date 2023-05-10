package createState

import (
	"bytes"
	"encoding/json"
	"fmt"
	"io"
	"log"
	"net/http"

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
		Key  string `json:"key"`
		Value Order `json:"value"`
	}

	requestBody, err := io.ReadAll(r.Body)

	if err != nil {
		log.Fatal(err)
	}

	var s State
	s.Key = uuid.String()
	//json.Unmarshal([]byte(requestBody), &s)

	if err != nil {
		log.Fatal(err)
	}
 
	a := []State{}
	j := append(a, s)

	o, _ := json.Marshal(j)

	res, err := http.Post("http://localhost:3500/v1.0/state/statestore", "application/json; charset=utf-8", bytes.NewBufferString(string(o)))
	if err != nil {
		log.Fatal(err)
	}

	defer res.Body.Close()

	fmt.Println(string(requestBody))
	fmt.Println(string(o))
	fmt.Println(s)
	fmt.Println(a)
	fmt.Println(j)


	m := map[string]string{"msg": fmt.Sprintf("Order created with ID: %v", uuid.String())}
	e := json.NewEncoder(w)
	e.Encode(m)
}
