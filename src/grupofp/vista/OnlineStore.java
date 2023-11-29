package grupofp.vista;

import grupofp.dao.DAOException;
import grupofp.modelo.MySQLConnector;
import grupofp.mysql.MySQLDAOManager;
import java.sql.Connection;
import java.sql.SQLException;

public class OnlineStore {
    public static void main(String[] args) {
        GestionOs gestion = new GestionOs();
        gestion.inicio();
    }
}