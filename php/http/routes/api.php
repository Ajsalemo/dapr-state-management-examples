<?php

use App\Http\Controllers\OrderCreateController;
use App\Http\Controllers\OrderDeleteController;
use App\Http\Controllers\OrderGetController;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Route;

/*
|--------------------------------------------------------------------------
| API Routes
|--------------------------------------------------------------------------
|
| Here is where you can register API routes for your application. These
| routes are loaded by the RouteServiceProvider within a group which
| is assigned the "api" middleware group. Enjoy building your API!
|
*/

Route::middleware('auth:sanctum')->get('/user', function (Request $request) {
    return $request->user();
});

Route::group(['prefix' => 'order', 'as' => 'order.'], function () {
    Route::post('/create', ['as' => 'create', OrderCreateController::class, 'orderCreate']);
    Route::get('/get/{uuid}', ['as' => 'get', OrderGetController::class, 'orderGet']);
    Route::delete('/delete/{uuid}', ['as' => 'delete', OrderDeleteController::class, 'orderDelete']);
});
