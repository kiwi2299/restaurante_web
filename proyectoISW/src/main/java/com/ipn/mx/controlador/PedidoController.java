package com.ipn.mx.controlador;

import com.ipn.mx.modelo.dao.CuentaDAO;
import com.ipn.mx.modelo.dao.PedidoDAO;
import com.ipn.mx.modelo.dao.PersonalDAO;
import com.ipn.mx.modelo.dao.PlatilloDAO;
import com.ipn.mx.modelo.dto.AccesoDTO;
import com.ipn.mx.modelo.dto.PedidoDTO;
import com.ipn.mx.modelo.dto.CuentaDTO;
import com.ipn.mx.modelo.dto.PersonalDTO;
import com.ipn.mx.modelo.dto.PlatilloDTO;
import com.ipn.mx.modelo.entidades.Platillo;
import java.util.ArrayList;
import java.util.Collection;
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
public class PedidoController {

    @Autowired
    ServletContext context;

    private static boolean validar(HttpSession sesion) {
        if (sesion.getAttribute("user") == null) {
            return false;
        } else if (((AccesoDTO) sesion.getAttribute("user")).getEntidad().getIdPersonal().getIdPuesto().getNombrePuesto().equals("Mesero")) {
            return true;
        }
        return false;
    }

    @RequestMapping("/listaPedidos")
    public String listaPedidos(Model modelo, HttpSession sesion) {
        if (!validar(sesion)) {
            return "redirect:/";
        }
        PedidoDAO dao = new PedidoDAO();
        List lista = dao.readAll();
        modelo.addAttribute("listaPedidos", lista);
        modelo.addAttribute("menu", "menuMesero.jsp");
        modelo.addAttribute("page", "listaPedidos");
        return "listaPedidos";
    }

    @RequestMapping("/registrarPedido")
    public String registrarPedido(Model modelo, HttpServletRequest request, HttpSession sesion) {
        if (!validar(sesion)) {
            return "redirect:/";
        }
        CuentaDAO dao = new CuentaDAO();
        List lista = dao.readAll();
        PlatilloDAO pdao = new PlatilloDAO();
        List listaPlatillo = pdao.readAll();
        AccesoDTO adto = (AccesoDTO) sesion.getAttribute("user");
        PersonalDTO pdto = new PersonalDTO();
        pdto.setEntidad(adto.getEntidad().getIdPersonal());
        modelo.addAttribute("mesero", pdto.getEntidad());
        modelo.addAttribute("menu", "menuMesero.jsp");
        modelo.addAttribute("lista", lista);
        modelo.addAttribute("listaPlatillos", listaPlatillo);
        modelo.addAttribute("page", "registrarPedido");
        return "formPedido";
    }

    @RequestMapping("/guardarPedido")
    public String guardarPedido(HttpServletRequest request,
            RedirectAttributes atributos, HttpSession sesion, Model modelo) {
        if (!validar(sesion)) {
            return "redirect:/";
        }
        PedidoDTO dto = new PedidoDTO();
        CuentaDTO cdto = new CuentaDTO();
        PersonalDTO pdto = new PersonalDTO();
        PersonalDAO pdao = new PersonalDAO();
        PlatilloDAO pldao = new PlatilloDAO();
        CuentaDAO cdao = new CuentaDAO();
        Collection<Platillo> platillos = new ArrayList<>();
        String[] nombresPlatillos;
        nombresPlatillos = request.getParameterValues("platillos");

        cdto.getEntidad().setIdCuenta(Integer.parseInt(request.getParameter("cuenta")));
        cdto = cdao.read(cdto);
        dto.getEntidad().setIdCuenta(cdto.getEntidad());
        pdto.getEntidad().setIdPersonal(Integer.parseInt(request.getParameter("idMesero")));
        pdto = pdao.read(pdto);
        dto.getEntidad().setIdPersonal(pdto.getEntidad());
        dto.getEntidad().setNumeroMesa(Integer.parseInt(request.getParameter("numeroMesa")));
        PlatilloDTO pldto = new PlatilloDTO();
        if (nombresPlatillos != null) {
            for (String id : nombresPlatillos) {
                pldto.getEntidad().setIdPlatillo(Integer.parseInt(id));
                pldto = pldao.read(pldto);
                platillos.add(pldto.getEntidad());
            }
        }

        if (request.getParameter("accion").equals("Agregar Producto")) {
            if (!request.getParameter("platilloSeleccionado").isEmpty()) {
                pldto.getEntidad().setIdPlatillo(Integer.parseInt(request.getParameter("platilloSeleccionado")));
                pldto = pldao.read(pldto);
                platillos.add(pldto.getEntidad());
            }
            pdto = pdao.read(pdto);
            List lista = cdao.readAll();
            List listaPlatillo = pldao.readAll();
            modelo.addAttribute("dto", dto);
            modelo.addAttribute("lista", lista);
            modelo.addAttribute("listaPlatillos", listaPlatillo);
            modelo.addAttribute("listaPlatillosSeleccionados", platillos);
            modelo.addAttribute("mesero", pdto.getEntidad());
            modelo.addAttribute("menu", "menuMesero.jsp");
            modelo.addAttribute("page", "registrarPedido");
            return "formPedido";
        }

        dto.getEntidad().setListaPlatillosPedido(platillos);

        PedidoDAO dao = new PedidoDAO();
        if (!request.getParameter("id").isEmpty() && !request.getParameter("id").equals("0")) {
            dto.getEntidad().setIdPedido(Integer.parseInt(request.getParameter("id")));
            dao.update(dto);
            return "redirect:/listaPedidos";
        } else {
            dao.crear(dto);
            System.out.println(dto.getEntidad().getIdPersonal().getIdPersonal());
            atributos.addFlashAttribute("msg", "Pedido registrada con exito");
            atributos.addFlashAttribute("msgType", 1);
        }

        return "redirect:/registrarPedido";
    }

    @RequestMapping("/editarPedido")
    public String editarPedido(HttpServletRequest request, RedirectAttributes atributos, HttpSession sesion) {
        if (!validar(sesion)) {
            return "redirect:/";
        }
        PedidoDTO dto = new PedidoDTO();
        PedidoDAO dao = new PedidoDAO();
        dto.getEntidad().setIdPedido(Integer.parseInt(request.getParameter("id")));
        dto = dao.read(dto);
        atributos.addFlashAttribute("dto", dto);
        return "redirect:/registrarPedido";
    }

    @RequestMapping("/eliminarPedido")
    public String eliminarPedido(HttpServletRequest request, HttpSession sesion) {
        if (!validar(sesion)) {
            return "redirect:/";
        }
        PedidoDTO dto = new PedidoDTO();
        PedidoDAO dao = new PedidoDAO();
        dto.getEntidad().setIdPedido(Integer.parseInt(request.getParameter("id")));
        dao.delete(dto);
        return "redirect:/listaPedidos";
    }
}
