<?php

namespace App\Http\Controllers;

use App\Dapr\Dapr;
use Dapr\consistency\Consistency;

class OrderDeleteController extends Controller
{
    public function orderDelete(string $uuid) {
        $d = new Dapr();
        // See this: https://github.com/dapr/php-sdk/issues/131
        // Ideally, deleteState() would be used
        $d->customDaprClientBuilder()->tryDeleteState("statestore", $uuid, $uuid);

        $msg = "Order deleted with Order ID: " . $uuid;
        return $msg;
    }

}
