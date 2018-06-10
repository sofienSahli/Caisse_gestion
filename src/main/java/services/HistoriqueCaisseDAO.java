package services;

import Utils.HibernateUtils;
import entities.HistoriqueCaisse;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class HistoriqueCaisseDAO {
    Session session;

    public HistoriqueCaisseDAO() {
        session = HibernateUtils.getSessionFactory().getCurrentSession();
    }

    public HistoriqueCaisse add(HistoriqueCaisse fournisseur) {
        Transaction transaction = session.beginTransaction();
        session.save(fournisseur);
        transaction.commit();
        session.close();
        return fournisseur;

    }

    public void delete(int id) {
        HistoriqueCaisse p = new HistoriqueCaisse();
        p.setId(id);
        session.beginTransaction();
        session.delete(p);
        session.getTransaction().commit();
        session.close();
    }

    public List<HistoriqueCaisse> findAll() {
        List fournisseur;
        session.beginTransaction();
        fournisseur = session.createQuery("From Fournisseur").list();
        session.getTransaction().commit();
        session.close();

        return fournisseur;
    }

    public void update(HistoriqueCaisse fournisseur) {
        session.beginTransaction();
        session.saveOrUpdate(fournisseur);
        session.getTransaction().commit();
        session.close();

    }

    public HistoriqueCaisse findById(int id) {
        session.beginTransaction();
        HistoriqueCaisse fournisseur = new HistoriqueCaisse();
        fournisseur.setId(id);
        fournisseur = session.load(HistoriqueCaisse.class, fournisseur.getId());
        session.getTransaction().commit();
        session.close();
        return fournisseur;
    }
    public List<HistoriqueCaisse> findByString(String query) {
        List fournisseurs;
        session.beginTransaction();
        fournisseurs = session.createQuery("From HistoriqueCaisse where nom like concat('%',:query,'%') ")
                .setParameter("query", query)
                .list();

        session.getTransaction().commit();
        session.close();
        return fournisseurs;
    }

}
