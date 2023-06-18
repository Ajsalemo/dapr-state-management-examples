using Microsoft.AspNetCore.Mvc;

namespace http.Controllers;

[ApiController]
[Route("order/[controller]")]
public class CreateController : ControllerBase
{    
    [HttpPost(Name = "OrderCreate")]
    public String Post(State state)
    {
        return "POST /order/create";
    }
}
