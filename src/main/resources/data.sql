INSERT INTO repuestos(marca,modelo,repuesto,valor) VALUES
 ('Bosch','m-1','Bujia',580.0),
 ('Bosch','m-1','Bujia',580.0),
 ('Bosch','m-1','Bujia',580.0),
 ('Bosch','m-1','Bujia',580.0);

 INSERT INTO empleados(apellido,celular,calle,codigo_postal,departamento,localidad,numero,piso,nombres,tipo_empleado) VALUES
 ('apellido',1234567,'calle',2552,'depto','localidad',866,0,'empleado1','administrativo'),
 ('apellido',1234567,'calle',2552,'depto','localidad',866,0,'empleado2','administrativo'),
 ('apellido',1234567,'calle',2552,'depto','localidad',866,0,'empleado3','administrativo'),
 ('apellido',1234567,'calle',2552,'depto','localidad',866,0,'empleado4','recepcionista'),
 ('apellido',1234567,'calle',2552,'depto','localidad',866,0,'empleado5','recepcionista'),
 ('apellido',1234567,'calle',2552,'depto','localidad',866,0,'empleado6','recepcionista');

 INSERT INTO vehiculos(anio,color,marca,modelo,patente) VALUES
 (2020,'color','marca','modelo','patente'),
 (2016,'color','marca','modelo','patente1'),
 (2016,'color','marca','modelo','patente2'),
 (2016,'color','marca','modelo','patente3'),
 (2016,'color','marca','modelo','patente4');

 INSERT INTO clientes(apellido,celular,calle,codigo_postal,departamento,localidad,numero,piso,correo_electronico,nombres,telefono_linea) VALUES
 ('apellido1',1234567,'calle',2552,'depto','localidad',866,0,'cliente1_mail@test.com','cliente1',444222),
 ('apellido2',1234567,'calle',2552,'depto','localidad',866,0,'cliente2_mail@test.com','cliente2',444222),
 ('apellido3',1234567,'calle',2552,'depto','localidad',866,0,'cliente3_mail@test.com','cliente3',444222),
 ('apellido4',1234567,'calle',2552,'depto','localidad',866,0,'cliente4_mail@test.com','cliente4',444222),
 ('apellido5',1234567,'calle',2552,'depto','localidad',866,0,'cliente5_mail@test.com','cliente5',444222);

 INSERT INTO mecanicos(activo,apellido,celular,calle,codigo_postal,departamento,localidad,numero,piso,especialidad,nombres) VALUES
 ('s','apellido',15512345,'calle',5521,'depto','localidad',866,0,'especialidad','mecanico1'),
 ('s','apellido',15512345,'calle',5521,'depto','localidad',866,0,'especialidad','mecanico2'),
 ('s','apellido',15512345,'calle',5521,'depto','localidad',866,0,'especialidad','mecanico3'),
 ('s','apellido',15512345,'calle',5521,'depto','localidad',866,0,'especialidad','mecanico4'),
 ('s','apellido',15512345,'calle',5521,'depto','localidad',866,0,'especialidad','mecanico5'),
 ('s','apellido',15512345,'calle',5521,'depto','localidad',866,0,'especialidad','mecanico6');

 INSERT INTO cliente_vehiculo(cliente_id,vehiculo_id) VALUES
 (1,1),
 (1,2),
 (2,2),
 (2,3),
 (3,3),
 (4,4),
 (5,5);

  INSERT INTO ordenes_trabajo(cantidad_cuotas,detalle_falla,estado,fecha_fin_reparacion,fecha_ingreso,fecha_pago,forma_pago,importe_total,kilometraje,nivel_combustible,tipo_tarjeta,administrativo_id,recepcionista_id,vehiculo_id) VALUES
  (6,'detalle falla','creada',null,null,null,'forma pago',123.3,123,'combustible','tipo tarjeta',1,2,1),
  (6,'detalle falla','En reparacion',null,null,null,'forma pago',123.3,123456,'combustible','tipo tarjeta',3,4,2),
  (6,'detalle falla','Para facturar',null,null,null,'forma pago',123.3,12345,'combustible','tipo tarjeta',5,2,3),
  (6,'detalle falla','Facturada',null,null,null,'forma pago',123.3,1234,'combustible','tipo tarjeta',1,4,4),
  (6,'detalle falla','creada',null,null,null,'forma pago',123.3,456123,'combustible','tipo tarjeta',3,2,5);


   INSERT INTO mano_obra(detalle,duracion_hs,mecanico_id,orden_trabajo_id) VALUES
   ('detalle1',null,1,1),
   ('detalle2',null,1,1),
   ('detalle3',null,2,2),
   ('detalle4',null,2,2),
   ('detalle5',null,3,3),
   ('detalle6',null,4,4),
   ('detalle7',null,5,5),
   ('detalle8',null,2,1),
   ('detalle9',null,1,2);

   INSERT INTO detalle_ordenes_trabajo(cantidad,valor_total,orden_trabajo_id,repuesto_id) VALUES
   (1,200.50,1,1),
   (2,200.50,1,2),
   (3,200.50,2,2),
   (2,200.50,2,1),
   (1,200.50,3,3),
   (1,200.50,4,4),
   (1,200.50,5,1);




