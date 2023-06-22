using System.Collections;
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
        string content = await new StreamReader(Request.Body).ReadToEndAsync();
        var state = new State
        {
            Value = content
        };

        List<State> list = new List<State>();
        list.Add(state);

        var json = JsonSerializer.Serialize(list);

        var httpClient = _httpClientFactory.CreateClient("daprClient");
        using var httpPostMessage = await httpClient.PostAsync("", new StringContent(json));
        Console.WriteLine(httpPostMessage);

        return "POST /order/create";
    }
}
