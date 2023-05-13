package main

import (
	"net/http"
	"log"

	index "github.com/azureossd/dapr-state-management-examples/go/sdk/controllers/index"
	createState "github.com/azureossd/dapr-state-management-examples/go/sdk/controllers/createState"
	getState "github.com/azureossd/dapr-state-management-examples/go/sdk/controllers/getState"  
	deleteState "github.com/azureossd/dapr-state-management-examples/go/sdk/controllers/deleteState"  
)

func main() {
	http.HandleFunc("/", index.IndexController)
	http.HandleFunc("/order/create", createState.CreateStateController)
	http.HandleFunc("/order/get/", getState.GetStateController)
	http.HandleFunc("/order/delete/", deleteState.DeleteStateController)

	log.Println("Server listening on port 8080.")
	err := http.ListenAndServe(":8080", nil)

	if err != nil {
		log.Fatal(err)
	}
}