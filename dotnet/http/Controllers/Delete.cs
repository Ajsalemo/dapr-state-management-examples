using Microsoft.AspNetCore.Mvc;

namespace http.Controllers;

[ApiController]
[Route("order/[controller]")]
public class DeleteController : ControllerBase
{
    private readonly IHttpClientFactory _httpClientFactory;

    public DeleteController(IHttpClientFactory httpClientFactory) =>
        _httpClientFactory = httpClientFactory;


    [HttpDelete("{uuid}")]
    public async Task<string?> Delete(string uuid)
    {
        try
        {
            var httpClient = _httpClientFactory.CreateClient("daprClient");
            using var httpGetMessage = await httpClient.DeleteAsync(uuid);

            string message = "Deleted order with Order ID: " + uuid;

            return message;

        }
        catch (Exception ex)
        {
            Console.WriteLine(ex.ToString());
            return ex.ToString();
        }
    }
}
