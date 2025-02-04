use parkingmonitor;

CREATE TABLE ReportImage (

	ID INTEGER PRIMARY KEY AUTO_INCREMENT,
    ReportID INTEGER NOT NULL,
    ImageURL TEXT NOT NULL,
    ImageType ENUM('LICENSE_PLATE', 'VIOLATION') NOT NULL,

    FOREIGN KEY (ReportID) REFERENCES Report(ID) ON DELETE CASCADE ON UPDATE CASCADE,
    INDEX ReportIDX(ReportID)
    
);