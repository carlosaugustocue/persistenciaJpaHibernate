package org.nerv.repository;


import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.nerv.domain.model.Tarea;

public class TareaRepository {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPA_PU");

    public void crear(Tarea tarea) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(tarea);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public Tarea leer(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Tarea.class, id);
        } finally {
            em.close();
        }
    }

    public void actualizar(Tarea tarea) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(tarea);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public void eliminar(Tarea tarea) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.remove(em.contains(tarea) ? tarea : em.merge(tarea));
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
}

