use parkingmonitor;

CREATE VIEW ReportWithStatus AS
SELECT R.*,
CASE 
   WHEN R.ResolutionStatus IS NOT NULL THEN 'RESOLVED'
   WHEN R.RespondingOfficerID IS NOT NULL AND R.CreatedAt < DATE_SUB(CURRENT_TIMESTAMP, INTERVAL 2 HOUR) THEN 'TIMED-OUT'
   WHEN R.RespondingOfficerID IS NOT NULL THEN 'IN-PROCESS'
   ELSE 'OPEN'
END AS Status
FROM Report R;