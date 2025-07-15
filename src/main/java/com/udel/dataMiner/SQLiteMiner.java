package com.udel.dataMiner;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.udel.dataMiner.dataModel.Condition;
import com.udel.dataMiner.dataModel.Item;
import com.udel.dataMiner.dataModel.Line;
import com.udel.dataMiner.dataModel.Mode;
import com.udel.dataMiner.dataModel.Plant;
import com.udel.dataMiner.dataModel.tabelsForCalc.costs.CostAdKoef;
import com.udel.dataMiner.dataModel.tabelsForCalc.costs.Inflation;
import com.udel.dataMiner.dataModel.tabelsForCalc.costs.OnlyCost;
import com.udel.dataMiner.dataModel.tabelsForCalc.naturals.NaturalAdProcent;
import com.udel.dataMiner.dataModel.tabelsForCalc.naturals.OnlyProcent;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;




public class SQLiteMiner {
    private static SessionFactory sessionFactory = null;

    static{
        try {
            Configuration configuration = new Configuration().configure();
            sessionFactory = configuration.buildSessionFactory();
        } 
        catch (Exception ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static Plant getPlantById(int id){
        try(Session session = sessionFactory.openSession()){
            return session.find(Plant.class, id);
        }
    }

    public static Line getLineById(int id){
        try(Session session = sessionFactory.openSession()){
            Line line = session.find(Line.class, id);
            if(line != null){
                line.Conditions = getAllConditions();
            }
            return line;
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

    public static CostAdKoef getCostAdKoefById(int id){
        try(Session session = sessionFactory.openSession()){
            return session.find(CostAdKoef.class, id);
        }
    }

    public static Inflation getInflationById(int id){
        try(Session session = sessionFactory.openSession()){
            return session.find(Inflation.class, id);
        }
    }

    public static OnlyCost getOnlyCostById(int id){
        try(Session session = sessionFactory.openSession()){
            return session.find(OnlyCost.class, id);
        }
    }

    public static NaturalAdProcent getNaturalAdProcentById(int id){
        try(Session session = sessionFactory.openSession()){
            return session.find(NaturalAdProcent.class, id);
        }
    }

    public static OnlyProcent getOnlyProcentById(int id){
        try(Session session = sessionFactory.openSession()){
            return session.find(OnlyProcent.class, id);
        }
    }
    

    public static List<Plant> getAllPlants(){
        try(Session session = sessionFactory.openSession()){
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Plant> cq = cb.createQuery(Plant.class);
            Root<Plant> rootEntry = cq.from(Plant.class);
            //rootEntry.fetch("Lines", JoinType.LEFT);
            //rootEntry.fetch("Items", JoinType.LEFT);
            CriteriaQuery<Plant> all = cq.select(rootEntry);
            Query<Plant> query = session.createQuery(all);
            return query.list();
        }
    }

    public static List<Line> getAllLines(){
        try(Session session = sessionFactory.openSession()){
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Line> cq = cb.createQuery(Line.class);
            Root<Line> rootEntry = cq.from(Line.class);
            //rootEntry.fetch("items", JoinType.LEFT);
            //rootEntry.fetch("modes", JoinType.LEFT);
            CriteriaQuery<Line> all = cq.select(rootEntry);
            Query<Line> query = session.createQuery(all);
            return query.list();
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

    public static List<CostAdKoef> getAllCostAdKoef(){
        try(Session session = sessionFactory.openSession()){
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<CostAdKoef> cq = cb.createQuery(CostAdKoef.class);
            Root<CostAdKoef> rootEntry = cq.from(CostAdKoef.class);
            CriteriaQuery<CostAdKoef> all = cq.select(rootEntry);

            Query<CostAdKoef> query = session.createQuery(all);
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

    public static List<OnlyCost> getAllOnlyCost(){
        try(Session session = sessionFactory.openSession()){
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<OnlyCost> cq = cb.createQuery(OnlyCost.class);
            Root<OnlyCost> rootEntry = cq.from(OnlyCost.class);
            CriteriaQuery<OnlyCost> all = cq.select(rootEntry);

            Query<OnlyCost> query = session.createQuery(all);
            return query.list();
        }
    }

    public static List<NaturalAdProcent> getAllNaturalAdProcent(){
        try(Session session = sessionFactory.openSession()){
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<NaturalAdProcent> cq = cb.createQuery(NaturalAdProcent.class);
            Root<NaturalAdProcent> rootEntry = cq.from(NaturalAdProcent.class);
            CriteriaQuery<NaturalAdProcent> all = cq.select(rootEntry);

            Query<NaturalAdProcent> query = session.createQuery(all);
            return query.list();
        }
    }

    public static List<OnlyProcent> getAllOnlyProcent(){
        try(Session session = sessionFactory.openSession()){
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<OnlyProcent> cq = cb.createQuery(OnlyProcent.class);
            Root<OnlyProcent> rootEntry = cq.from(OnlyProcent.class);
            CriteriaQuery<OnlyProcent> all = cq.select(rootEntry);

            Query<OnlyProcent> query = session.createQuery(all);
            return query.list();
        }
    }

    public static void savePlant(Plant plant) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.persist(plant);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public static void updatePlant(Plant plant) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.merge(plant);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public static void deletePlant(int plantId) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Plant plant = session.find(Plant.class, plantId);
            if (plant != null) {
                session.remove(plant);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public static void saveLine(Line line) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.persist(line);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public static void updateLine(Line line) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.merge(line);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public static void deleteLine(int lineId) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Line line = session.find(Line.class, lineId); // Современный способ
            if (line != null) {
                session.remove(line);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
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

    public static void saveCostAdKoef(CostAdKoef CostAdKoef) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.persist(CostAdKoef);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public static void updateCostAdKoef(CostAdKoef CostAdKoef) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.merge(CostAdKoef);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public static void deleteCostAdKoef(int CostAdKoefId) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            CostAdKoef CostAdKoef = session.find(CostAdKoef.class, CostAdKoefId);
            if (CostAdKoef != null) {
                session.remove(CostAdKoef);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public static void saveInflation(Inflation Inflation) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.persist(Inflation);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
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

    public static void saveOnlyCost(OnlyCost OnlyCost) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.persist(OnlyCost);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public static void updateOnlyCost(OnlyCost OnlyCost) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.merge(OnlyCost);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public static void deleteOnlyCost(int OnlyCostId) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            OnlyCost OnlyCost = session.find(OnlyCost.class, OnlyCostId);
            if (OnlyCost != null) {
                session.remove(OnlyCost);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public static void saveNaturalAdProcent(NaturalAdProcent NaturalAdProcent) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.persist(NaturalAdProcent);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public static void updateNaturalAdProcent(NaturalAdProcent NaturalAdProcent) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.merge(NaturalAdProcent);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public static void deleteNaturalAdProcent(int NaturalAdProcentId) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            NaturalAdProcent NaturalAdProcent = session.find(NaturalAdProcent.class, NaturalAdProcentId);
            if (NaturalAdProcent != null) {
                session.remove(NaturalAdProcent);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public static void saveOnlyProcent(OnlyProcent OnlyProcent) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.persist(OnlyProcent);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public static void updateOnlyProcent(OnlyProcent OnlyProcent) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.merge(OnlyProcent);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public static void deleteOnlyProcent(int OnlyProcentId) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            OnlyProcent OnlyProcent = session.find(OnlyProcent.class, OnlyProcentId);
            if (OnlyProcent != null) {
                session.remove(OnlyProcent);
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
