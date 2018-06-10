package services;

import Utils.HibernateUtils;
import entities.SoldProduct;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class SoldProductDAO {
    Session session;

    public SoldProductDAO() {
        session = HibernateUtils.getSessionFactory().getCurrentSession();
    }

    public SoldProduct add(SoldProduct produit) {
        Transaction transaction = session.beginTransaction();
        session.save(produit);

        transaction.commit();
        session.close();
        return produit;
    }

    public void delete(int id) {
        SoldProduct p = new SoldProduct();
        p.setId(id);
        session.beginTransaction();
        session.delete(p);
        session.getTransaction().commit();
        session.close();
    }

    public List<SoldProduct> findAll() {
        List produits;
        session.beginTransaction();
        produits = session.createQuery("From SoldProduct").list();
        session.getTransaction().commit();
        session.close();
        return produits;
    }

    public void update(SoldProduct produit) {
        session.beginTransaction();
        session.saveOrUpdate(produit);
        session.getTransaction().commit();
        session.close();

    }

    public SoldProduct findById(int id) {
        session.beginTransaction();
        SoldProduct produit = new SoldProduct();
        produit.setId(id);
        produit = session.load(SoldProduct.class, produit.getId());
        produit.getId() ;
        session.getTransaction().commit();
        session.close();
        return produit;
    }
    public List<SoldProduct> findByString(String query) {
        List produits;
        session.beginTransaction();
        produits = session.createQuery("From SoldProduct where nom like concat('%',:query,'%') " +
                "or reference like concat('%',:query,'%') " +
                "or codabar  like  concat('%',:query,'%')")
                .setParameter("query", query)
                .list();

        session.getTransaction().commit();
        session.close();
        return produits;
    }

}
