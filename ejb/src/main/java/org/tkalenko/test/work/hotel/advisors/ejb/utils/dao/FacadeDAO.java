package org.tkalenko.test.work.hotel.advisors.ejb.utils.dao;

public interface FacadeDAO {

    ContactDAO getContactDAO(final int type) throws IllegalArgumentException;

    AddressDAO getAddressDAO(final int type) throws IllegalArgumentException;

}
