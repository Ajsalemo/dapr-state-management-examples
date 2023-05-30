<?php

namespace App\Http\Controllers;
use Illuminate\Http\Request;
use Illuminate\Support\Str;
use Ramsey\Uuid\Rfc4122\UuidV4;

class OrderCreateController extends Controller
{
    public function orderCreate(Request $request) {
        $uuid = Str::uuid();
        $res = $request->all();
        $value = array('key' => $uuid, 'value' => $res);

        $structuredArray = array();
        array_push($structuredArray, $value);
        
        $json = json_encode($structuredArray);
        return $json;
    }
}
