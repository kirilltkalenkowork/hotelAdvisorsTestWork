package org.tkalenko.test.work.hotel.advisors.hibernate.entity;

public class Address {
    private long id;
    private String city;
    private String street;
    private String house;
    private String zip;
    private String flat;
    private long contactId;

    public Address() {
    }

    public long getId() {
        return this.id;
    }

    public void setId(final long id) {
        this.id = id;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(final String city) {
        this.city = city;
    }

    public String getStreet() {
        return this.street;
    }

    public void setStreet(final String street) {
        this.street = street;
    }

    public String getHouse() {
        return this.house;
    }

    public void setHouse(final String house) {
        this.house = house;
    }

    public String getZip() {
        return this.zip;
    }

    public void setZip(final String zip) {
        this.zip = zip;
    }

    public String getFlat() {
        return this.flat;
    }

    public void setFlat(final String flat) {
        this.flat = flat;
    }

    public long getContactId() {
        return this.contactId;
    }

    public void setContactId(final long contactId) {
        this.contactId = contactId;
    }
}
