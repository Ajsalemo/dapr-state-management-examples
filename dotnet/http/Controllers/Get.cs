using Microsoft.AspNetCore.Mvc;

namespace http.Controllers;

[ApiController]
[Route("order/[controller]")]
public class GetController : ControllerBase
{
    private readonly IHttpClientFactory _httpClientFactory;

    public GetController(IHttpClientFactory httpClientFactory) =>
        _httpClientFactory = httpClientFactory;

    [HttpGet("{uuid}")]
    public async Task<string> GetAsync(string uuid)
    {
        var httpClient = _httpClientFactory.CreateClient("daprClient");
        using var httpGetMessage = await httpClient.GetAsync(uuid);
        Console.WriteLine(httpGetMessage.Content);
        Console.WriteLine(httpClient);
        return httpGetMessage.ToString();
    }
}