using Microsoft.AspNetCore.Mvc;

namespace http.Controllers;

[ApiController]
[Route("order/[controller]")]
public class GetController : ControllerBase
{
    [HttpGet("{uuid}")]
    public String Get(string uuid)
    {
        return "GET /order/get/" + uuid;
    }
}