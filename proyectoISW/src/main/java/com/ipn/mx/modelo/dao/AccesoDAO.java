
package com.ipn.mx.modelo.dao;

import com.ipn.mx.modelo.dto.AccesoDTO;
import com.ipn.mx.modelo.entidades.Acceso;
import com.ipn.mx.utilerias.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;


public class AccesoDAO {
    public void crear(AccesoDTO dto) {
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaccion = sesion.getTransaction();
        try {
            transaccion.begin();
            sesion.save(dto.getEntidad());
            transaccion.commit();
        } catch (HibernateException he) {
            if (transaccion != null && transaccion.isActive()) {
                transaccion.rollback();
            }
        }
    }
    public void update(AccesoDTO dto){
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaccion = sesion.getTransaction();
        try{
            transaccion.begin();
            sesion.update(dto.getEntidad());
            transaccion.commit();
        }catch(HibernateException he){
            if(transaccion != null && transaccion.isActive()){
                transaccion.rollback();
            }
        }
    }
    public void delete(AccesoDTO dto){
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaccion = sesion.getTransaction();
        try{
            transaccion.begin();
            sesion.delete(dto);
            transaccion.commit();
        }catch(HibernateException he){
            if(transaccion != null && transaccion.isActive()){
                transaccion.rollback();
            }
        }
    }
    public AccesoDTO read(AccesoDTO dto){
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaccion = sesion.getTransaction();
        try{
            transaccion.begin();
            dto.setEntidad(sesion.get(dto.getEntidad().getClass(), dto.getEntidad().getIdAcceso()));
            transaccion.commit();
            return dto;
        }catch(HibernateException he){
            if(transaccion != null && transaccion.isActive()){
                transaccion.rollback();
            }
        }
        return null;
    }
    
    public List readAll(){
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaccion = sesion.getTransaction();
        List lista = new ArrayList();
        try{
            transaccion.begin();
            Query q = sesion.createQuery("from Direccion d order by d.idDireccion");
            for(Acceso d : (List<Acceso>) q.list()){
                AccesoDTO dto = new AccesoDTO();
                dto.setEntidad(d);
                lista.add(dto);
            }
            transaccion.commit();
            return lista;
        }catch(HibernateException he){
            if(transaccion != null && transaccion.isActive()){
                transaccion.rollback();
            }
        }
        return null;
    }
    public AccesoDTO search(AccesoDTO dto){
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaccion = sesion.getTransaction();
        try{
            transaccion.begin();
            Query q = sesion.createQuery("from Acceso u where u.nombreUsuario = :nombreUsuario and u.clavePersonal = :clavePersonal ");
            q.setParameter("nombreUsuario", dto.getEntidad().getNombreUsuario());
            q.setParameter("clavePersonal", dto.getEntidad().getClavePersonal());
            //dto = (AccesoDTO)q.getSingleResult();
             if(q.getResultList().isEmpty()){
                transaccion.commit();
                return null; 
             }
            dto.setEntidad((Acceso)q.getSingleResult());
            transaccion.commit();
            return dto;
        }catch(HibernateException he){
            if(transaccion != null && transaccion.isActive()){
                transaccion.rollback();
                return null;
            }
        }
        return null;
    }
}
