package rs.saga.businessobject;

public class Response implements IResponse {
    private String user;

    public Response(String user) {
        this.user = user;
    }

    @Override
    public String getUser() {
        return user;
    }
}
