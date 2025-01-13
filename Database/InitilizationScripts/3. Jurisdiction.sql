use parkingmonitor;

CREATE TABLE Jurisdiction (

    ID INTEGER PRIMARY KEY AUTO_INCREMENT,
    City VARCHAR(32) NOT NULL,
    StateInitials CHAR(2) NOT NULL,
    
    CHECK (CHAR_LENGTH(City) >= 3),
    CHECK (CHAR_LENGTH(StateInitials) = 2),
    UNIQUE KEY UniqueCityState(City, StateInitials),
    INDEX CityStateIDX(City, StateInitials)
    
);

