<?php

namespace App\Http\Controllers;
use Illuminate\Support\Facades\Http;

class OrderGetController extends Controller
{
    public function orderGet(string $uuid) {
        $res = Http::get('http://localhost:3500/v1.0/state/statestore/' . $uuid);
        $arr = array();
        $value = array('key' => $uuid, 'value' => json_decode($res->body()));

        array_push($arr, $value);

        return $arr;
    }
}
