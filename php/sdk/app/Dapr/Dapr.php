<?php

namespace App\Dapr;

use Dapr\Client\DaprClient;

class Dapr 
{
    public function customDaprClientBuilder() 
    {
        $daprClient = DaprClient::clientBuilder()->build();
        return $daprClient;
    }
}
