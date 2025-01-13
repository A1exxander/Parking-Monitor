use parkingmonitor;

CREATE TABLE ReportVehicle (

	ID INTEGER PRIMARY KEY AUTO_INCREMENT,
    Manufacturer VARCHAR(16) NOT NULL,
    Model VARCHAR(32) NOT NULL,
    Year SMALLINT NOT NULL,
    Color VARCHAR(8) NULL, 
	LicensePlateID INTEGER NOT NULL,
    
	CHECK (CHAR_LENGTH(Manufacturer) >= 2),
    CHECK (CHAR_LENGTH(Model) >= 2),
	FOREIGN KEY (LicensePlateID) REFERENCES VehicleLicensePlate(ID) ON DELETE NO ACTION ON UPDATE NO ACTION,
    INDEX LicensePlateIDX(LicensePlateID)
    
);