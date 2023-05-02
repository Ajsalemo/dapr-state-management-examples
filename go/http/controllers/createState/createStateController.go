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

	type order struct {
		Key string `json:"key"`
		Value string `json:"value"`
	}

	requestBody, err := ioutil.ReadAll(r.Body)
	s := string(requestBody)
	if err != nil {
		log.Fatal(err)
	}

	state := []order{
		{
			Key: uuid.String(),
			Value: s,
		},
	}
	
	o, _ := json.Marshal(state)
	fmt.Println(string(o))

	m := map[string]string{"msg": "T"}
	e := json.NewEncoder(w)
	e.Encode(m)
}
