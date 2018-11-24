package com.david.entities;

import javax.persistence.*;

/**
 * Represents a  piece detail and hibernate entity. 
 * @author David
 * @version 1.0
 * @since 1.0
 *
 */

@NamedNativeQueries({
	@NamedNativeQuery(
	name = "callSpPrueba",
	query = "CALL sp_prueba (:nombrePieza, :valorPieza, :serieComputador)"
	)
})

@Entity
@Table(name="DETALLE_PIEZAS")
public class DetallePieza {
	@Id
	@Column(name = "ID_DETALLE_PIEZA")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idDetallePieza;
	@Column(name="ID_PIEZA")
	private int idPieza;
	@Column(name="ID_COMPUTADOR")
	private int idComputador;
	@Column(name="PIEZA")
	private String detalleDePieza;
	@Column(name="VALOR")
	private int valorPiezaDetalle;
	
	public DetallePieza(){}
	
	public DetallePieza(String detalleDePieza, int valorPiezaDetalle) {
		super();
		this.detalleDePieza = detalleDePieza;
		this.valorPiezaDetalle = valorPiezaDetalle;
	}
	public int getIdDetallePieza() {
		return idDetallePieza;
	}
	public void setIdDetallePieza(int idDetallePieza) {
		this.idDetallePieza = idDetallePieza;
	}
	public int getIdPieza() {
		return idPieza;
	}
	public void setIdPieza(int idPieza) {
		this.idPieza = idPieza;
	}
	public int getIdComputador() {
		return idComputador;
	}
	public void setIdComputador(int idComputador) {
		this.idComputador = idComputador;
	}
	public String getDetalleDePieza() {
		return detalleDePieza;
	}
	public void setDetalleDePieza(String detalleDePieza) {
		this.detalleDePieza = detalleDePieza;
	}
	public int getValorPiezaDetalle() {
		return valorPiezaDetalle;
	}
	public void setValorPiezaDetalle(int valorPiezaDetalle) {
		this.valorPiezaDetalle = valorPiezaDetalle;
	}
	
	
}
