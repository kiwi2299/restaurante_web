package com.ipn.mx.controlador;

import com.ipn.mx.modelo.dao.AccesoDAO;
import com.ipn.mx.modelo.dto.AccesoDTO;
import com.ipn.mx.modelo.dto.PersonalDTO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LoginController {

    @RequestMapping("/")
    public String handler(Model model, HttpServletRequest request) {
        LoginController lg = new LoginController();
        HttpSession sesion = request.getSession();
        model.addAttribute("page", "home");
        if (sesion.getAttribute("user") == null) {

            model.addAttribute("menu", "menuGeneral.jsp");

        } else{
            AccesoDTO adto = (AccesoDTO) sesion.getAttribute("user");
            PersonalDTO pdto = new PersonalDTO();
            pdto.setEntidad(adto.getEntidad().getIdPersonal());
            if(pdto.getEntidad().getIdPuesto().getNombrePuesto().equals("Administrador")){
                model.addAttribute("menu", "menuAdmin.jsp");
            }else if(pdto.getEntidad().getIdPuesto().getNombrePuesto().equals("Cocinero")){
                 model.addAttribute("menu", "menuCocinero.jsp");
            }else if(pdto.getEntidad().getIdPuesto().getNombrePuesto().equals("Mesero")){
                 model.addAttribute("menu", "menuMesero.jsp");
            }
            
        }

        return "index";
    }

    @RequestMapping("/login")
    public String login(HttpServletRequest request, Model model, RedirectAttributes atributos) {
        AccesoDTO dto = new AccesoDTO();
        dto.getEntidad().setNombreUsuario(request.getParameter("user"));
        dto.getEntidad().setClavePersonal(request.getParameter("pass"));
        AccesoDAO dao = new AccesoDAO();
        dto = dao.search(dto);
        if (dto != null) {
            HttpSession sesion = request.getSession(true);
            sesion.setAttribute("user", dto);

        } else {
           
            atributos.addFlashAttribute("msg", "Usuario o contraseña incorrectos");
        }

        return "redirect:/";
    }
    @RequestMapping("/cerrarSesion")
    public String cerrarSesion(HttpSession sesion){
        if(sesion.getAttribute("user") != null){
            sesion.invalidate();
        }
        
        return "redirect:/";
    }
}
