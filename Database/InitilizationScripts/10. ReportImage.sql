use parkingmonitor;

CREATE TABLE ReportImage (

	ID INTEGER PRIMARY KEY AUTO_INCREMENT,
    ReportID INTEGER NOT NULL,
	BucketName VARCHAR(64) NOT NULL,
	ObjectKey VARCHAR(64) NOT NULL,
    ImageFormat ENUM('JPEG', 'PNG', 'WEBP', 'HEIC') NOT NULL,
    ImageSize INTEGER NOT NULL,
    ImageType ENUM('LICENSE_PLATE', 'VIOLATION') NOT NULL,

    FOREIGN KEY (ReportID) REFERENCES Report(ID) ON DELETE CASCADE ON UPDATE CASCADE,
    UNIQUE (BucketName, ObjectKey),
    CHECK (ImageSize > 0),
    INDEX ReportIDX(ReportID)
    
);