package FuncTests;

import DAO.keysDao;

public class keysDAOTest {

    public static void main(String[] args) {
        keysDao DAO = new keysDao();
        DAO.getSecondaryKey("VBMG_LEVERINGEN", "VBMG_ORDERS");
    }

}
