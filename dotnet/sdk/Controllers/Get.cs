using Microsoft.AspNetCore.Mvc;
using System.Text.Json;
using Dapr.Client;

namespace sdk.Controllers;

[ApiController]
[Route("order/[controller]")]
public class GetController : ControllerBase
{
    private readonly DaprClient _daprClient;

    public GetController(DaprClient daprClient)
    {
        _daprClient = daprClient;
    }

    [HttpGet("{uuid}")]
    public async Task<string?> GetAsync(string uuid)
    {
        try
        {
            // The "Type" of returned state is a State array
            var getStateWithDapr = await _daprClient.GetStateAsync<State[]>("statestore", uuid);
            var json = JsonSerializer.Serialize(getStateWithDapr);

            return json;
        }
        catch (Exception ex)
        {
            Console.WriteLine(ex.ToString());
            return ex.ToString();
        }
    }
}