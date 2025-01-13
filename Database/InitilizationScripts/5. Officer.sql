CREATE TABLE Officer (

    ID INTEGER PRIMARY KEY,
    IdentificationNumber VARCHAR(16) NOT NULL,
    DepartmentID INTEGER NOT NULL,
    
    FOREIGN KEY (ID) REFERENCES User(ID) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (DepartmentID) REFERENCES Department(ID) ON DELETE NO ACTION ON UPDATE NO ACTION,
    CHECK (CHAR_LENGTH(IdentificationNumber) >= 3),
    UNIQUE KEY UniqueIDDepartment(IdentificationNumber, DepartmentID),
    INDEX DepartmentIDX(DepartmentID)
    
);