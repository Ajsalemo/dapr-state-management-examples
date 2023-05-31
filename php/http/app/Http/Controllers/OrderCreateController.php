<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use Illuminate\Support\Facades\Http;
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
        $d = Http::post('http://localhost:3500/v1.0/state/statestore', $structuredArr);
        $msg = "Order created with ID: " . $uuid;

        return $msg;
    }
}
