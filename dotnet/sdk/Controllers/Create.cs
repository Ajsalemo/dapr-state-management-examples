using System.Text;
using System.Text.Json;
using Dapr.Client;
using Microsoft.AspNetCore.Mvc;

namespace sdk.Controllers;

[ApiController]
[Route("order/[controller]")]
public class CreateController : ControllerBase
{
    private readonly DaprClient _daprClient;

    public CreateController(DaprClient daprClient)
    {
        _daprClient = daprClient;
    }

    [HttpPost(Name = "OrderCreate")]
    public async Task<string> PostAsync()
    {
        try
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
            await _daprClient.SaveStateAsync("statestore", g.ToString(), stateArray);

            string message = "Order created with ID: " + g.ToString();
            return message;

        }
        catch (Exception ex)
        {
            Console.WriteLine(ex.ToString());
            return ex.ToString();
        }
    }
}
