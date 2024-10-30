package org.example;


import org.example.models.Actor;
import org.example.models.Movie;
import org.example.models.Studio;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class Main {
    public static void main(String[] args) {

        /*HibernateUtil.getSessionFactory().inSession(session -> {
            Studio studio1 = session.get(Studio.class, 1L);
            System.out.println(studio1);
        });*/

        Studio studio1;
        Movie movie1;
        Actor actor1;

        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            //Query de un count de cuantos studios hay
            Query<Long> q = session.createQuery("select count(s) from Studio s", Long.class);
            System.out.println(q.getSingleResult());

            //Query de un count de cuantas peliculas tiene el primer studio
            Query<Long> q1 = session.createQuery("select count(m) from Studio s join s.movies m where s.id=1", Long.class);
            System.out.println(q1.getSingleResult());

            Query<Movie> q2 = session.createQuery("select a.movies from Actor a where a.id=1", Movie.class);
            q2.list().forEach(System.out::println);

            Query<Studio> q3 = session.createQuery("select s from Actor a join a.movies m join m.studio s where a.id=1", Studio.class);
            q3.list().forEach(System.out::println);

        }













        /*try(Session session = HibernateUtil.getSessionFactory().openSession()) { //Cuando trabajamos con metodos que nos devuelven cosas, por defecto usamos este
            studio1 = session.get(Studio.class, 1L);
       }
        studio1.getMovies().forEach(System.out::println);// Funciona el sout estando fuera por el fetch de la clase Studio, sin eso, solo funcionaria dentro del try
*/
        /*
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            actor1 = session.get(Actor.class, 6L);


            movie1 = session.get(Movie.class, 1L);
            actor1.addMovie(movie1);
            movie1.addActor(actor1);
            session.merge(movie1);
            session.merge(actor1);
            session.getTransaction().commit();



            actor1.getMovies().forEach(System.out::println);
        }
*/


    }
}