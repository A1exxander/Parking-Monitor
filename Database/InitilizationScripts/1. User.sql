use parkingmonitor;

CREATE TABLE User (

    ID INTEGER PRIMARY KEY AUTO_INCREMENT,
    FirstName VARCHAR(32) NOT NULL,
    LastName VARCHAR(32) NOT NULL,
    BirthDate DATE NOT NULL,
    ProfilePictureURL VARCHAR(2048) DEFAULT "https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460_1280.png",
    AccountType ENUM("USER", "OFFICER") NOT NULL DEFAULT "USER",
    CreatedAt TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
    UpdatedAt TIMESTAMP NULL ON UPDATE CURRENT_TIMESTAMP,
    
    CHECK (CHAR_LENGTH(FirstName) >= 2),
    CHECK (CHAR_LENGTH(LastName) >= 2),
    INDEX AccountTypeIDX(AccountType)
    
);