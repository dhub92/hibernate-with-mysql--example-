create database prueba;
CREATE TABLE prueba.usuario (
        id INT NOT NULL AUTO_INCREMENT,
        USERNAME VARCHAR(30) NOT NULL,
        PASS VARCHAR(30),
        PRIMARY KEY (ID)
);

CREATE TABLE prueba.computador (
        ID_COMPUTADOR INT NOT NULL AUTO_INCREMENT,
        SERIE_COMPUTADOR INT NOT NULL,
        DESCRIPCION VARCHAR(30) NOT NULL,
        FECHA_ENSAMBLAJE INT NOT NULL,
        PRIMARY KEY (ID_COMPUTADOR)
);


CREATE TABLE prueba.detalle_piezas (
        ID_DETALLE_PIEZA INT NOT NULL AUTO_INCREMENT,
        ID_PIEZA INT NOT NULL,
        ID_COMPUTADOR INT NOT NULL,
        PIEZA VARCHAR(30) NOT NULL,
        VALOR INT NOT NULL,
        PRIMARY KEY (ID_DETALLE_PIEZA),
                
        CONSTRAINT FK_PIEZAS FOREIGN KEY (ID_PIEZA)
		REFERENCES PIEZAS(ID_PIEZA),
        
        CONSTRAINT FK_COMPUTADOR FOREIGN KEY (ID_COMPUTADOR)
		REFERENCES COMPUTADOR(ID_COMPUTADOR)
);

CREATE TABLE prueba.piezas (
        ID_PIEZA INT NOT NULL AUTO_INCREMENT,
        SERIE INT NOT NULL,
        NOMBRE VARCHAR(30) NOT NULL,
        CARACTERISTICA VARCHAR(30) NOT NULL,
        
        PRIMARY KEY (ID_PIEZA)
);

use prueba;
DELIMITER $$
CREATE procedure sp_prueba(in vNombre_pieza varchar(50), vValor INT, vSerie_computador INT)

begin
	declare sp_id_computador INT;
    declare sp_id_pieza INT;
    declare sp_nombre_pieza varchar(50);
    declare sp_valor INT;
    
	set @sp_nombre_pieza:= vNombre_pieza;  
    
    select @sp_id_computador := id_computador from prueba.computador where SERIE_COMPUTADOR = vSerie_computador;
    select @sp_id_pieza := id_pieza from prueba.piezas where nombre = vNombre_pieza;
        
	insert into prueba.detalle_piezas (id_pieza,id_computador,pieza,valor)
								values(@sp_id_pieza,@sp_id_computador,@sp_nombre_pieza,vValor);
    
end$$