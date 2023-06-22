using Microsoft.AspNetCore.Mvc;

namespace http.Controllers;

[ApiController]
[Route("/")]
public class IndexController : ControllerBase
{
    [HttpGet]
    public String Get()
    {
        string msg = "dapr-state-management-examples-dotnet-http";
        return msg;
    }
}