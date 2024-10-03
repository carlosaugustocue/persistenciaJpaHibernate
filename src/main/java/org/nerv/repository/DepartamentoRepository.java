package org.nerv.repository;


import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.nerv.domain.model.Departamento;

public class DepartamentoRepository {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPA_PU");

    public void crear(Departamento departamento) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(departamento);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public Departamento leer(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Departamento.class, id);
        } finally {
            em.close();
        }
    }

    public void actualizar(Departamento departamento) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(departamento);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public void eliminar(Departamento departamento) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.remove(em.contains(departamento) ? departamento : em.merge(departamento));
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
}

