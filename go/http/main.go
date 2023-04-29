package main

import (
	"net/http"
	// This import will be changed as this project expands
	_ "github.com/dapr/go-sdk/client"
	"log"

	index "github.com/azureossd/dapr-state-management-examples/go/http/index"
)

func main() {
	http.HandleFunc("/", index.Index)

	log.Println("Server listening on port 8080.")
	err := http.ListenAndServe(":8080", nil)

	if err != nil {
		log.Fatal(err)
	}
}