package com.udel.dataMiner;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.udel.dataMiner.dataModel.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.udel.dataMiner.dataModel.tablesForCalc.costs.Inflation;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;




public class SQLiteMiner {
    private static SessionFactory sessionFactory = null;
    private static final Logger logger = LoggerFactory.getLogger(SQLiteMiner.class);

    static {
        try {
            Configuration configuration = new Configuration().configure();
            sessionFactory = configuration.buildSessionFactory();
        }
        catch (Exception ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            ex.printStackTrace();
            throw new ExceptionInInitializerError(ex);
        }
    }


    public static Item getItemById(int id){
        try(Session session = sessionFactory.openSession()){
            return session.find(Item.class, id);
        }
    }

    public static Mode getModeById(int id){
        try(Session session = sessionFactory.openSession()){
            return session.find(Mode.class, id);
        }
    }

    public static Condition getConditionById(int id){
        try(Session session = sessionFactory.openSession()){
            return session.find(Condition.class, id);
        }
    }



    public static Inflation getInflationById(int id){
        try(Session session = sessionFactory.openSession()){
            return session.find(Inflation.class, id);
        }
    }


    public static List<Item> getAllItems(){
        try(Session session = sessionFactory.openSession()){
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Item> cq = cb.createQuery(Item.class);
            Root<Item> rootEntry = cq.from(Item.class);
            CriteriaQuery<Item> all = cq.select(rootEntry);

            Query<Item> query = session.createQuery(all);
            return query.list();
        }
    }

    public static List<Mode> getAllModes(){
        try(Session session = sessionFactory.openSession()){
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Mode> cq = cb.createQuery(Mode.class);
            Root<Mode> rootEntry = cq.from(Mode.class);
            CriteriaQuery<Mode> all = cq.select(rootEntry);

            Query<Mode> query = session.createQuery(all);
            return query.list();
        }
    }

    public static List<Condition> getAllConditions(){
        try(Session session = sessionFactory.openSession()){
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Condition> cq = cb.createQuery(Condition.class);
            Root<Condition> rootEntry = cq.from(Condition.class);
            CriteriaQuery<Condition> all = cq.select(rootEntry);

            Query<Condition> query = session.createQuery(all);
            return query.list();
        }
    }



    public static List<Inflation> getAllInflation(){
        try(Session session = sessionFactory.openSession()){
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Inflation> cq = cb.createQuery(Inflation.class);
            Root<Inflation> rootEntry = cq.from(Inflation.class);
            CriteriaQuery<Inflation> all = cq.select(rootEntry);

            Query<Inflation> query = session.createQuery(all);
            return query.list();
        }
    }




    public static <T> void saveEntities(List<T> entities) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            for (T entity : entities) {
                session.persist(entity);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }


    /* Методы для таблицы objects */

    public static void saveObject(ObjectEntity object) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            logger.info("Сохраняем Object с Id: {}", object.Id);
            session.persist(object);
            transaction.commit();
            logger.info("Успешное сохранение");
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error("Ошибка при сохранении Object", e);
            e.printStackTrace();
        }
    }

    public static void saveObjects(List<ObjectEntity> objects) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();

            for (ObjectEntity obj : objects) {
                logger.info("Сохраняем Object с Id: {}", obj.Id);
                session.persist(obj);
            }

            transaction.commit();
            logger.info("Успешное сохранение списка объектов");
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error("Ошибка при сохранении списка объектов", e);
            e.printStackTrace();
        }
    }

    public static List<ObjectEntity> getAllObjects() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from ObjectEntity", ObjectEntity.class).list();
        }
    }

    public static List<ObjectParameters> getAllObjectParameters() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from ObjectParameters", ObjectParameters.class).list();
        }
    }

    public static ObjectEntity getObjectById(int id) {
        try (Session session = sessionFactory.openSession()) {
            return session.find(ObjectEntity.class, id);
        }
    }

    public static List<ObjectEntity> getChildrenByObject(Optional<ObjectEntity> parent) {
        try (Session session = sessionFactory.openSession()) {
            if (parent.isPresent()) {
                return session.createQuery(
                        "FROM ObjectEntity o WHERE o.ParentObject = :parent",
                        ObjectEntity.class
                    )
                    .setParameter("parent", parent.get())
                    .list();
            }
            else {
                // родителя нет, можно вернуть пустой список
                return Collections.emptyList();
            }
        }
    }
    public static void saveObjectParameters(List<ObjectParameters> objectParameters) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()){
            //session.createNativeQuery("SELECT 1").getSingleResult();
            transaction = session.beginTransaction();
            for (ObjectParameters objectParameter :objectParameters) {
                logger.info("Сохраняем ObjectParameters с id: {}", objectParameter.Id);
                session.persist(objectParameter);
            }
            transaction.commit();
            logger.info("ObjectParameters успешно сохранены");
        } catch (Exception e) {
            if(transaction != null){
                transaction.rollback();
            }
            logger.error("Ошибка при сохранении ObjectParameters", e);
            e.printStackTrace();
        }
    }

    public static void saveItem(Item item) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.persist(item);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public static void updateItem(Item item) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.merge(item);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public static void deleteItem(int itemId) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Item item = session.find(Item.class, itemId); // Современный способ
            if (item != null) {
                session.remove(item);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public static void saveMode(Mode mode) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.persist(mode);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public static void updateMode(Mode mode) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.merge(mode);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public static void deleteMode(int modeId) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Mode mode = session.find(Mode.class, modeId); // Современный способ
            if (mode != null) {
                session.remove(mode);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public static void saveCondition(Condition condition) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.persist(condition);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public static void updateCondition(Condition condition) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.merge(condition);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public static void deleteCondition(int conditionId) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Condition condition = session.find(Condition.class, conditionId); // Современный способ
            if (condition != null) {
                session.remove(condition);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }




    public static void saveInflation(List<Inflation> Inflations) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()){
            transaction = session.beginTransaction();
            for (Inflation Inflation :Inflations) {
                session.persist(Inflation);
            }
            transaction.commit();
        } catch (Exception e) {
            if(transaction != null){
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public static void updateInflation(Inflation Inflation) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.merge(Inflation);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public static void deleteInflation(int InflationId) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Inflation Inflation = session.find(Inflation.class, InflationId);
            if (Inflation != null) {
                session.remove(Inflation);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }


}
