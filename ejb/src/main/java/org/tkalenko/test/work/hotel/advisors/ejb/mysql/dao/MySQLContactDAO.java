package org.tkalenko.test.work.hotel.advisors.ejb.mysql.dao;

import org.apache.commons.collections4.CollectionUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tkalenko.test.work.hotel.advisors.ejb.entity.Contact;
import org.tkalenko.test.work.hotel.advisors.ejb.mysql.MySQLSessionFactory;
import org.tkalenko.test.work.hotel.advisors.ejb.utils.dao.ContactDAO;

import java.util.Collections;
import java.util.List;

public class MySQLContactDAO implements ContactDAO {
    private static final Logger log = LoggerFactory.getLogger(MySQLContactDAO.class);

    @Override
    public Contact select(final long id) {
        Transaction transaction = null;
        try (Session session = MySQLSessionFactory.get().openSession()) {
            transaction = session.beginTransaction();
            Query query = session.createQuery("from Contact where id = :id");
            query.setParameter("id", id);
            List<Contact> list = (List<Contact>) query.list();
            Contact res = null;
            if (CollectionUtils.isEmpty(list)) {
                log.debug(String.format("no contact by id=[%s]", id));
            } else {
                res = list.iterator().next();
            }
            transaction.commit();
            return res;
        } catch (Throwable t) {
            log.error(String.format("error while select contact by id=[%s]", id), t);
            try {
                if (transaction != null)
                    transaction.rollback();
            } catch (Throwable t1) {
                log.error(String.format("error while rollback transaction select contact by id=[%s]", id), t1);
            }
        }
        return null;
    }

    @Override
    public List<Contact> selectAll() {
        Transaction transaction = null;
        try (Session session = MySQLSessionFactory.get().openSession()) {
            transaction = session.beginTransaction();
            List<Contact> res = (List<Contact>) session.createQuery("from Contact").list();
            log.debug(String.format("selectAll Contact.size=[%s]", res.size()));
            transaction.commit();
            return res;
        } catch (Throwable t) {
            log.error(String.format("error while selectAll contact"), t);
            try {
                if (transaction != null)
                    transaction.rollback();
            } catch (Throwable t1) {
                log.error(String.format("error while transaction rollback selectAll contact"), t1);
            }
        }
        return Collections.emptyList();
    }
}
