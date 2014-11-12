package es.concesionario.controladores;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.concesionario.modelo.Negocio;

/**
 * Servlet implementation class ProcesarServlet
 */
@WebServlet("/Procesar")
public class ProcesarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProcesarServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//CAMINO DE IDA A LA BD
		
		int id= Integer.parseInt(request.getParameter("id"));
		String matricula= request.getParameter("matricula");
		String marca= request.getParameter("marca");
		String modelo= request.getParameter("modelo");
		String color= request.getParameter("color");
		int numeroCaballos= Integer.parseInt(request.getParameter("numeroCaballos"));
		boolean marchas= Boolean.parseBoolean(request.getParameter("marchas"));
		String borrar= request.getParameter("borrar");
	    String actualizar=request.getParameter("actualizar");
	    
	    Negocio negocio = new Negocio();
	    
	    String act = ""; 
	       //proceso borrar
	    
	     if(borrar!=null) {
		     act= negocio.borrar(id);
		     
		   //proceso actualizar
	      }
	     if(actualizar!=null) {
	         act= negocio.actualizar(id, matricula, marca, modelo, color, numeroCaballos, marchas);
	     }
		
	   //CAMINO DE VUELTA DE LA BD
	     
	     request.setAttribute("mensaje",act);
	     // redirigir... a  la vista mensaje
	     RequestDispatcher rd= request.getRequestDispatcher("vistaMensaje.jsp");
	     rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
