package com.david.hibernateMethods;

import java.util.ArrayList;
import java.util.List;

import com.david.entities.DetallePieza;
import com.david.entities.Pieza;
import com.david.entities.Usuario;
import com.david.entities.Computador;

public class MethodsTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		/**
		Usuario user = new Usuario("", ""); 
		
		CrudMethods methods = new CrudMethods();
		
		System.out.println("Valor "+methods.logIn(user.getNombreUsuario(),user.getContraseniaUsuario()));
		**/
		
		/**
		DetallePieza detallePieza1 = new DetallePieza("i3", 133);
		DetallePieza detallePieza2 = new DetallePieza("i7", 233);
		DetallePieza detallePieza3 = new DetallePieza("i5", 333);
		
		List<DetallePieza> list= new ArrayList<DetallePieza>();
		list.add(detallePieza1);
		list.add(detallePieza2);
		list.add(detallePieza3);
		
			
		QueryObjectDemo methods = new QueryObjectDemo();
		methods.newComputer(1992, "Computador dell", 2018, list);
		**/
		
		/**
		
		QueryObjectDemo methods = new QueryObjectDemo();
		methods.deleteComputer(methods.searchComputer(1010));;
		**/
		
		/**
		Computador updateComputer = new Computador(1993,"Asus Laptop",2019);
		QueryObjectDemo methods = new QueryObjectDemo();
		methods.updateComputer(updateComputer, 1992);**/
		
		/**CrudMethods methods = new CrudMethods();
		methods.getPieces();
		
		for(Pieza pieza : methods.getPieces())
			System.out.println(pieza.getNombrePieza());**/
		
		/**
		CrudMethods methods = new CrudMethods();
		methods.getCosts();
		**/
	}

}
