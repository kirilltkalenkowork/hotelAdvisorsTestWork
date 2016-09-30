package org.tkalenko.test.work.hotel.advisors.war;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tkalenko.test.work.hotel.advisors.ejb.utils.FacadeDAO;
import org.zkoss.xel.VariableResolver;
import org.zkoss.xel.XelException;

public class ContactResolver implements VariableResolver {
    private static final Logger log = LoggerFactory.getLogger(ContactResolver.class);

    public Object resolveVariable(final String s) throws XelException {
        switch (s) {
            case "contacts":
                return FacadeDAO.get().getContactDAO(FacadeDAO.MYSQL_TYPE).selectAll();
        }
        return null;
    }
}
