package org.tkalenko.test.work.hotel.advisors.ejb.utils;

import org.tkalenko.test.work.hotel.advisors.ejb.mysql.dao.MySQLFactoryDAO;
import org.tkalenko.test.work.hotel.advisors.ejb.utils.dao.AddressDAO;
import org.tkalenko.test.work.hotel.advisors.ejb.utils.dao.ContactDAO;
import org.tkalenko.test.work.hotel.advisors.ejb.utils.dao.FactoryDAO;

import java.util.HashMap;
import java.util.Map;

public class FacadeDAO implements org.tkalenko.test.work.hotel.advisors.ejb.utils.dao.FacadeDAO {
    private static final FacadeDAO INSTANCE = new FacadeDAO();

    private Map<Integer, FactoryDAO> daoMap = new HashMap<>();

    public static final int MYSQL_TYPE = 1;

    private FacadeDAO() {
        this.daoMap.put(MYSQL_TYPE, new MySQLFactoryDAO());
    }

    public static FacadeDAO get() {
        return INSTANCE;
    }

    @Override
    public ContactDAO getContactDAO(final int type) throws IllegalArgumentException {
        if (!this.daoMap.containsKey(type))
            throw new IllegalArgumentException("no type");
        return this.daoMap.get(type).getContactDAO();
    }

    @Override
    public AddressDAO getAddressDAO(final int type) throws IllegalArgumentException {
        if (!this.daoMap.containsKey(type))
            throw new IllegalArgumentException("no type");
        return this.daoMap.get(type).getAddressDAO();
    }
}
