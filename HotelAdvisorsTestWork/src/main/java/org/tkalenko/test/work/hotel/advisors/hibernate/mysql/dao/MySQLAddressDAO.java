package org.tkalenko.test.work.hotel.advisors.hibernate.mysql.dao;

import org.apache.commons.collections4.CollectionUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tkalenko.test.work.hotel.advisors.hibernate.entity.Address;
import org.tkalenko.test.work.hotel.advisors.hibernate.entity.Contact;
import org.tkalenko.test.work.hotel.advisors.hibernate.mysql.MySQLSessionFactory;
import org.tkalenko.test.work.hotel.advisors.hibernate.utils.dao.AddressDAO;

import java.util.Collections;
import java.util.List;

public class MySQLAddressDAO implements AddressDAO {
    private static final Logger log = LoggerFactory.getLogger(MySQLAddressDAO.class);

    @Override
    public List<Address> selectAll() {
        Transaction transaction = null;
        try (Session session = MySQLSessionFactory.get().openSession()) {
            transaction = session.beginTransaction();
            List<Address> res = (List<Address>) session.createQuery("from Address").list();
            log.debug(String.format("selectAll Address.size=[%s]", res.size()));
            transaction.commit();
            return res;
        } catch (Throwable t) {
            log.error(String.format("error while selectAll address"), t);
            try {
                if (transaction != null)
                    transaction.rollback();
            } catch (Throwable t1) {
                log.error(String.format("error while rollback transaction selectAll address"), t1);
            }
        }
        return Collections.emptyList();
    }

    @Override
    public List<Address> selectAll(final long contractId) {
        Transaction transaction = null;
        try (Session session = MySQLSessionFactory.get().openSession()) {
            transaction = session.beginTransaction();
            Query query = session.createQuery("from Address where contact_id = :c_id");
            query.setParameter("c_id", contractId);
            List<Address> res = (List<Address>) query.list();
            log.debug(String.format("selectAll Address.size=[%s] by contact_id=[%s]", res.size(), contractId));
            transaction.commit();
            return res;
        } catch (Throwable t) {
            log.error(String.format("error while selectAll address by contact_id=[%s]", contractId), t);
            try {
                if (transaction != null)
                    transaction.rollback();
            } catch (Throwable t1) {
                log.error(String.format("error while rollback transaction selectAll address by contact_id=[%s]", contractId), t1);
            }
        }
        return Collections.emptyList();
    }

    @Override
    public Address select(final long id) {
        Transaction transaction = null;
        try (Session session = MySQLSessionFactory.get().openSession()) {
            transaction = session.beginTransaction();
            Query query = session.createQuery("from Address where  id = :id");
            query.setParameter("id", id);
            List<Address> list = (List<Address>) query.list();
            Address res = null;
            if (CollectionUtils.isEmpty(list)) {
                log.debug(String.format("no address by id=[%s]", id));
            } else {
                res = list.iterator().next();
            }
            transaction.commit();
            return res;
        } catch (Throwable t) {
            log.error(String.format("error while select address by id=[%s]", id), t);
            try {
                if (transaction != null)
                    transaction.rollback();
            } catch (Throwable t1) {
                log.error(String.format("error while rollback transaction select address by id=[%s]", id), t1);
            }
        }
        return null;
    }
}
