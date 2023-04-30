package createState

import (
	"encoding/json"
	"fmt"
	"net/http"

	"github.com/google/uuid"
)

func CreateStateController(w http.ResponseWriter, r *http.Request) {
	uuid := uuid.New()
	msg := fmt.Sprintf("Created order with Order ID: %v", uuid)
	m := map[string]string{"msg": msg}
	e := json.NewEncoder(w)
	e.Encode(m)
}