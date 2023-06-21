using System.Collections;
using System.Text.Json;
using Microsoft.AspNetCore.Mvc;

namespace http.Controllers;

[ApiController]
[Route("order/[controller]")]
public class CreateController : ControllerBase
{
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

        foreach (var item in list)
        {
            Console.WriteLine("[{0}]", string.Join(", ", item.Value));
        }
        return "POST /order/create";
    }
}
