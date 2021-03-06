package services;

import Utils.HibernateUtils;
import entities.Fournisseur;
import entities.Produit;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class FournisseurDAO {

    Session session;

    public FournisseurDAO() {
        session = HibernateUtils.getSessionFactory().getCurrentSession();
    }

    public Fournisseur add(Fournisseur fournisseur) {
        Transaction transaction = session.beginTransaction();
        session.save(fournisseur);
        transaction.commit();
        session.close();
        return fournisseur;

    }

    public void delete(int id) {
        Fournisseur p = new Fournisseur();
        p.setId(id);
        session.beginTransaction();
        session.delete(p);
        session.getTransaction().commit();
        session.close();
    }

    public List<Fournisseur> findAll() {
        List fournisseur;
        session.beginTransaction();
        fournisseur = session.createQuery("From Fournisseur").list();
        session.getTransaction().commit();
        session.close();

        return fournisseur;
    }

    public void update(Fournisseur fournisseur) {
        session.beginTransaction();
        session.saveOrUpdate(fournisseur);
        session.getTransaction().commit();
        session.close();

    }

    public Fournisseur findById(int id) {
        session.beginTransaction();
        Fournisseur fournisseur = new Fournisseur();
        fournisseur.setId(id);
        fournisseur = session.load(Fournisseur.class, fournisseur.getId());
        session.getTransaction().commit();
        session.close();
        return fournisseur;
    }
    public List<Fournisseur> findByString(String query) {
        List fournisseurs;
        session.beginTransaction();
        fournisseurs = session.createQuery("From Fournisseur where nom like concat('%',:query,'%') ")
                .setParameter("query", query)
                .list();

        session.getTransaction().commit();
        session.close();
        return fournisseurs;
    }


}
