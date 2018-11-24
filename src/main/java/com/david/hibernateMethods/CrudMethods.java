package com.david.hibernateMethods;

import org.hibernate.Transaction;
import org.hibernate.procedure.ProcedureCall;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.david.entities.Computador;
import com.david.entities.DetallePieza;
import com.david.entities.Pieza;
import com.david.entities.Usuario;
import com.david.hibernateUtils.HibernateUtils;
/**
 * Methods that allow make CRUD operations over a DB using Hibernate
 * @author David Moreno
 * @version 1.0
 * @since 1.0
 *
 */

public class CrudMethods {
	
	SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
	/**
	 * Search if the username exists in the DB through its password
	 * @param username
	 * @param password
	 * @return 1 if the username was not found it or 0 if the username was found it
	 */
	public int logIn(String username, String password) {
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		Usuario user = null;
		List<Usuario> users = null;
		
		try {
			transaction = session.beginTransaction();
			users = session.createQuery("FROM Usuario U WHERE U.contraseniaUsuario =: password")
						.setParameter("password", password)
						.list();
			if(users.isEmpty() || users == null)
				return 1;
		}catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
			return 1;
		}finally {
			session.close();
		}
		
		return 0;
			
	}
	/**
	 * Search if a computer exists through its serial
	 * @param computerSerial
	 * @return Computador object
	 */

	public Computador searchComputer(int computerSerial) {
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		Computador computador = null;
		try {
			transaction = session.beginTransaction();
			List<Computador> computers = session.createQuery("FROM Computador C WHERE C.serieComputador = :computerSerial")
							.setParameter("computerSerial", computerSerial)
							.list();
			
			if(computers != null && computers.size() >0) {
				for (Iterator iterator = computers.iterator(); iterator.hasNext();){
		            computador = (Computador) iterator.next();
		        }
			}
						
			transaction.commit();
			
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
			return computador;
		}finally {
			session.close();
		}
		return computador;
	}
	
	/**
	 * Insert a new computer to the DB
	 * @param serieComputador
	 * @param descripcionComputador
	 * @param fechaEnsamblajeComputador
	 * @param detallePieza
	 */
	public void newComputer(int serieComputador, String descripcionComputador, int fechaEnsamblajeComputador, List<DetallePieza> detallePieza) {
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		Computador computador = null;
		
		try {
			transaction = session.beginTransaction();
			computador = new Computador(serieComputador,descripcionComputador,fechaEnsamblajeComputador);
			session.save(computador);
		
			
			for(int i=0;i<detallePieza.size();i++) {
		    	Query query = session.createNamedQuery("callSpPrueba")
						.setParameter("nombrePieza", detallePieza.get(i).getDetalleDePieza())
						.setParameter("valorPieza", detallePieza.get(i).getValorPiezaDetalle())
						.setParameter("serieComputador", serieComputador);
		    	query.list();//.lis() method execute the query but the Stored Procedure does not return any list
			}
			transaction.commit();
			
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
		}finally {
			session.close();
		}
		
	}
	/**
	 * Delete a computer through its serial
	 * @param computador
	 */
	public boolean deleteComputer(Computador computador) {
		
		if(computador != null) {
			Session session = sessionFactory.openSession();
			Transaction transaction = null;
					
			try {
				transaction = session.beginTransaction();
				System.out.println();
				/*First delete the computer from DetallePieza class (FK constraints restrictions)*/
				Query deleteIdComputer = session.createQuery("DELETE FROM DetallePieza WHERE idComputador = :computerId");
				deleteIdComputer.setParameter("computerId",computador.getIdComputador());
				deleteIdComputer.executeUpdate();
				
				/*Then, delete the computer from Computador class*/
				Query deleteComputer = session.createQuery("DELETE FROM Computador WHERE idComputador = :computerId");
				deleteComputer.setParameter("computerId",computador.getIdComputador());
				deleteComputer.executeUpdate();
							
				transaction.commit();
				return true;
			} catch (Exception e) {
				transaction.rollback();
				e.printStackTrace();
				return false;
			}finally {
				session.close();
			}
		}else {
			return false;
		}
		
	}
	/**
	 * Retireve a List of Pieza objects from the DB
	 * @return List<Pieza>
	 */
	public List<Pieza> getPieces() {
		List<Pieza> listPieza = new ArrayList<Pieza>();
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			listPieza = session.createQuery("FROM Pieza").list();
			if(listPieza.size()==0) {
				/*Create a default computer piece*/
				listPieza.add(new Pieza(0,"Default","Default"));
				return listPieza;
			}
			transaction.commit();
			
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
		}finally {
			session.close();
		}
		
		return listPieza;
	}
	
	/**
	 * Update a computer through its computer serial. NOTE: It only update the computer attributes, not its pieces in the DB.
	 * @param updatedComputer
	 * @param computerSerial
	 * @return true if the computer was successfully updated or false if it not
	 */
	public boolean updateComputer(Computador updatedComputer, int computerSerial) {
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
								
			Query query = session.createQuery("UPDATE Computador set serieComputador =: serieComputador, "
											+ "descripcionComputador =: descripcionComputador, "
											+ "fechaEnsamblajeComputador =: fechaEnsamblajeComputador "
											+ "WHERE idComputador =: idComputador");
			query.setParameter("serieComputador", updatedComputer.getSerieComputador());
			query.setParameter("descripcionComputador", updatedComputer.getDescripcionComputador());
			query.setParameter("fechaEnsamblajeComputador", updatedComputer.getFechaEnsamblajeComputador());
			query.setParameter("idComputador", searchComputer(computerSerial).getIdComputador());
			query.executeUpdate();
			
			transaction.commit();
			
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
			return false;
		}finally {
			session.close();
		}
		return true;
	}
	/**
	 * Retrieve a List of DetallePieza objects from the DB.
	 * @return List<DetallePieza>
	 */
	public List<DetallePieza> getCosts(){
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		List<DetallePieza> listDetallePieza = new ArrayList<DetallePieza>();
		try {
			transaction = session.beginTransaction();
			Query getCost = session.createQuery("SELECT c.descripcionComputador, SUM(dp.valorPiezaDetalle) "
												+ "FROM Computador c INNER JOIN DetallePieza dp "
												+ "ON c.idComputador = dp.idComputador "
												+ "GROUP BY c.descripcionComputador");
			
			/*It will return a Object List
			 * Example: [[Object 1],[Object 2],....]*/
			List result = getCost.list();
			if(result != null ) {
				for (int i = 0;i<result.size();i++) {
					/*Object can be cast as an Array
					 * Example: Object 1 = {Computer 1, Cost 1}
					 * 			Object 2 = {Computer 2, Cost 2}*/
					Object[] computerCostDetails = (Object[]) result.get(i);
					listDetallePieza.add(new DetallePieza((String)computerCostDetails[0],((Long) computerCostDetails[1]).intValue()));
				}
			}else {
				listDetallePieza = null;
				return listDetallePieza;
			}
			
			transaction.commit();
			
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
			return listDetallePieza;
		}finally {
			session.close();
		}
		return listDetallePieza;
	}
		
}


