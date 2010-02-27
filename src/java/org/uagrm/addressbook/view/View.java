package org.uagrm.addressbook.view;

import org.uagrm.addressbook.controller.Controller;

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