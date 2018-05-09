package services;

import Utils.HibernateUtils;
import entities.Produit;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class ProduitDAO {

    Session session;

    public ProduitDAO() {
        session = HibernateUtils.getSessionFactory().getCurrentSession();
    }

    public void add(Produit produit) {
        Transaction transaction = session.beginTransaction();
        session.save(produit);

        transaction.commit();
        session.close();

    }

    public void delete(int id) {
        Produit p = new Produit();
        p.setId(id);
        session.beginTransaction();
        session.delete(p);
        session.getTransaction().commit();
        session.close();
    }

    public List<Produit> findAll() {
        List produits;
        session.beginTransaction();
        produits = session.createQuery("From Produit").list();
        session.close();
        return produits;
    }

    public void update(Produit produit) {
        session.beginTransaction();
        session.saveOrUpdate(produit);
        session.getTransaction().commit();
        session.close();

    }

    public Produit findById(int id) {
        session.beginTransaction();
        Produit produit = new Produit();
        produit.setId(id);
        produit = session.load(Produit.class, produit.getId());
        session.getTransaction().commit();
        session.close();
        return produit;
    }

}
