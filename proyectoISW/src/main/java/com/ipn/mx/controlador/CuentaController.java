
package com.ipn.mx.controlador;

import com.ipn.mx.modelo.dao.EnvioDAO;
import com.ipn.mx.modelo.dao.CuentaDAO;
import com.ipn.mx.modelo.dto.AccesoDTO;
import com.ipn.mx.modelo.dto.CuentaDTO;
import com.ipn.mx.modelo.dto.EnvioDTO;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class CuentaController {
    @Autowired ServletContext context;
    
    private static boolean validar(HttpSession sesion){
        if(sesion.getAttribute("user")==null 
                || !((AccesoDTO)sesion.getAttribute("user")).getEntidad().getIdPersonal().getIdPuesto().getNombrePuesto().equals("Mesero")){
            System.out.println(((AccesoDTO)sesion.getAttribute("user")).getEntidad().getIdPersonal().getIdPuesto().getNombrePuesto()+"false");
            return false;
        }
        System.out.println(((AccesoDTO)sesion.getAttribute("user")).getEntidad().getIdPersonal().getIdPuesto().getNombrePuesto());
        return true;
    }
    
    @RequestMapping("/listaCuentas")
    public String listaCuentas(Model modelo,HttpSession sesion){
        if(!validar(sesion)){
            return "redirect:/";
        }
        CuentaDAO dao = new CuentaDAO();
        List lista = dao.readAll();
        modelo.addAttribute("listaCuentas", lista );
        modelo.addAttribute("menu", "menuMesero.jsp");
        modelo.addAttribute("page", "listaCuentas");
        return "listaCuentas";
    }
    
    @RequestMapping("/registrarCuenta")
    public String registrarCuenta(Model modelo,HttpSession sesion){
        if(!validar(sesion)){
            return "redirect:/";
        }
        EnvioDAO dao = new EnvioDAO();
        List lista = dao.readAll();
        java.util.Date fecha = new java.util.Date();
        DateFormat date = new SimpleDateFormat("yyyy-MM-dd");
        modelo.addAttribute("fecha", date.format(fecha));  
        modelo.addAttribute("menu", "menuMesero.jsp");
        modelo.addAttribute("lista", lista);
        modelo.addAttribute("page", "registrarCuenta");
        return "formCuenta";
    }
    
    @RequestMapping("/guardarCuenta")
    public String guardarCuenta(HttpServletRequest request,
            RedirectAttributes atributos,HttpSession sesion) {
        if(!validar(sesion)){
            return "redirect:/";
        }
        CuentaDTO dto = new CuentaDTO();
        EnvioDTO edto = new EnvioDTO();
        
        if(!request.getParameter("envio").equals("Sin envio")){
            edto.getEntidad().setIdEnvio(Integer.parseInt(request.getParameter("envio")));
            dto.getEntidad().setIdEnvio(edto.getEntidad());
        }
        dto.getEntidad().setNombreCliente(request.getParameter("nombreCliente"));
        dto.getEntidad().setPropina(Float.parseFloat(request.getParameter("propina")));
        dto.getEntidad().setTotal(Float.parseFloat(request.getParameter("total")));
        dto.getEntidad().setFecha(java.sql.Date.valueOf(request.getParameter("fecha")));
        
        CuentaDAO dao = new CuentaDAO();
        if (!request.getParameter("id").isEmpty()) {
            dto.getEntidad().setIdCuenta(Integer.parseInt(request.getParameter("id")));
            dao.update(dto);
            return "redirect:/listaCuentas";
        } else {
            dao.crear(dto);
            atributos.addFlashAttribute("msg", "Cuenta registrada con exito");
            atributos.addFlashAttribute("msgType", 1);
        }

        

        return "redirect:/registrarCuenta";
    }
    
    @RequestMapping("/editarCuenta")
    public String editarCuenta(HttpServletRequest request,RedirectAttributes atributos,HttpSession sesion){
        if(!validar(sesion)){
            return "redirect:/";
        }
        CuentaDTO dto = new CuentaDTO();
        CuentaDAO dao = new CuentaDAO();
        dto.getEntidad().setIdCuenta(Integer.parseInt(request.getParameter("id")));
        dto = dao.read(dto);
        atributos.addFlashAttribute("dto",dto);
        return "redirect:/registrarCuenta";
    }
    
    @RequestMapping("/eliminarCuenta")
    public String eliminarCuenta(HttpServletRequest request,HttpSession sesion){
        if(!validar(sesion)){
            return "redirect:/";
        }
        CuentaDTO dto = new CuentaDTO();
        CuentaDAO dao = new CuentaDAO();
        dto.getEntidad().setIdCuenta(Integer.parseInt(request.getParameter("id")));
        dao.delete(dto);
        return "redirect:/listaCuentas";
    }
}
