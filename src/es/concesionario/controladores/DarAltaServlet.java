package es.concesionario.controladores;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.concesionario.modelo.Negocio;
import es.concesionario.modelo.Vehiculo;

/**
 * Servlet implementation class DarAltaServlet
 */
@WebServlet("/DarAlta")
public class DarAltaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DarAltaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//CAMINO DE IDA A LA BD
		
		String matricula= request.getParameter("matricula");
		String marca= request.getParameter("marca");
		String modelo= request.getParameter("modelo");
		String color= request.getParameter("color");
		int numeroCaballos= Integer.parseInt(request.getParameter("numeroCaballos"));
		boolean marchas= Boolean.parseBoolean(request.getParameter("marchas"));
		
		
		Negocio negocio= new Negocio();
		int id= negocio.darAlta(matricula, marca, modelo, color, numeroCaballos, marchas);
		
		String men;
		if (id==0){
			men= negocio.noAlta();
			
			request.setAttribute("mensaje",men);
			RequestDispatcher rd= request.getRequestDispatcher("vistaMensaje.jsp");
		    rd.forward(request, response);
		}
		
		
		//CAMINO DE VUELTA DE LA BD
		
		Vehiculo v= negocio.consultarUno(id);
		request.setAttribute("vehiculo", v);
		
		RequestDispatcher rd;
	    rd= request.getRequestDispatcher("vistaIndividual.jsp");
	    rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
