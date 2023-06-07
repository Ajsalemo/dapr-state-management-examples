<?php

namespace App\Http\Controllers;

use App\Dapr\Dapr;
use Illuminate\Http\Request;
use Illuminate\Support\Str;

class OrderCreateController extends Controller
{
    public function orderCreate(Request $request)
    {
        $uuid = Str::uuid();
        $res = $request->all();

        $structuredArr = array();
        $value = array('key' => $uuid, 'value' => $res);
        array_push($structuredArr, $value);

        // Make a POST request to the Dapr sidecar
        $d = new Dapr();
        $d->customDaprClientBuilder()->saveStateAsync("statestore", $uuid, $structuredArr);
        $msg = "Order created with ID: " . $uuid;

        return $msg;
    }
}
