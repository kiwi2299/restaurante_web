package com.ipn.mx.controlador;

import com.ipn.mx.modelo.dao.AccesoDAO;
import com.ipn.mx.modelo.dao.PersonalDAO;
import com.ipn.mx.modelo.dao.PuestoDAO;
import com.ipn.mx.modelo.dto.AccesoDTO;
import com.ipn.mx.modelo.dto.PersonalDTO;
import com.ipn.mx.modelo.dto.PuestoDTO;
import com.ipn.mx.utilerias.Graficas;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.util.JRLoader;
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
public class PersonalController {
    @Autowired
    private ServletContext context;
    
    private static boolean validar(HttpSession sesion){
        if(sesion.getAttribute("user")==null){
            return false;
        }else if(((AccesoDTO)sesion.getAttribute("user")).getEntidad().getIdPersonal().getIdPuesto().getNombrePuesto().equals("Administrador")){
            return true;
        }
        return false;
    }
    
    @RequestMapping("/registrarPersonal")
    public String registrarPersonal(Model modelo, HttpServletRequest request) {
        HttpSession sesion = request.getSession();
        if(!validar(sesion)){
            return "redirect:/";
        }
        PuestoDAO dao = new PuestoDAO();
        List lista = dao.readAll();
        java.util.Date fecha = new java.util.Date();
        DateFormat date = new SimpleDateFormat("yyyy-MM-dd");
        modelo.addAttribute("fecha", date.format(fecha));
        modelo.addAttribute("lista", lista);
        modelo.addAttribute("menu", "menuAdmin.jsp");
        modelo.addAttribute("page", "registrarPersonal");
        return "formPersonal";
    }

    @RequestMapping("/guardarPersonal")
    public String guardarPersonal(HttpServletRequest request,
            RedirectAttributes atributos) {
        HttpSession sesion = request.getSession();
        if(!validar(sesion)){
            return "redirect:/";
        }
        PersonalDTO dtop = new PersonalDTO();
        PersonalDAO daop = new PersonalDAO();
        PuestoDTO puestodto = new PuestoDTO();
        dtop.getEntidad().setNombre(request.getParameter("nombre"));
        dtop.getEntidad().setPaterno(request.getParameter("paterno"));
        dtop.getEntidad().setMaterno(request.getParameter("materno"));
        dtop.getEntidad().setFechaNacimiento(java.sql.Date.valueOf(request.getParameter("fechaNacimiento")));
        puestodto.getEntidad().setIdPuesto(Integer.parseInt(request.getParameter("puesto")));
        dtop.getEntidad().setIdPuesto(puestodto.getEntidad());
        AccesoDTO dtoa = new AccesoDTO();
        AccesoDAO daoa = new AccesoDAO();

        if (!request.getParameter("idPersonal").isEmpty()) {
            dtop.getEntidad().setIdPersonal(Integer.parseInt(request.getParameter("idPersonal")));
            daop.update(dtop);
            dtoa.getEntidad().setIdPersonal(dtop.getEntidad());
            dtoa.getEntidad().setNombreUsuario(request.getParameter("nombreUsuario"));
            dtoa.getEntidad().setClavePersonal(request.getParameter("password"));
            dtoa.getEntidad().setIdAcceso(Integer.parseInt(request.getParameter("idAcceso")));
            daoa.update(dtoa);
            return "redirect:/listaPersonal";
        }

        int id = daop.crear(dtop);
        if (id != 0) {
            dtoa.getEntidad().setIdPersonal(dtop.getEntidad());
            dtoa.getEntidad().setNombreUsuario(request.getParameter("nombreUsuario"));
            dtoa.getEntidad().setClavePersonal(request.getParameter("password"));
            daoa.crear(dtoa);
        } else {
            atributos.addAttribute("msg", "Error al registrar usuario");
            atributos.addAttribute("msgType", 2);
        }

        return "redirect:/registrarPersonal";
    }

    @RequestMapping("/listaPersonal")
    public String listaPersonal(Model modelo, HttpSession sesion) {
        if(!validar(sesion)){
            return "redirect:/";
        }
        PersonalDAO dao = new PersonalDAO();
        List lista = dao.readAll();
        modelo.addAttribute("listaPersonal", lista);
        modelo.addAttribute("menu", "menuAdmin.jsp");
        modelo.addAttribute("page", "listaPersonal");
        return "listaPersonal";
    }

    @RequestMapping("/eliminarPersonal")
    public String eliminarPersonal(HttpServletRequest request,
            RedirectAttributes atributos) {
        HttpSession sesion = request.getSession();
        if(!validar(sesion)){
            return "redirect:/";
        }
        PersonalDTO dto = new PersonalDTO();
        dto.getEntidad().setIdPersonal(Integer.parseInt(request.getParameter("id")));
        PersonalDAO dao = new PersonalDAO();
        try {
            dto = dao.read(dto);
            dao.delete(dto);
        } catch (Exception e) {
            atributos.addFlashAttribute("msg", "Error al eliminar Usuario");
            atributos.addFlashAttribute("msgType", 2);
        } finally {
            atributos.addFlashAttribute("msg", "Usuario eliminado con exito");
            atributos.addFlashAttribute("msgType", 1);
        }

        return "redirect:/listaPersonal";
    }

    @RequestMapping("/editarPersonal")
    public String editarPersonal(HttpServletRequest request, RedirectAttributes modelo) {
        HttpSession sesion = request.getSession();
        if(!validar(sesion)){
            return "redirect:/";
        }
        PersonalDTO dto = new PersonalDTO();
        PersonalDAO dao = new PersonalDAO();
        dto.getEntidad().setIdPersonal(Integer.parseInt(request.getParameter("id")));
        dto = dao.read(dto);
        modelo.addFlashAttribute("dto", dto);

        return "redirect:/registrarPersonal";
    }
    
    @ResponseBody
    @RequestMapping("/reportePersonal")
     public void gerReport(ModelAndView model,HttpServletResponse response,OutputStream sos) throws JRException, IOException {
        PersonalDAO dao = new PersonalDAO();
        JasperPrint jp = dao.fill(context.getRealPath("/resources/reports/ReportePersonal.jasper"));
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        JasperExportManager.exportReportToPdfStream(jp, os);
        sos.write(os.toByteArray());
        sos.flush();
        sos.close();
    }
    @RequestMapping("/graficaPersonal")
    public String graficar(Model model) {
        Graficas data = new Graficas();
        JFreeChart g = ChartFactory.createPieChart3D("Personal por Edad", data.getGraficaPersonal(), true, true,Locale.getDefault());
        String fileName = context.getRealPath("/resources/graficas/graficaPersonal.png");
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
        model.addAttribute("menu","menuAdmin.jsp");
            model.addAttribute("grafica", "resources/graficas/graficaPersonal.png");
        return "grafica";
    }
}
