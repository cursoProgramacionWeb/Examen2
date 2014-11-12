package es.concesionario.controladores;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.concesionario.modelo.Negocio;
import es.concesionario.modelo.Vehiculo;

/**
 * Servlet implementation class ConsultarMatriculaServlet
 */
@WebServlet("/ConsultarMatricula")
public class ConsultarMatriculaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConsultarMatriculaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//CAMINO DE IDA HACIA LA BD
		String matricula= request.getParameter("matricula");
		
		Negocio negocio= new Negocio();
		ArrayList<Vehiculo> vehiculos =negocio.consultarMatricula(matricula);
		
		
		//CAMINO DE VUELTA DE LA BD
		request.setAttribute("listado", vehiculos);
		
		RequestDispatcher rd;
        rd=request.getRequestDispatcher("mostrarTodos.jsp");
        rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
