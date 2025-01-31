use parkingmonitor;

CREATE TABLE ResolvedReport (

    ID INTEGER PRIMARY KEY,
    ResolutionStatus ENUM('APPROVED', 'DENIED') NOT NULL,
    ResolutionTimestamp TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
    Notes VARCHAR(64),
    
    FOREIGN KEY (ID) REFERENCES Report(ID)
    
);