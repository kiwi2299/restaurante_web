
package com.ipn.mx.utilerias;

import com.github.javaparser.metamodel.JavaParserMetaModel;
import com.ipn.mx.modelo.dao.CategoriaDAO;
import com.ipn.mx.modelo.dao.PersonalDAO;
import com.ipn.mx.modelo.dao.PlatilloDAO;
import com.ipn.mx.modelo.dao.PuestoDAO;
import com.ipn.mx.modelo.dto.CategoriaDTO;
import com.ipn.mx.modelo.dto.PersonalDTO;
import com.ipn.mx.modelo.dto.PlatilloDTO;
import com.ipn.mx.modelo.dto.PuestoDTO;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.HibernateException;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;


public class Graficas {
    public PieDataset getGraficaPuesto(){
        DefaultPieDataset data = new DefaultPieDataset();
        PuestoDAO dao = new PuestoDAO();
        try{
            Collection<PuestoDTO> dto = dao.readAll();
            Iterator<PuestoDTO> i = dto.iterator();
            while(i.hasNext()){
                PuestoDTO pdto = i.next();
                data.setValue(pdto.getEntidad().getNombrePuesto(), pdto.getEntidad().getListaPersonal().size());
            }
            
            
        }catch(HibernateException he){
                    Logger.getLogger(Graficas.class.getName()).log(Level.SEVERE, null, he);
                    }
        return data;
    }
    public PieDataset getGraficaPersonal(){
        DefaultPieDataset data = new DefaultPieDataset();
        PersonalDAO dao = new PersonalDAO();
        try{
            Collection<PersonalDTO> dto = dao.readAll();
            Iterator<PersonalDTO> i = dto.iterator();
            List<Integer> edades = new ArrayList<Integer>();
            List<Integer>  cantidad = new ArrayList<Integer> ();
            while(i.hasNext()){
                PersonalDTO pdto = i.next();
                Date fecha = Date.valueOf(LocalDate.now());
                Calendar f = Calendar.getInstance();
                f.setTime(fecha);
                Calendar fn = Calendar.getInstance();
                fn.setTime(pdto.getEntidad().getFechaNacimiento());
                int edad = f.get(Calendar.YEAR)-fn.get(Calendar.YEAR);
                if(edades.contains(edad)){
                    int e = edades.indexOf(edad);
                    cantidad.set(e, cantidad.get(e)+1);
                }else{
                    edades.add(edad);
                    cantidad.add(1);
                }
                for (int j = 0; j < edades.size(); j++) {
                    data.setValue(edades.get(j)+" años", cantidad.get(j));
                }
            }
            
            
            
        }catch(HibernateException he){
                    Logger.getLogger(Graficas.class.getName()).log(Level.SEVERE, null, he);
                    }
        return data;
    }
    public PieDataset getGraficaCategoria(){
        DefaultPieDataset data = new DefaultPieDataset();
        CategoriaDAO dao = new CategoriaDAO();
        try{
            Collection<CategoriaDTO> dto = dao.readAll();
            Iterator<CategoriaDTO> i = dto.iterator();
            while(i.hasNext()){
                CategoriaDTO cdto = i.next();
                data.setValue(cdto.getEntidad().getNombreCategoria(), cdto.getEntidad().getListaPlatillos().size());
            }
            
            
        }catch(HibernateException he){
                    Logger.getLogger(Graficas.class.getName()).log(Level.SEVERE, null, he);
                    }
        return data;
    }
    public CategoryDataset getGraficaPlatillos() {
        DefaultCategoryDataset ds = new DefaultCategoryDataset();
        PlatilloDAO dao = new PlatilloDAO();
        try {
            Collection<PlatilloDTO> dto = dao.readAll();
            Iterator<PlatilloDTO> i = dto.iterator();
            while(i.hasNext()){
                PlatilloDTO pdto = i.next();
                ds.setValue(pdto.getEntidad().getPrecioPlatillo(), "Precio", pdto.getEntidad().getNombrePlatillo());
            }
        } catch (HibernateException he) {
            Logger.getLogger(Graficas.class.getName()).log(Level.SEVERE, null, he);
        }
     return ds;   
    }
}
