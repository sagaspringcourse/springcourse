package businessobject;

import org.springframework.stereotype.Component;

@Component("response")
public class Response implements IResponse {
    private String user;

    public Response() {
        this.user = "John";
    }

    public Response(String user) {
        this.user = user;
    }

    @Override
    public String getUser() {
        return user;
    }
}
