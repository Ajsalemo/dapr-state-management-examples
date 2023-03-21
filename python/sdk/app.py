from flask import Flask, jsonify, request
import uuid, json
from dapr.clients import DaprClient

app = Flask(__name__)

dapr_state_store = "statestore"
d = DaprClient()

@app.route("/")
def index():
    return jsonify({ "msg": "dapr-state-management-examples-python-sdk" })


@app.route("/order/create", methods=['POST'])
def create_order():
    data = request.json
    id = str(uuid.uuid4())
    s = [
            {
                "key": id,
                "value": data 
            }
        ]
    
    try:
        d.save_state(dapr_state_store, key=id, value=json.dumps(s))

        return jsonify({ "msg": f"Created order with Order ID: {id}" })
    except Exception as e:
        return jsonify({ "error": str(e) })
    

@app.route("/order/get/<string:id>")
def get_order(id):
    try:
        r = d.get_state(dapr_state_store, key=id)
        
        return jsonify({ "orders": r.json() })
    except Exception as e:
        print(e)
        return jsonify({ "error": str(e) })
    

@app.route("/order/delete/<string:id>")
def delete_order(id):
    try:
        r = d.delete_state(dapr_state_store, key=id)
        
        return jsonify({ "msg": f"Deleted order with Order ID: {id}" })
    except Exception as e:
        print(e)
        return jsonify({ "error": str(e) })

