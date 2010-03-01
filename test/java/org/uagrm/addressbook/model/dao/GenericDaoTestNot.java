package org.uagrm.addressbook.model.dao;

/**
 * The 'Not' suffix is a hack to avoid the class inclusion in a unit test.
 * @author Timoteo Ponce
 *
 */
public interface GenericDaoTestNot {
    final int TEST_ENTRIES = 5;
    void createEntries();

    void updateEntries();

    void deleteEntries();
}
