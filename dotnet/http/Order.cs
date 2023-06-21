namespace http;

public class Order
{
    public Values? Data { get; set; }

    public static implicit operator Order(string v)
    {
        throw new NotImplementedException();
    }
}