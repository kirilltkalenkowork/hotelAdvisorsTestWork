package org.tkalenko.test.work.hotel.advisors.hibernate.utils.dao;

import org.tkalenko.test.work.hotel.advisors.hibernate.entity.Contact;

import java.util.List;

public interface ContactDAO {

    List<Contact> selectAll();

    Contact select(final long id);

}
