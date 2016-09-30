package org.tkalenko.test.work.hotel.advisors.hibernate.utils.dao;

public interface FactoryDAO {

    ContactDAO getContactDAO();

    AddressDAO getAddressDAO();

}
