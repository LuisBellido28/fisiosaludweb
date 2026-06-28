USE fisiosalud_db;

INSERT IGNORE INTO fisioterapeutas (nombre, apellido, cmp, especialidad, turno, telefono, activo) VALUES
('Carlos','Mendoza Rios','CMP-12345','Terapia Fisica','MAÑANA','987654321',true),
('Lucia','Torres Vargas','CMP-23456','Terapia Neurologica','TARDE','976543210',true),
('Roberto','Huanca Mamani','CMP-34567','Terapia Deportiva','AMBOS','965432109',true),
('Ana','Flores Quispe','CMP-45678','Terapia Respiratoria','MAÑANA','954321098',true);

INSERT IGNORE INTO pacientes (nombre, apellido, dni, fecha_nacimiento, telefono, correo, diagnostico_medico, activo) VALUES
('Juan','Garcia Lopez','12345678','1985-03-15','991234567','juan.garcia@gmail.com','Lumbalgia cronica con contractura muscular L4-L5',true),
('Maria','Quispe Huanca','23456789','1992-07-22','992345678','maria.quispe@gmail.com','Esguince de tobillo grado II post caida',true),
('Pedro','Ramirez Castro','34567890','1978-11-08','993456789','pedro.ramirez@gmail.com','Hemiplegia izquierda post ACV isquemico',true),
('Rosa','Condori Mamani','45678901','1965-05-30','994567890','rosa.condori@gmail.com','Artritis reumatoide en rodillas bilaterales',true),
('Luis','Villanueva Perez','56789012','1990-09-12','995678901','luis.villa@gmail.com','Tendinitis rotuliana rodilla derecha deportista',true),
('Carmen','Soto Benavides','67890123','1955-01-25','996789012','carmen.soto@gmail.com','EPOC moderado con fisioterapia respiratoria indicada',true),
('Jorge','Alvarado Nunez','78901234','1988-12-03','997890123','jorge.alv@gmail.com','Cervicalgia cronica con radiculopatia C5-C6',true),
('Sandra','Meza Roque','89012345','1975-08-17','998901234','sandra.meza@gmail.com','Post operatorio rodilla izquierda meniscectomia',true),
('Miguel','Herrera Tafur','90123456','2001-04-09','999012345','miguel.h@gmail.com','Escoliosis dorsal moderada 22 grados Cobb',true),
('Patricia','Lozano Cardenas','01234567','1969-06-14','900123456','patricia.l@gmail.com','Fibromialgia generalizada con puntos gatillo multiples',true);

INSERT IGNORE INTO equipos_insumos (nombre, tipo, cantidad, cantidad_minima, estado, costo_uso, activo) VALUES
('Ultrasonido terapeutico UF-500','EQUIPO',3,1,'DISPONIBLE',25.00,true),
('Electroterapia TENS/EMS dual','EQUIPO',4,1,'DISPONIBLE',20.00,true),
('Laser terapeutico 830nm','EQUIPO',2,1,'DISPONIBLE',30.00,true),
('Camilla hidraulica multifuncion','EQUIPO',5,2,'DISPONIBLE',10.00,true),
('Bicicleta estatica rehabilitacion','EQUIPO',2,1,'DISPONIBLE',15.00,true),
('Paralelas de marcha 2m','EQUIPO',1,1,'DISPONIBLE',8.00,true),
('Electrodos adhesivos 5x5cm','INSUMO',200,50,'DISPONIBLE',2.00,true),
('Gel conductor ultrasonido 250ml','INSUMO',30,5,'DISPONIBLE',3.50,true),
('Vendas elasticas 10cm x 4.5m','INSUMO',50,10,'DISPONIBLE',4.00,true),
('Guantes de latex (par)','INSUMO',100,20,'DISPONIBLE',1.50,true),
('Compresas calientes (bolsas)','INSUMO',20,5,'DISPONIBLE',5.00,true);

INSERT IGNORE INTO reservas (paciente_id, fisioterapeuta_id, fecha_hora, tipo_terapia, motivo, estado, creado_en) VALUES
(1,1,'2026-06-23 08:00:00','Terapia Fisica','Control lumbalgia semana 3','COMPLETADA',NOW()),
(2,1,'2026-06-23 09:00:00','Terapia Fisica','Rehabilitacion tobillo sesion 5','COMPLETADA',NOW()),
(3,2,'2026-06-23 10:00:00','Terapia Neurologica','Ejercicios neuromotores miembro superior','COMPLETADA',NOW()),
(4,1,'2026-06-24 08:00:00','Terapia Fisica','Movilizacion articular rodillas','COMPLETADA',NOW()),
(5,3,'2026-06-24 09:00:00','Terapia Deportiva','Fortalecimiento cuadriceps post lesion','COMPLETADA',NOW()),
(6,4,'2026-06-24 10:00:00','Terapia Respiratoria','Drenaje postural y ejercicios respiratorios','COMPLETADA',NOW()),
(7,1,'2026-06-25 08:00:00','Electroterapia','TENS cervical mas masoterapia','COMPLETADA',NOW()),
(8,1,'2026-06-25 09:00:00','Terapia Fisica','Rehabilitacion post meniscectomia sesion 4','COMPLETADA',NOW()),
(9,2,'2026-06-25 10:00:00','Terapia Fisica','Elongacion y correccion postural','COMPLETADA',NOW()),
(10,3,'2026-06-25 11:00:00','Masoterapia','Puntos gatillo fibromialgia','COMPLETADA',NOW()),
(1,1,'2026-06-27 08:00:00','Terapia Fisica','Control semanal lumbalgia','PENDIENTE',NOW()),
(2,1,'2026-06-27 09:00:00','Terapia Fisica','Seguimiento tobillo','PENDIENTE',NOW()),
(3,2,'2026-06-27 10:00:00','Terapia Neurologica','Sesion neuromotriz continuacion','PENDIENTE',NOW()),
(5,3,'2026-06-28 08:00:00','Terapia Deportiva','Retorno progresivo al deporte','PENDIENTE',NOW()),
(7,1,'2026-06-28 09:00:00','Electroterapia','Control cervicalgia','PENDIENTE',NOW());

INSERT IGNORE INTO sesiones_terapia (reserva_id, observaciones, evolucion, monto_cobrado, fecha_sesion) VALUES
(1,'Aplicacion TENS lumbar mas ultrasonido paravertebral. Contractura moderada.','Reduccion dolor 30 porciento. Mejora en flexion lumbar.',80.00,'2026-06-23'),
(2,'Crioterapia tobillo mas movilizacion pasiva. Edema residual leve.','Edema disminuyendo. Rango articular mejoro 15 grados.',70.00,'2026-06-23'),
(3,'Ejercicios Bobath miembro superior izquierdo. Facilitacion propioceptiva.','Mejoria en tono muscular. Coordinacion en progreso.',90.00,'2026-06-23'),
(4,'Termoterapia bilateral rodillas mas movilizacion articular pasiva.','Disminucion rigidez matutina. Paciente mas funcional.',75.00,'2026-06-24'),
(5,'Fortalecimiento cuadriceps con electroestimulacion mas cadena cerrada.','Fuerza mejorada. Sin dolor en actividades leves.',85.00,'2026-06-24'),
(6,'Drenaje postural mas espirometria incentivada mas tos asistida.','Saturacion mejoro a 96 porciento. Tolera esfuerzo fisico leve.',70.00,'2026-06-24'),
(7,'TENS burst cervical mas masoterapia trapecio y escalenos 20 minutos.','Contractura reducida. Cefalea tensional aliviada.',65.00,'2026-06-25'),
(8,'Ejercicios propiocepcion rodilla. Marcha en superficies inestables.','Marcha normalizada. Edema ausente.',90.00,'2026-06-25'),
(9,'Estiramientos columna mas Pilates basico mas reeducacion postural.','Curva escoliotica estable. Postura mejorada.',80.00,'2026-06-25'),
(10,'Presion isquemica en puntos gatillo. 8 puntos. Calor local.','Dolor difuso redujo 40 porciento. Sueno mejorado.',75.00,'2026-06-25');

INSERT IGNORE INTO detalle_sesion (sesion_id, equipo_insumo_id, cantidad_usada) VALUES
(1,2,2),(1,7,4),(1,8,1),
(2,4,1),(2,9,1),
(3,4,1),(3,10,2),
(4,4,1),(4,11,1),
(5,2,2),(5,7,4),
(6,4,1),(6,10,2),
(7,2,2),(7,7,2),(7,8,1),
(8,4,1),(8,9,1),
(9,4,1),(9,10,2),
(10,4,1),(10,11,1),(10,8,1);

SELECT CONCAT('Pacientes: ', COUNT(*)) AS resumen FROM pacientes
UNION ALL SELECT CONCAT('Fisioterapeutas: ', COUNT(*)) FROM fisioterapeutas
UNION ALL SELECT CONCAT('Equipos/Insumos: ', COUNT(*)) FROM equipos_insumos
UNION ALL SELECT CONCAT('Reservas: ', COUNT(*)) FROM reservas
UNION ALL SELECT CONCAT('Sesiones: ', COUNT(*)) FROM sesiones_terapia;
