package services;

import Utils.HibernateUtils;
import entities.HistoriqueCaisse;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.Date;
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
        fournisseur = session.createQuery("From HistoriqueCaisse").list();
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
        fournisseurs = session.createQuery("select hc From HistoriqueCaisse hc JOIN fetch hc.produits p WHERE " +
                "p.nom like concat('%',:query,'%')" +
                "or p.reference like concat('%',:query,'%')" +
                "or p.codabar  like concat('%',:query,'%') ")
                .setParameter("query", query)
                .list();
        //fournisseurs.get(0).toString();
        session.getTransaction().commit();
        session.close();
        return fournisseurs;
    }

    public List<HistoriqueCaisse> findByDate(Date start, Date end) {
        List fournisseurs;
        session.beginTransaction();
        fournisseurs = session.createQuery("From HistoriqueCaisse where date between :start and :end ")
                .setParameter("start", start)
                .setParameter("end", end)
                .list();

        session.getTransaction().commit();
        session.close();
        return fournisseurs;
    }


}
