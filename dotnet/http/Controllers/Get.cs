using Microsoft.AspNetCore.Mvc;
using System.Text.Json;

namespace http.Controllers;

[ApiController]
[Route("order/[controller]")]
public class GetController : ControllerBase
{
    private readonly IHttpClientFactory _httpClientFactory;

    public GetController(IHttpClientFactory httpClientFactory) =>
        _httpClientFactory = httpClientFactory;

    [HttpGet("{uuid}")]
    public async Task<string?> GetAsync(string uuid)
    {
        try
        {
            var httpClient = _httpClientFactory.CreateClient("daprClient");
            using var httpGetMessage = await httpClient.GetAsync(uuid);

            var jsonResponse = await httpGetMessage.Content.ReadAsStringAsync();
            var j = JsonSerializer.Deserialize<Order>(jsonResponse);

            var state = new State
            {
                key = uuid,
                value = j
            };

            List<State> arr = new List<State>();
            arr.Add(state);
            State[] stateArray = arr.ToArray<State>();
            // Serialize the array to JSON
            var json = JsonSerializer.Serialize(stateArray);

            return json;
        }
        catch (Exception ex)
        {
            Console.WriteLine(ex.ToString());
            return ex.ToString();
        }
    }
}