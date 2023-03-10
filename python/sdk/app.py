from flask import Flask, jsonify, request
import uuid, json
import requests

app = Flask(__name__)

dapr_uri = "http://localhost:3500/v1.0/state"
dapr_state_store = "statestore"

@app.route("/")
def index():
    return jsonify({ "msg": "dapr-state-management-examples-python-http" })


@app.route("/order/create", methods=['POST'])
def create_order():
    data = request.json
    id = str(uuid.uuid4())
    headers = {'Content-Type': 'application/json'}
    s = [
            {
                "key": id,
                "value": data 
            }
        ]
    
    try:
        requests.post(f"{dapr_uri}/{dapr_state_store}", headers=headers, data=json.dumps(s))

        return jsonify({ "msg": f"Created order with Order ID: {id}" })
    except Exception as e:
        return jsonify({ "error": str(e) })
    

@app.route("/order/get/<string:id>")
def get_order(id):
    try:
        r = requests.get(f"{dapr_uri}/{dapr_state_store}/{id}")
        
        print(r.json())
        return jsonify({ "orders": r.json() })
    except Exception as e:
        print(e)
        return jsonify({ "error": str(e) })

