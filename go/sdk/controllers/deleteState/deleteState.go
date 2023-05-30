package deleteState

import (
	"context"
	"encoding/json"
	"fmt"
	"net/http"
	"strings"

	d "github.com/azureossd/dapr-state-management-examples/go/sdk/dapr"
)

func DeleteStateController(w http.ResponseWriter, r *http.Request) {
	uuid := strings.TrimPrefix(r.URL.Path, "/order/delete/")
	ctx := context.Background()
	if err := d.DaprClient().DeleteState(ctx, "statestore", uuid, nil); err != nil {
		panic(err)
	}

	defer d.DaprClient().Close()
	m := fmt.Sprintf("Deleted order with Order ID: %v", uuid)

	e := json.NewEncoder(w)
	e.Encode(m)
}
