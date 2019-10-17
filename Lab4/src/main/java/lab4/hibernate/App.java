package lab4.hibernate;

import java.util.Iterator;
import java.util.List;

import lab4.hibernate.entities.Client;
import lab4.hibernate.entities.Room;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class App {

    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        App app = new App();

        Client cl1 = new Client("Jan", "Kowalski", 43);
        Client cl2 = new Client("Janusz", "Nowak", 57);

        Room room1 = new Room(4, 2,"pokoj bez internetu");

        Client cl3 = new Client("Tadeusz", "Pan", 31);
        Client cl4 = new Client("Mateusz","Rand",19);
        Client cl5 = new Client("Grazyna", "Zdun", 51);

        Room room2 = new Room(7, 1,"dziura w scianie");

        app.saveRoom(room1);
        app.saveClient(room1, cl1);
        app.saveClient(room1, cl2);

        app.saveRoom(room2);
        app.saveClient(room2, cl3);
        app.saveClient(room2, cl4);
        app.saveClient(room2, cl5);

        app.listRoomInfo();

    }

    public void saveClient(Room room, Client cl) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction=null;
        try {
            transaction = session.beginTransaction();
            cl.setRoom(room);
            session.save(cl);
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void saveRoom(Room room){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction=null;
        try {
            transaction = session.beginTransaction();
            session.save(room);
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void listRoomInfo() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            @SuppressWarnings("unchecked")
            List<Client> personList = session.createQuery("from Client").list();
            for (Iterator<Client> iterator = personList.iterator(); iterator
                    .hasNext();) {
                Client person = (Client) iterator.next();
                System.out.println(person.getFirstName() +" "+ person.getLastName()+" wiek: "+ person.getAge());
                System.out.println("Pokoj: ");
                System.out.println("Petro :"+person.getRoom().getFloor());
                System.out.println("Ilosc pokoi:"+person.getRoom().getRooms());
                System.out.println("Opis: "+person.getRoom().getDescription());
            }
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

}
