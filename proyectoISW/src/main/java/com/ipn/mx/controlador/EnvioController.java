
package com.ipn.mx.controlador;

import com.ipn.mx.modelo.dao.PlatilloDAO;
import com.ipn.mx.modelo.dao.EnvioDAO;
import com.ipn.mx.modelo.dto.AccesoDTO;
import com.ipn.mx.modelo.dto.EnvioDTO;
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
public class EnvioController {
    @Autowired ServletContext context;
    
    private static boolean validar(HttpSession sesion){
        if(sesion.getAttribute("user")==null){
            return false;
        }else if(((AccesoDTO)sesion.getAttribute("user")).getEntidad().getIdPersonal().getIdPuesto().getNombrePuesto().equals("Mesero")){
            return true;
        }
        return false;
    }
    
    @RequestMapping("/listaEnvios")
    public String listaEnvios(Model modelo,HttpSession sesion){
        if(!validar(sesion)){
            return "redirect:/";
        }
        EnvioDAO dao = new EnvioDAO();
        List lista = dao.readAll();
        modelo.addAttribute("listaEnvios", lista );
        modelo.addAttribute("menu", "menuMesero.jsp");
        modelo.addAttribute("page", "listaEnvios");
        return "listaEnvios";
    }
    
    @RequestMapping("/registrarEnvio")
    public String registrarEnvio(Model modelo,HttpSession sesion){
        if(!validar(sesion)){
            return "redirect:/";
        }
        modelo.addAttribute("menu", "menuMesero.jsp");
        modelo.addAttribute("page", "registrarEnvio");
        return "formEnvio";
    }
    
    @RequestMapping("/guardarEnvio")
    public String guardarEnvio(HttpServletRequest request,
            RedirectAttributes atributos,HttpSession sesion) {
        if(!validar(sesion)){
            return "redirect:/";
        }
        EnvioDTO dto = new EnvioDTO();
        dto.getEntidad().setCalle(request.getParameter("calle"));
        dto.getEntidad().setNumeroCalle(request.getParameter("numeroCalle"));
        dto.getEntidad().setNumeroInterior(request.getParameter("numeroInterior"));
        dto.getEntidad().setColonia(request.getParameter("colonia"));
        dto.getEntidad().setCodigoPostal(request.getParameter("codigoPostal"));
        dto.getEntidad().setMunicipio(request.getParameter("municipio"));
        dto.getEntidad().setEstado(request.getParameter("estado"));
        dto.getEntidad().setPais(request.getParameter("pais"));
        EnvioDAO dao = new EnvioDAO();
        if (!request.getParameter("id").isEmpty()) {
            dto.getEntidad().setIdEnvio(Integer.parseInt(request.getParameter("id")));
            dao.update(dto);
            return "redirect:/listaEnvios";
        } else {
            dao.crear(dto);
            atributos.addFlashAttribute("msg", "Envio registrado con exito");
            atributos.addFlashAttribute("msgType", 1);
        }

        

        return "redirect:/registrarEnvio";
    }
    
     @RequestMapping("/editarEnvio")
    public String editarEnvio(HttpServletRequest request,RedirectAttributes atributos,HttpSession sesion){
        if(!validar(sesion)){
            return "redirect:/";
        }
        EnvioDTO dto = new EnvioDTO();
        EnvioDAO dao = new EnvioDAO();
        dto.getEntidad().setIdEnvio(Integer.parseInt(request.getParameter("id")));
        dto = dao.read(dto);
        atributos.addFlashAttribute("dto",dto);
        return "redirect:/registrarEnvio";
    }
    
    @RequestMapping("/eliminarEnvio")
    public String eliminarEnvio(HttpServletRequest request,HttpSession sesion){
        if(!validar(sesion)){
            return "redirect:/";
        }
        EnvioDTO dto = new EnvioDTO();
        EnvioDAO dao = new EnvioDAO();
        dto.getEntidad().setIdEnvio(Integer.parseInt(request.getParameter("id")));
        dao.delete(dto);
        return "redirect:/listaEnvios";
    }
}
