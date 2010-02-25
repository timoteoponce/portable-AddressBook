package org.ponce.addressbook.view;

import org.ponce.addressbook.controller.Controller;

/**
 * @author Timoteo Ponce
 *
 * @param <T>
 */
public interface View<T> {

    void setModel(T model);

    void update();

    void setController(Controller<T> controller);

    Controller<T> getController();

}
