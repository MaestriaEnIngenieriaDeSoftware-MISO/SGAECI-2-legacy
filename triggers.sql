CREATE TRIGGER nuevo_estado_afiliacion
AFTER UPDATE
   ON Solicitud_Afiliacion FOR EACH ROW
BEGIN
    DECLARE
        tempDocumnentoID int;
    SELECT DocumentoID FROM Egresado where NEW.DocumentoID=Egresado.DocumentoID INTO tempDocumnentoID; 		
		
        IF(tempDocumnentoID IS NOT NULL)THEN
            IF((NEW.Estado_Solicitud="ACEPTADA")) THEN 
                IF ((SELECT DATEDIFF(SYSDATE(),Fecha_Graduacion) FROM Egresado WHERE NEW.DocumentoID=Egresado.DocumentoID)<=180) THEN 
                    INSERT INTO Estado_afiliacion (DocumentoID,Fecha_Inicio,Fecha_Fin,Estado)
                    VALUES
                    (NEW.DocumentoID,SYSDATE(),DATE_ADD(SYSDATE(),INTERVAL 180 DAY),"ACTIV0");
                ELSE
                    INSERT INTO Estado_afiliacion (DocumentoID,Fecha_Inicio,Fecha_Fin,Estado)
                    VALUES
                    (NEW.DocumentoID,SYSDATE(),DATE_ADD(SYSDATE(),INTERVAL 30 DAY),"INACTIVO - PENDIENTE PAGO CUOTA");
                END IF;
            ELSEIF ((NEW.Estado_Solicitud="RECHAZADA")) THEN 
                DELETE FROM PERSONA WHERE DocumentoID=tempDocumnentoID;
            END IF;
        ELSE 
            INSERT INTO Estado_afiliacion (DocumentoID,Fecha_Inicio,Fecha_Fin,Estado)
            VALUES
            (NEW.DocumentoID,SYSDATE(),DATE_ADD(SYSDATE(),INTERVAL 180 DAY),"ACTIVO");
        END IF;
END;
