using Microsoft.AspNetCore.Mvc;
using Dapr.Client;

namespace sdk.Controllers;

[ApiController]
[Route("order/[controller]")]
public class DeleteController : ControllerBase
{
    private readonly DaprClient _daprClient;

    public DeleteController(DaprClient daprClient)
    {
        _daprClient = daprClient;
    }

    [HttpDelete("{uuid}")]
    public async Task<string?> Delete(string uuid)
    {
        try
        {
            await _daprClient.DeleteStateAsync("statestore", uuid);

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
