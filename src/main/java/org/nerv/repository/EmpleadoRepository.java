package org.nerv.repository;


import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.nerv.domain.model.Empleado;

public class EmpleadoRepository {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPA_PU");

    public void crear(Empleado empleado) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(empleado);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public Empleado leer(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Empleado.class, id);
        } finally {
            em.close();
        }
    }

    public void actualizar(Empleado empleado) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(empleado);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public void eliminar(Empleado empleado) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.remove(em.contains(empleado) ? empleado : em.merge(empleado));
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
}

