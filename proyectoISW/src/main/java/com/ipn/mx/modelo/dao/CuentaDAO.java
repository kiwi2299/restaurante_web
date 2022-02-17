
package com.ipn.mx.modelo.dao;

import com.ipn.mx.modelo.dto.CuentaDTO;
import com.ipn.mx.modelo.entidades.Cuenta;
import com.ipn.mx.utilerias.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;


public class CuentaDAO {
    public void crear(CuentaDTO dto) {
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
    public void update(CuentaDTO dto){
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
    public void delete(CuentaDTO dto){
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaccion = sesion.getTransaction();
        try{
            transaccion.begin();
            sesion.delete(dto.getEntidad());
            transaccion.commit();
        }catch(HibernateException he){
            if(transaccion != null && transaccion.isActive()){
                transaccion.rollback();
            }
        }
    }
    public CuentaDTO read(CuentaDTO dto){
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaccion = sesion.getTransaction();
        try{
            transaccion.begin();
            dto.setEntidad(sesion.get(dto.getEntidad().getClass(), dto.getEntidad().getIdCuenta()));
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
    
    public List readAll(){
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaccion = sesion.getTransaction();
        List lista = new ArrayList();
        try{
            transaccion.begin();
            Query q = sesion.createQuery("from Cuenta c order by c.idCuenta");
            for(Cuenta d : (List<Cuenta>) q.list()){
                CuentaDTO dto = new CuentaDTO();
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
}
