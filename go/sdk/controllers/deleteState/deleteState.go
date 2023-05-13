package deleteState

import (
	"encoding/json"
	"fmt"
	"log"
	"net/http"
	"strings"
)

func DeleteStateController(w http.ResponseWriter, r *http.Request) {
	uuid := strings.TrimPrefix(r.URL.Path, "/order/delete/")
	deleteStateUrl := fmt.Sprintf("http://localhost:3500/v1.0/state/statestore/%v", uuid)
	// There is no high-level "net/http" API for DELETE, so we need to use an alternative route
	req, err := http.NewRequest(http.MethodDelete, deleteStateUrl, nil)

	if err != nil {
		log.Fatal(err)
	}
	// Contruct a client to execute the DELETE call
	client := &http.Client{}
    res, err := client.Do(req)
    if err != nil {
        log.Fatalln(err)
    }

	defer res.Body.Close()
	m := fmt.Sprintf("Deleted order with Order ID: %v", uuid)

	e := json.NewEncoder(w)
	e.Encode(m)
}
