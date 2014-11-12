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
 * Servlet implementation class ConsultarUnoServlet
 */
@WebServlet("/ConsultarUno")
public class ConsultarUnoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConsultarUnoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		// CAMINO DE IDA A LA BD
		
		int id= Integer.parseInt(request.getParameter("idVehiculo"));
		
		Negocio negocio= new Negocio();
		Vehiculo vehiculo= negocio.consultarUno(id);
		
		
		// CAMINO VUELTA DE LA BD
		
		request.setAttribute("vehiculo", vehiculo);
		
		RequestDispatcher rd;
	    rd=request.getRequestDispatcher("vistaIndividual.jsp");
	    rd.forward(request, response);
		
	}
		
		

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
