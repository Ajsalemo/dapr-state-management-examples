using System.Collections;
using System.Text;
using System.Text.Json;
using Microsoft.AspNetCore.Mvc;

namespace http.Controllers;

[ApiController]
[Route("order/[controller]")]
public class CreateController : ControllerBase
{
    private readonly IHttpClientFactory _httpClientFactory;

    public CreateController(IHttpClientFactory httpClientFactory) =>
        _httpClientFactory = httpClientFactory;

    [HttpPost(Name = "OrderCreate")]
    public async Task<string> PostAsync()
    {
        Guid g = Guid.NewGuid();
        string content = await new StreamReader(Request.Body).ReadToEndAsync();

        // Deserialize content into State - or else we'll get JSON serialization formatting issues with newlines 
        var j = JsonSerializer.Deserialize<Order>(content);
        var state = new State
        {
            key = g.ToString(),
            value = j
        };

        // Add state to a list and convert it to an Array
        List<State> arr = new List<State>();
        arr.Add(state);
        State[] stateArray = arr.ToArray<State>();
        // Serealize the array to JSON and pass this into StringContent so it can be POSTed to the dapr sidecar
        var json = JsonSerializer.Serialize(stateArray);
        var data = new StringContent(json, Encoding.UTF8, "application/json");

        var httpClient = _httpClientFactory.CreateClient("daprClient");
        using var httpPostMessage = await httpClient.PostAsync("", data);

        string message = "Order created with ID: " + g.ToString();

        return message;
    }
}
