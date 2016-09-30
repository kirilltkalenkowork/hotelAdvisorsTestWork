package org.tkalenko.test.work.hotel.advisors.ejb.utils.dao;

import org.tkalenko.test.work.hotel.advisors.ejb.entity.Address;

import java.util.List;

public interface AddressDAO {

    List<Address> selectAll();

    List<Address> selectAll(final long contractId);

    Address select(final long id);
}
