package rs.saga.domain;

import javax.persistence.Column;

/**
 * @author <a href="mailto:slavisa.avramovic@escriba.de">avramovics</a>
 * @since 2018-03-01
 */
public class Address {


    @Column(name = "ADDRESS")
    private String address;

    public Address() {

    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
