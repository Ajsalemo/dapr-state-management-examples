package constants

// Create these structs to mimic the shape of the incoming GET request
// So we can marshal this struct into JSON
type OrderData struct {
	OrderId string `json:"orderId"`
}

type Order struct {
	Data OrderData `json:"data"`
}

type State struct {
	Key   string `json:"key"`
	Value Order  `json:"value"`
}

var StateStruct = &State{}