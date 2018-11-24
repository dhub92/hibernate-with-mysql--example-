package com.david.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * Represents a computer piece and hibernate entity
 * @author David Moreno
 * @version 1.0
 * @since 1.0
 *
 */
@Entity
@Table(name="PIEZAS")
public class Pieza {
	@Id
	@Column(name="ID_PIEZA")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idPieza;
	@Column(name="SERIE")
	private int seriePieza;
	@Column(name="NOMBRE")
	private String nombrePieza;
	@Column(name="CARACTERISTICA")
	private String caracteristicaPieza;
	
	public Pieza(){}
	
	public Pieza(int seriePieza, String nombrePieza, String caracteristicaPieza) {
		super();
		this.seriePieza = seriePieza;
		this.nombrePieza = nombrePieza;
		this.caracteristicaPieza = caracteristicaPieza;
	}
	public int getIdPieza() {
		return idPieza;
	}
	public void setIdPieza(int idPieza) {
		this.idPieza = idPieza;
	}
	public int getSeriePieza() {
		return seriePieza;
	}
	public void setSeriePieza(int seriePieza) {
		this.seriePieza = seriePieza;
	}
	public String getNombrePieza() {
		return nombrePieza;
	}
	public void setNombrePieza(String nombrePieza) {
		this.nombrePieza = nombrePieza;
	}
	public String getCaracteristicaPieza() {
		return caracteristicaPieza;
	}
	public void setCaracteristicaPieza(String caracteristicaPieza) {
		this.caracteristicaPieza = caracteristicaPieza;
	}
	
	

}
