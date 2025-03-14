use parkingmonitor;

CREATE TABLE ReportAddress (

	ID INTEGER PRIMARY KEY AUTO_INCREMENT,
    StreetLine VARCHAR(64) NOT NULL,
    JurisdictionID INTEGER NOT NULL,
    Zipcode VARCHAR(9) NOT NULL,
    Latitude DECIMAL(9,6) NOT NULL,
    Longitude DECIMAL(9,6) NOT NULL, 
	Notes VARCHAR(128) NULL,
    
    CHECK (CHAR_LENGTH(StreetLine) >= 4),
	CHECK (CHAR_LENGTH(Zipcode) >= 5),
	FOREIGN KEY (JurisdictionID) REFERENCES Jurisdiction(ID) ON DELETE NO ACTION ON UPDATE CASCADE,
    INDEX JurisdictionIDX(JurisdictionID)
    
);
