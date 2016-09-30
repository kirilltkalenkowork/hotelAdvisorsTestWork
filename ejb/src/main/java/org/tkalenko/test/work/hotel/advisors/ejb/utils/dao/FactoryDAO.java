package org.tkalenko.test.work.hotel.advisors.ejb.utils.dao;

public interface FactoryDAO {

    ContactDAO getContactDAO();

    AddressDAO getAddressDAO();

}
