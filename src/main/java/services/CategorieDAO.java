package services;

import Utils.HibernateUtils;
import entities.Categorie;
import entities.Categorie;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class CategorieDAO {
    Session session;

    public CategorieDAO() {
        session = HibernateUtils.getSessionFactory().getCurrentSession();
    }

    public void add(Categorie categorie) {
        Transaction transaction = session.beginTransaction();
        session.save(categorie);
        transaction.commit();
        session.close();

    }

    public void delete(int id) {
        Categorie p = new Categorie();
        p.setId(id);
        session.beginTransaction();
        session.delete(p);
        session.getTransaction().commit();
        session.close();
    }

    public List<Categorie> findAll() {
        List categories;
        session.beginTransaction();
        categories = session.createQuery("From Categorie").list();
        session.getTransaction().commit();
        session.close();
        return categories;
    }

    public void update(Categorie categorie) {
        session.beginTransaction();
        session.delete(categorie);
        session.getTransaction().commit();
        session.close();

    }

    public Categorie findById(int id) {
        session.beginTransaction();
        Categorie categorie = new Categorie();
        categorie.setId(id);
        categorie = session.load(Categorie.class, categorie.getId());
        session.getTransaction().commit();
        session.close();
        return categorie;
    }

}
