<?php

namespace App\Http\Controllers;

use App\Dapr\Dapr;
use Dapr\consistency\Consistency;

class OrderDeleteController extends Controller
{
    public function orderDelete(string $uuid) {
        $d = new Dapr();
        $d->customDaprClientBuilder()->deleteState("statestore", $uuid);

        $msg = "Order deleted with Order ID: " . $uuid;
        return $msg;
    }

}
