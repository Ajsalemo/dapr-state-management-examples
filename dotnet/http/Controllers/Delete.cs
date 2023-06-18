using Microsoft.AspNetCore.Mvc;

namespace http.Controllers;

[ApiController]
[Route("order/[controller]")]
public class DeleteController : ControllerBase
{    
    [HttpDelete("{uuid}")]
    public String Delete(string uuid)
    {
        return "DELETE /order/delete/" + uuid;
    }
}
