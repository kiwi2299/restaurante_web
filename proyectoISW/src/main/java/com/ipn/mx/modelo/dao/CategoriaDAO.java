package com.ipn.mx.modelo.dao;

import com.ipn.mx.utilerias.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.ipn.mx.modelo.dto.CategoriaDTO;
import com.ipn.mx.modelo.entidades.Categoria;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.query.JRHibernateQueryExecuterFactory;
import org.hibernate.query.Query;

public class CategoriaDAO {

    public void crear(CategoriaDTO dto) {
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
    public void update(CategoriaDTO dto){
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
    public void delete(CategoriaDTO dto){
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
    public CategoriaDTO read(CategoriaDTO dto){
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaccion = sesion.getTransaction();
        try{
            transaccion.begin();
            dto.setEntidad(sesion.get(dto.getEntidad().getClass(), dto.getEntidad().getIdCategoria()));
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
            Query q = sesion.createQuery("from Categoria c order by c.idCategoria");
            for(Categoria d : (List<Categoria>) q.list()){
                CategoriaDTO dto = new CategoriaDTO();
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
     public JasperPrint fill(String reporte) throws JRException
  {
    Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
     JasperPrint jp = null;
    Transaction transaccion = sesion.getTransaction();
    transaccion.begin();
     Map<String, Object> model = new HashMap<String, Object>(); 
     model.put(JRHibernateQueryExecuterFactory.PARAMETER_HIBERNATE_SESSION,sesion); 
      File[] files = 
      new File[]{
        new File(reporte),
        };
    for(int i = 0; i < files.length; i++)
    {
      File reportFile = files[i];
      long start = System.currentTimeMillis();
      System.err.println(reportFile.getAbsolutePath());
      jp = JasperFillManager.fillReport(reportFile.getAbsolutePath(), model);
      System.err.println(
        "Report : " + reportFile + ". Filling time : " + (System.currentTimeMillis() - start)
        );
    }
    
    transaccion.rollback();
    sesion.close();
    return jp;
  }
}
