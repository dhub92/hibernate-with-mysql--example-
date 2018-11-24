package com.david.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * Represents a computer and hibernate entity
 * @author David Moreno
 * @version 1.0
 * @since 1.0
 *
 */

@Entity
@Table(name="COMPUTADOR")
public class Computador {
	@Id
	@Column(name = "ID_COMPUTADOR")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idComputador;
	@Column(name = "SERIE_COMPUTADOR")
	private int serieComputador;
	@Column(name = "DESCRIPCION")
	private String descripcionComputador;
	@Column(name = "FECHA_ENSAMBLAJE")
	private int fechaEnsamblajeComputador;
	
	public Computador() {}
	
	public Computador(int serieComputador, String descripcionComputador, int fechaEnsamblajeComputador) {
		super();
		this.serieComputador = serieComputador;
		this.descripcionComputador = descripcionComputador;
		this.fechaEnsamblajeComputador = fechaEnsamblajeComputador;
	}
	
	public int getIdComputador() {
		return idComputador;
	}
	
	public void setIdComputador(int idComputador) {
		this.idComputador = idComputador;
	}
	
	public int getSerieComputador() {
		return serieComputador;
	}
	
	public void setSerieComputador(int serieComputador) {
		this.serieComputador = serieComputador;
	}
	public String getDescripcionComputador() {
		return descripcionComputador;
	}
	public void setDescripcionComputador(String descripcionComputador) {
		this.descripcionComputador = descripcionComputador;
	}
	public int getFechaEnsamblajeComputador() {
		return fechaEnsamblajeComputador;
	}
	public void setFechaEnsamblajeComputador(int fechaEnsamblajeComputador) {
		this.fechaEnsamblajeComputador = fechaEnsamblajeComputador;
	}
	
	

}
