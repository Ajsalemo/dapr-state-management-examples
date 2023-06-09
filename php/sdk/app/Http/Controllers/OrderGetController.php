<?php

namespace App\Http\Controllers;

use App\Dapr\Dapr;

class OrderGetController extends Controller
{
    public function orderGet(string $uuid) {
        $d = new Dapr();
        $r = $d->customDaprClientBuilder()->getState("statestore", $uuid);

        $j = json_decode(print_r($r->data));
        return $j;
    }
}
