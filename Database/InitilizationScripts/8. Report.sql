use parkingmonitor;

CREATE TABLE Report (

	ID INTEGER PRIMARY KEY AUTO_INCREMENT,
    UserID INTEGER NOT NULL,
    VehicleID INTEGER NOT NULL,
    AddressID INTEGER NOT NULL,
    ViolationDescription VARCHAR(256) NULL, 
    RespondingOfficerID INTEGER NOT NULL,
    ResolutionStatus ENUM('APPROVED', 'DENIED') NULL,
    ResolutionNotes VARCHAR(64) NULL,
	CreatedAt TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
    UpdatedAt TIMESTAMP NULL ON UPDATE CURRENT_TIMESTAMP(),
    
    FOREIGN KEY (UserID) REFERENCES User(ID) ON DELETE NO ACTION ON UPDATE CASCADE,
    FOREIGN KEY (VehicleID) REFERENCES ReportVehicle(ID) ON DELETE NO ACTION ON UPDATE CASCADE,
	FOREIGN KEY (AddressID) REFERENCES ReportAddress(ID) ON DELETE NO ACTION ON UPDATE CASCADE,
    FOREIGN KEY (RespondingOfficerID) REFERENCES Officer(ID) ON DELETE NO ACTION ON UPDATE CASCADE,
    INDEX UserIDX(UserID),
    INDEX VehicleIDX(VehicleID),
    INDEX AddressIDX(AddressID),
    INDEX RespondingOfficerIDX(RespondingOfficerID),
    INDEX CreatedAtIDX(CreatedAt)
    
);