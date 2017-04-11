package view;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.sql.Time;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;


public class ManagePlayer {

    private static final SessionFactory factory;
    //private static SessionFactory factory;


    static {
        try {
            factory = new Configuration().configure("view/hibernate.cfg.xml").buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static Session getSession() throws HibernateException {
        return factory.openSession();
    }

    public static void main(final String[] args) throws Exception {
        /*
        try {
            factory = new Configuration().configure("view/hibernate.cfg.xml").buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
        */
        ManagePlayer mp = new ManagePlayer();

        /*
        HashSet<Score> set1 = new HashSet();
        HashSet<Score> set2 = new HashSet();
        set1.add(new Score(new Time(0,4,34),6756));
        set2.add(new Score(new Time(0,45,3),765));
        mp.addPlayer("Moshe",set1);
        mp.addPlayer("Shimshon",set2);
        */
        mp.updateScore(1,new Score(new Time(2,3,4),555));
        mp.listPlayers();

    }

    public Integer addPlayer(String name, Set scores) {
        Session session = factory.openSession();
        Transaction tx = null;
        Integer playerId = null;
        try {
            tx = session.beginTransaction();
            Player p = new Player(name);
            p.setScores(scores);
            playerId = (Integer)session.save(p);
            tx.commit();

        } catch (HibernateException e) {
            if(tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return playerId;
    }

    public void listPlayers(){
        Session session = factory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            List players = session.createQuery("FROM Player").list();
            for(Iterator it = players.iterator(); it.hasNext();){
                Player p = (Player)it.next();
                System.out.print("Name: " + p.getName() + " ");
                Set scores = p.getScores();
                for (Iterator it2 = scores.iterator(); it2.hasNext(); ) {
                    Score s = (Score) it2.next();
                    System.out.println("Time: " + s.getTime() + "| Steps: " + s.getSteps());
                }
            }
            tx.commit();
        } catch (HibernateException e) {
            if(tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void deletePlayer(int playerId) {
        Session session = factory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            Player p = (Player) session.get(Player.class,playerId);
            session.delete(p);
            tx.commit();
        } catch (HibernateException e) {
            if(tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }


    }

    public List selectFrom(String s) {

        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();
        List list = session.createQuery("FROM " + s).list();

        return list;
    }

    public void updateScore(Integer playerId, Score score){

        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Player player = (Player)session.get(Player.class,playerId);
            player.getScores().add(score);
            session.update(player);
            tx.commit();

        } catch (HibernateException e) {
            if(tx != null) tx.rollback();;
            e.printStackTrace();
        } finally {
            session.close();
        }

    }

}