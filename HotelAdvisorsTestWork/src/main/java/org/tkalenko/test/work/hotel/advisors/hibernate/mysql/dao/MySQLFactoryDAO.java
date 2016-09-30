package org.tkalenko.test.work.hotel.advisors.hibernate.mysql.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tkalenko.test.work.hotel.advisors.hibernate.utils.dao.AddressDAO;
import org.tkalenko.test.work.hotel.advisors.hibernate.utils.dao.ContactDAO;
import org.tkalenko.test.work.hotel.advisors.hibernate.utils.dao.FactoryDAO;

public class MySQLFactoryDAO implements FactoryDAO {
    private static final Logger log = LoggerFactory.getLogger(MySQLFactoryDAO.class);

    private ContactDAO contactDAO = new MySQLContactDAO();
    private AddressDAO addressDAO = new MySQLAddressDAO();

    @Override
    public ContactDAO getContactDAO() {
        return this.contactDAO;
    }

    @Override
    public AddressDAO getAddressDAO() {
        return this.addressDAO;
    }
}
