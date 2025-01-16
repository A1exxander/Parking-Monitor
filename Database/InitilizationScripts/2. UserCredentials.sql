use parkingmonitor;

CREATE TABLE UserCredentials (

	ID INTEGER PRIMARY KEY,
    EmailAddress VARCHAR(320) UNIQUE NOT NULL,
	HashedPassword VARCHAR(256) NOT NULL,
    
    FOREIGN KEY (ID) REFERENCES User(ID) ON DELETE CASCADE ON UPDATE CASCADE,
    CHECK (CHAR_LENGTH(EmailAddress) >= 8),
    CHECK (CHAR_LENGTH(HashedPassword) >= 8),
    INDEX EmailAddressIDX(EmailAddress)
    
);