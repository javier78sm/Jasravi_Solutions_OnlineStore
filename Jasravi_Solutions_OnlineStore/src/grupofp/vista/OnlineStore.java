package grupofp.vista;

import grupofp.dao.DAOException;
import grupofp.modelo.MySQLConnector;
import grupofp.mysql.MySQLDAOManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import grupofp.modelo.Articulo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
public class OnlineStore {
    public static void main(String[] args) {
        GestionOs gestion = new GestionOs();
        gestion.inicio();
    }
}