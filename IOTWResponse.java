import java.io.Serializable;

public class IOTWResponse implements Serializable {
    private String Response;

    public String getResponse() {
        return Response;
    }

    public void setResponse(String response) {
        Response = response;
    }
}
