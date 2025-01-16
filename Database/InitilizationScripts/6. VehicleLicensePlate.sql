use parkingmonitor;

CREATE TABLE VehicleLicensePlate (

	ID INTEGER PRIMARY KEY AUTO_INCREMENT,
    Number VARCHAR(10) NOT NULL,
    StateInitials CHAR(2) NOT NULL,
    
	CHECK (CHAR_LENGTH(StateInitials) = 2),
	UNIQUE KEY UniqueLicensePlate(Number, StateInitials)
    
    
);