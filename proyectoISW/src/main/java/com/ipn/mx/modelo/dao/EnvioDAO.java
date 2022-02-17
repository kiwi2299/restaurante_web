/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.modelo.dao;

import com.ipn.mx.modelo.dto.EnvioDTO;
import com.ipn.mx.modelo.entidades.Envio;
import com.ipn.mx.utilerias.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 *
 * @author metal
 */
public class EnvioDAO {
    public void crear(EnvioDTO dto){
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaccion = sesion.getTransaction();
        try{
            transaccion.begin();
            sesion.save(dto.getEntidad());
            transaccion.commit();
        }catch(HibernateException he){
            if(transaccion != null && transaccion.isActive()){
                transaccion.rollback();
            }
        }
    }
    public void update(EnvioDTO dto){
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
    public void delete(EnvioDTO dto){
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
    public EnvioDTO read(EnvioDTO dto){
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaccion = sesion.getTransaction();
        try{
            transaccion.begin();
            dto.setEntidad(sesion.get(dto.getEntidad().getClass(), dto.getEntidad().getIdEnvio()));
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
            Query q = sesion.createQuery("from Envio e order by e.idEnvio");
            for(Envio d : (List<Envio>) q.list()){
                EnvioDTO dto = new EnvioDTO();
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
