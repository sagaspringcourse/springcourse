package businessobject;

import java.util.Date;

public class Request implements IRequest {
    private Date startAt;
    private Date endAt;
    private String pet;

    public Request(Date startAt, Date endAt, String pet) {
        this.startAt = startAt;
        this.endAt = endAt;
        this.pet = pet;
    }

    @Override
    public Date getStartAt() {
        return startAt;
    }

    @Override
    public Date getEndAt() {
        return endAt;
    }

    @Override
    public String getPet() {
        return pet;
    }
}
