package org.tkalenko.test.work.hotel.advisors.zk;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.tkalenko.test.work.hotel.advisors.hibernate.entity.Address;
import org.tkalenko.test.work.hotel.advisors.hibernate.entity.Contact;
import org.tkalenko.test.work.hotel.advisors.hibernate.utils.FacadeDAO;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.metainfo.EventHandler;
import org.zkoss.zk.ui.util.Composer;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ContactInfoRetriever implements Composer {

    public void doAfterCompose(final Component target) {
        target.addEventListener("onClick", new EventListener<Event>() {
            public void onEvent(Event event) {
                List<Contact> contacts = Collections.emptyList();
                switch (target.getId()) {
                    case "search":
                        Textbox input = (Textbox) target.query("#input");
                        String value = input.getValue();
                        if (StringUtils.isNumeric(value)) {
                            Contact contact = FacadeDAO.get().getContactDAO(FacadeDAO.MYSQL_TYPE).select(Long.valueOf(value));
                            if (contact != null)
                                contacts = Arrays.asList(contact);
                        }
                        break;
                    case "all":
                        contacts = FacadeDAO.get().getContactDAO(FacadeDAO.MYSQL_TYPE).selectAll();
                        break;
                }
                Component contact = target.query("#contact");
                Component address = target.query("#addresses");
                contact.getChildren().clear();
                address.getChildren().clear();
                if (CollectionUtils.isEmpty(contacts)) {
                    contact.appendChild(new Label("No contact/s"));
                } else {
                    Grid grid = new Grid();
                    contact.appendChild(grid);
                    Columns columns = new Columns();
                    columns.appendChild(new Column("Id"));
                    columns.appendChild(new Column("Name"));
                    columns.appendChild(new Column("Surname"));
                    columns.appendChild(new Column("Patronymic"));
                    columns.appendChild(new Column("Phone"));
                    columns.appendChild(new Column("Action"));
                    grid.appendChild(columns);
                    grid.setRowRenderer(new RowRenderer<Contact>() {
                        @Override
                        public void render(final Row row, final Contact contact, final int i) throws Exception {
                            row.appendChild(new Label(String.valueOf(contact.getId())));
                            row.appendChild(new Label(contact.getName()));
                            row.appendChild(new Label(contact.getSurname()));
                            row.appendChild(new Label(contact.getPatronymic()));
                            row.appendChild(new Label(contact.getPhone()));

                            Button button = new Button();
                            row.appendChild(button);
                            button.setId(String.format("address#%s", contact.getId()));
                            button.setLabel("Show addresses");
                            button.setType("button");
                            button.addEventListener("onClick", new EventListener<Event>() {
                                @Override
                                public void onEvent(final Event event) throws Exception {
                                    List<Address> addresses = FacadeDAO.get().getAddressDAO(FacadeDAO.MYSQL_TYPE).selectAll(contact.getId());
                                    address.getChildren().clear();
                                    if (CollectionUtils.isEmpty(addresses)) {
                                        address.appendChild(new Label("No address/es"));
                                    } else {
                                        Grid addressGrid = new Grid();
                                        address.appendChild(addressGrid);
                                        Columns addressColumns = new Columns();
                                        addressColumns.appendChild(new Column("Id"));
                                        addressColumns.appendChild(new Column("City"));
                                        addressColumns.appendChild(new Column("Street"));
                                        addressColumns.appendChild(new Column("House"));
                                        addressColumns.appendChild(new Column("Zip"));
                                        addressColumns.appendChild(new Column("Flat"));
                                        addressGrid.appendChild(addressColumns);
                                        addressGrid.setRowRenderer(new RowRenderer<Address>() {
                                            @Override
                                            public void render(final Row row, final Address address, final int i) throws Exception {
                                                row.appendChild(new Label(String.valueOf(address.getId())));
                                                row.appendChild(new Label(address.getCity()));
                                                row.appendChild(new Label(address.getStreet()));
                                                row.appendChild(new Label(address.getHouse()));
                                                row.appendChild(new Label(address.getZip()));
                                                row.appendChild(new Label(address.getFlat()));
                                            }
                                        });
                                        addressGrid.setModel(new ListModelList<Address>(addresses));
                                    }
                                }
                            });
                        }
                    });
                    grid.setModel(new ListModelList<Contact>(contacts));
                }
            }
        });
    }
}
