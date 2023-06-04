<?php

namespace App\Http\Controllers;
use Illuminate\Support\Facades\Http;

class OrderDeleteController extends Controller
{
    public function orderDelete(string $uuid) {
        Http::delete('http://localhost:3500/v1.0/state/statestore/' . $uuid);

        $msg = "Order deleted with Order ID: " . $uuid;
        return $msg;
    }
}
