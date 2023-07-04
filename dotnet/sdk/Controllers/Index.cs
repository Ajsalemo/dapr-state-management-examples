using Microsoft.AspNetCore.Mvc;

namespace sdk.Controllers;

[ApiController]
[Route("/")]
public class IndexController : ControllerBase
{
    [HttpGet]
    public String Get()
    {
        string msg = "dapr-state-management-examples-dotnet-sdk";
        return msg;
    }
}