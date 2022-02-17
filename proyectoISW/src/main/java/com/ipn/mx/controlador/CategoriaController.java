
package com.ipn.mx.controlador;

import com.ipn.mx.modelo.dao.CategoriaDAO;
import com.ipn.mx.modelo.dto.AccesoDTO;
import com.ipn.mx.modelo.dto.CategoriaDTO;
import com.ipn.mx.utilerias.Graficas;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class CategoriaController {
    @Autowired
    private ServletContext context;
    
    private static boolean validar(HttpSession sesion){
        if(sesion.getAttribute("user")==null){
            return false;
        }else if( 
                ((AccesoDTO)sesion.getAttribute("user")).getEntidad().getIdPersonal().getIdPuesto().getNombrePuesto().equals("Cocinero")){
            return true;
        }
        return false;
    }
    
    @RequestMapping("/listaCategorias")
    public String listaCategorias(Model modelo, HttpSession sesion){
        if(!validar(sesion)){
            return "redirect:/";
        }
        CategoriaDAO dao = new CategoriaDAO();
        List lista = dao.readAll();
        modelo.addAttribute("listaCategorias", lista);
        modelo.addAttribute("menu","menuCocinero.jsp");
        modelo.addAttribute("page", "listaCategorias");
        return "listaCategorias";
    }
    
    
    @RequestMapping("/registrarCategoria")
    public String registrarCategoria(Model modelo, HttpSession sesion){
        if(!validar(sesion)){
            return "redirect:/";
        }
        modelo.addAttribute("menu", "menuCocinero.jsp");
        modelo.addAttribute("page","registarCategoria");
        return "formCategoria";
    }
    @RequestMapping("/guardarCategoria")
    public String guardarCategoria(HttpServletRequest request,RedirectAttributes atributos, HttpSession sesion){
        if(!validar(sesion)){
            return "redirect:/";
        }
         CategoriaDTO dto = new CategoriaDTO();
        dto.getEntidad().setNombreCategoria(request.getParameter("nombre"));
        dto.getEntidad().setDescripcionCategoria(request.getParameter("descripcion"));
        CategoriaDAO dao = new CategoriaDAO();
        if(!request.getParameter("idCategoria").isEmpty()){
            dto.getEntidad().setIdCategoria(Integer.parseInt(request.getParameter("idCategoria")));
            dao.update(dto);
            return "redirect:/listaCategorias";
        }else{
            dao.crear(dto);
        }
        return "redirect:/registrarCategoria";
    }
    @RequestMapping("/editarCategoria")
    public String editarCategoria(HttpServletRequest request,RedirectAttributes atributos, HttpSession sesion){
        if(!validar(sesion)){
            return "redirect:/";
        }
        CategoriaDTO dto = new CategoriaDTO();
        CategoriaDAO dao = new CategoriaDAO();
        dto.getEntidad().setIdCategoria(Integer.parseInt(request.getParameter("id")));
        dto = dao.read(dto);
        atributos.addFlashAttribute("dto",dto);
        return "redirect:/registrarCategoria";
    }
    @RequestMapping("/eliminarCategoria")
    public String eliminarCategoria(HttpServletRequest request, HttpSession sesion){
        if(!validar(sesion)){
            return "redirect:/";
        }
        CategoriaDTO dto = new CategoriaDTO();
        CategoriaDAO dao = new CategoriaDAO();
        dto.getEntidad().setIdCategoria(Integer.parseInt(request.getParameter("id")));
        dao.delete(dto);
        return "redirect:/listaCategorias";
    }
    @ResponseBody
    @RequestMapping("/reporteCategoria")
    public void getReporte(ModelAndView model,OutputStream sos,HttpSession sesion) throws JRException, IOException{
        validar(sesion);
        CategoriaDAO dao = new CategoriaDAO();
        JasperPrint jp = dao.fill(context.getRealPath("/resources/reports/ReporteCategoria.jasper"));
        ByteArrayOutputStream os =new ByteArrayOutputStream();
        JasperExportManager.exportReportToPdfStream(jp,os);
        sos.write(os.toByteArray());
        sos.flush();
        sos.close();
    }
    @RequestMapping("/graficaCategoria")
    public String graficar(Model model,HttpSession sesion) {
        validar(sesion);
        Graficas data = new Graficas();
        JFreeChart g = ChartFactory.createPieChart3D("Platillos Por Categoria", data.getGraficaCategoria(), true, true,Locale.getDefault());
        String fileName = context.getRealPath("/resources/graficas/graficaCategoria.png");
        File file = new File(fileName);
        
        try{
            if(file.exists()){
            file.delete();
            file.createNewFile();
        }
            ChartUtils.saveChartAsPNG(file, g, 600, 600);
        } catch (IOException ex) {
            Logger.getLogger(PuestoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        model.addAttribute("menu","menuCocinero.jsp");
            model.addAttribute("grafica", "resources/graficas/graficaCategoria.png");
        return "grafica";
    }
}
