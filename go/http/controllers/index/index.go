package index

import (
	"encoding/json"
	"net/http"
)

func IndexController(w http.ResponseWriter, r *http.Request) {
	m := map[string]string{"msg": "dapr-state-management-examples-go-http"}
	e := json.NewEncoder(w)
	e.Encode(m)
}