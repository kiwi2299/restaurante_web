package com.ipn.mx.controlador;

import com.ipn.mx.modelo.dao.CategoriaDAO;
import com.ipn.mx.modelo.dao.PlatilloDAO;
import com.ipn.mx.modelo.dto.AccesoDTO;
import com.ipn.mx.modelo.dto.CategoriaDTO;
import com.ipn.mx.modelo.dto.PlatilloDTO;
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
public class PlatilloControler {

    @Autowired
    ServletContext context;
    private static boolean validar(HttpSession sesion){
        if(sesion.getAttribute("user")==null){
            return false;
        }else if(((AccesoDTO)sesion.getAttribute("user")).getEntidad().getIdPersonal().getIdPuesto().getNombrePuesto().equals("Mesero") || 
                ((AccesoDTO)sesion.getAttribute("user")).getEntidad().getIdPersonal().getIdPuesto().getNombrePuesto().equals("Cocinero")){
            return true;
        }
        return false;
    }
    
    @RequestMapping("/listaPlatillos")
    public String listaPlatillos(Model modelo, HttpServletRequest request) {
        HttpSession sesion = request.getSession();
        if(!validar(sesion)){
            return "redirect:/";
        }
        PlatilloDAO dao = new PlatilloDAO();
        List lista = dao.readAll();
        modelo.addAttribute("listaPlatillos", lista);
        if (((AccesoDTO) sesion.getAttribute("user")).getEntidad().getIdPersonal().getIdPuesto().getNombrePuesto().equals("Cocinero")) {
            modelo.addAttribute("menu", "menuCocinero.jsp");
            
        }else{
            modelo.addAttribute("menu", "menuMesero.jsp");
        }
        modelo.addAttribute("page", "listaPlatillos");
        return "listaPlatillos";
    }

    @RequestMapping("/registrarPlatillo")
    public String registrarPuesto(Model modelo, HttpSession sesion) {
        if(!validar(sesion)){
            return "redirect:/";
        }
        CategoriaDAO dao = new CategoriaDAO();
        List lista = dao.readAll();
        if (((AccesoDTO) sesion.getAttribute("user")).getEntidad().getIdPersonal().getIdPuesto().getNombrePuesto().equals("Cocinero")) {
            modelo.addAttribute("menu", "menuCocinero.jsp");
            
        }else{
            modelo.addAttribute("menu", "menuMesero.jsp");
        }
        modelo.addAttribute("page", "registrarPlatillo");
        modelo.addAttribute("lista", lista);
        return "formPlatillo";
    }

    @RequestMapping("/guardarPlatillo")
    public String guardarPlatillo(HttpServletRequest request, RedirectAttributes atributos, HttpSession sesion) {
        if(!validar(sesion)){
            return "redirect:/";
        }
        PlatilloDTO dto = new PlatilloDTO();
        CategoriaDTO dtoc = new CategoriaDTO();
        dto.getEntidad().setNombrePlatillo(request.getParameter("nombre"));
        dto.getEntidad().setDescripcionPlatillo(request.getParameter("descripcion"));
        dto.getEntidad().setPrecioPlatillo(Float.parseFloat(request.getParameter("precio")));
        dtoc.getEntidad().setIdCategoria(Integer.parseInt(request.getParameter("categoria")));
        dto.getEntidad().setIdCategoria(dtoc.getEntidad());

        PlatilloDAO dao = new PlatilloDAO();
        if (!request.getParameter("idPlatillo").isEmpty()) {
            dto.getEntidad().setIdPlatillo(Integer.parseInt(request.getParameter("idPlatillo")));
            dao.update(dto);
            return "redirect:/listaPlatillos";
        } else {
            dao.crear(dto);
        }
        return "redirect:/registrarPlatillo";
    }

    @RequestMapping("/editarPlatillo")
    public String editarPlatillo(HttpServletRequest request, RedirectAttributes atributos,HttpSession sesion) {
        if(!validar(sesion)){
            return "redirect:/";
        }
        PlatilloDTO dto = new PlatilloDTO();
        PlatilloDAO dao = new PlatilloDAO();
        dto.getEntidad().setIdPlatillo(Integer.parseInt(request.getParameter("id")));
        dto = dao.read(dto);
        atributos.addFlashAttribute("dto", dto);
        return "redirect:/registrarPlatillo";
    }

    @RequestMapping("/eliminarPlatillo")
    public String eliminarPlatillo(HttpServletRequest request, HttpSession sesion) {
        if(!validar(sesion)){
            return "redirect:/";
        }
        PlatilloDTO dto = new PlatilloDTO();
        PlatilloDAO dao = new PlatilloDAO();
        dto.getEntidad().setIdPlatillo(Integer.parseInt(request.getParameter("id")));
        dao.delete(dto);
        return "redirect:/listaPlatillos";

    }
    
    @ResponseBody
    @RequestMapping("/reportePlatillos")
    public void gerReport(ModelAndView model,HttpServletResponse response,OutputStream sos) throws JRException, IOException {
        PlatilloDAO dao = new PlatilloDAO();
        JasperPrint jp = dao.fill(context.getRealPath("/resources/reports/ReportePlatillos.jasper"));
        //response.setContentType("application/x-pdf");
        //response.setHeader("Content-disposition", "inline; filename=helloWorldReport.pdf");

        ByteArrayOutputStream os = new ByteArrayOutputStream();
        JasperExportManager.exportReportToPdfStream(jp, os);
        sos.write(os.toByteArray());
        sos.flush();
        sos.close();
    }
    
    @RequestMapping("/graficaPlatillos")
    public String graficar(Model model,HttpSession sesion) {
         if(!validar(sesion)){
            return "redirect:/";
        }
        Graficas data = new Graficas();
        JFreeChart g = ChartFactory.createLineChart("Precios de Platillos", "Precios", "Platillos", data.getGraficaPlatillos());
        String fileName = context.getRealPath("/resources/graficas/graficaPlatillos.png");
        File file = new File(fileName);
        
        try{
            if(file.exists()){
            file.delete();
            file.createNewFile();
        }
            ChartUtils.saveChartAsPNG(file, g, 1600, 800);
        } catch (IOException ex) {
            Logger.getLogger(PuestoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        model.addAttribute("menu","menuCocinero.jsp");
            model.addAttribute("grafica", "resources/graficas/graficaPlatillos.png");
        return "grafica";
    }
}
