package org.example.dao;

import org.example.models.Movie;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.List;

public class MovieDAO implements DAO<Movie> {
    private SessionFactory sessionFactory;

    public MovieDAO(SessionFactory sessionFactory) {

        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Movie> findAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("SELECT m from Movie m", Movie.class).list();
        } catch (Exception e) {
            return new ArrayList<Movie>(0);
        }
    }

    @Override
    public Movie findById(Long id) {
        try(Session session = sessionFactory.openSession()) {
            return session.get(Movie.class, id);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public void save(Movie movie) {
        sessionFactory.inTransaction( session -> session.persist(movie) );

    }

    @Override
    public void update(Movie movie) {
        sessionFactory.inTransaction( session -> session.merge(movie) );

    }

    @Override
    public void delete(Movie movie) {
        sessionFactory.inTransaction( session -> session.remove(movie) );

    }
}