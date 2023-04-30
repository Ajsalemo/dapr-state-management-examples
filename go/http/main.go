package main

import (
	"net/http"
	"log"

	index "github.com/azureossd/dapr-state-management-examples/go/http/controllers/index"
	createState "github.com/azureossd/dapr-state-management-examples/go/http/controllers/createState" 
)

func main() {
	http.HandleFunc("/", index.IndexController)
	http.HandleFunc("/order/create", createState.CreateStateController)

	log.Println("Server listening on port 8080.")
	err := http.ListenAndServe(":8080", nil)

	if err != nil {
		log.Fatal(err)
	}
}