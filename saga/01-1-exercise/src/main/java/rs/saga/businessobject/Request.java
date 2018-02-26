package rs.saga.businessobject;

import java.util.Date;

public class Request {
    private Date startAt;
    private Date endAt;
    private String pet;

    public Request(Date startAt, Date endAt, String pet) {
        this.startAt = startAt;
        this.endAt = endAt;
        this.pet = pet;
    }

    public Date getStartAt() {
        return startAt;
    }

    public Date getEndAt() {
        return endAt;
    }

    public String getPet() {
        return pet;
    }
}
