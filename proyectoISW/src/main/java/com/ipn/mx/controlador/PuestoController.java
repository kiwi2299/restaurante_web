package com.ipn.mx.controlador;

import com.ipn.mx.modelo.dao.PuestoDAO;
import com.ipn.mx.modelo.dto.AccesoDTO;
import com.ipn.mx.modelo.dto.PuestoDTO;
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
import org.hibernate.HibernateException;
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
public class PuestoController {
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
    
    @RequestMapping("/registrarPuesto")
    public String registrarPuesto(HttpServletRequest request,Model modelo) {
        HttpSession sesion = request.getSession();
        if(!validar(sesion)){
            return "redirect:/";
        }
        modelo.addAttribute("menu", "menuAdmin.jsp");
        modelo.addAttribute("page", "registrarPuesto");
        return "formPuesto";
    }

    @RequestMapping("/guardarPuesto")
    public String guardarPuesto(HttpServletRequest request,
            RedirectAttributes atributos) {
        HttpSession sesion = request.getSession();
        if(!validar(sesion)){
            return "redirect:/";
        }
        PuestoDTO dto = new PuestoDTO();
        dto.getEntidad().setNombrePuesto(request.getParameter("nombrePuesto"));
        dto.getEntidad().setDescripcionPuesto(request.getParameter("descripcionPuesto"));
        PuestoDAO dao = new PuestoDAO();
        if (!request.getParameter("id").isEmpty()) {
            dto.getEntidad().setIdPuesto(Integer.parseInt(request.getParameter("id")));
            dao.update(dto);
            return "redirect:/listaPuestos";
        } else {
            dao.crear(dto);
        }

        atributos.addFlashAttribute("msg", "Puesto registrado con exito");
        atributos.addFlashAttribute("msgType", 1);

        return "redirect:/registrarPuesto";
    }

    @RequestMapping("/listaPuestos")
    public String listaPuestos(Model model, HttpServletRequest request) throws JRException {
        HttpSession sesion = request.getSession();
        if(!validar(sesion)){
            return "redirect:/";
        }
        PuestoDAO dao = new PuestoDAO();
        List lista = dao.readAll();
        model.addAttribute("listaPuestos", lista);
        model.addAttribute("page","listaPuestos");
        model.addAttribute("menu","menuAdmin.jsp");
        
        
        return "listaPuestos";
    }
    

    @RequestMapping("/eliminarPuesto")
    public String eliminarPuesto(HttpServletRequest request) {
        HttpSession sesion = request.getSession();
        if(!validar(sesion)){
            return "redirect:/";
        }
        PuestoDTO dto = new PuestoDTO();
        PuestoDAO dao = new PuestoDAO();
        dto.getEntidad().setIdPuesto(Integer.parseInt(request.getParameter("id")));
        dao.delete(dto);
        return "redirect:/listaPuestos";
    }

    @RequestMapping("/editarPuesto")
    public String editarPuesto( RedirectAttributes modelo,HttpServletRequest request) {
        HttpSession sesion = request.getSession();
        if(!validar(sesion)){
            return "redirect:/";
        }
        PuestoDTO dto = new PuestoDTO();
        PuestoDAO dao = new PuestoDAO();
        dto.getEntidad().setIdPuesto(Integer.parseInt(request.getParameter("id")));
        dto = dao.read(dto);
        modelo.addFlashAttribute("dto", dto);
        return "redirect:/registrarPuesto";
    }
    @ResponseBody
    @RequestMapping("/reportePuestos")
    public void gerReport(ModelAndView model,HttpServletResponse response,OutputStream sos) throws JRException, IOException {
        PuestoDAO dao = new PuestoDAO();
        JasperPrint jp = dao.fill(context.getRealPath("/resources/reports/PersonalPorPuesto.jasper"));
        //response.setContentType("application/x-pdf");
        //response.setHeader("Content-disposition", "inline; filename=helloWorldReport.pdf");

        ByteArrayOutputStream os = new ByteArrayOutputStream();
        JasperExportManager.exportReportToPdfStream(jp, os);
        sos.write(os.toByteArray());
        sos.flush();
        sos.close();
    }
    @RequestMapping("/graficaPuesto")
    public String graficar(Model model) {
        Graficas data = new Graficas();
        JFreeChart g = ChartFactory.createPieChart3D("Personal Por Puesto", data.getGraficaPuesto(), true, true,Locale.getDefault());
        String fileName = context.getRealPath("/resources/graficas/graficaPuesto.png");
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
            model.addAttribute("grafica", "resources/graficas/graficaPuesto.png");
        return "grafica";
    }
}
