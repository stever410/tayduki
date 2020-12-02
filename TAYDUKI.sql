CREATE DATABASE TAYDUKI
GO

USE TAYDUKI
GO

CREATE TABLE tblRole (
	roleID VARCHAR(10) PRIMARY KEY,
	roleName NVARCHAR(100) NOT null
)

CREATE TABLE tblUser (
	userID VARCHAR(100) PRIMARY KEY,
	roleID VARCHAR(10) FOREIGN KEY REFERENCES dbo.tblRole(roleID),
	userFullname NVARCHAR(100) NOT NULL,
	userSex CHAR(1) NOT NULL CHECK (userSex = 'M' OR userSex='F' OR userSex='O'),
	userPassword VARCHAR(100) NOT NULL,
	userPhone VARCHAR(10) UNIQUE NOT NULL,
	userEmail VARCHAR(200) UNIQUE NOT NULL,
	userDescription NVARCHAR(500),
	userImg NVARCHAR(2083),
	userStatus BIT NOT NULL
)

CREATE TABLE tblEquipment (
	equipmentID VARCHAR(100) PRIMARY KEY,
	equipmentName NVARCHAR(100) NOT NULL,
	equipmentDescription NVARCHAR(100) NOT NULL,
	equipmentImg NVARCHAR(2083),
	equipmentAmount INT NOT NULL CHECK(equipmentAmount >= 0),
	equipmentStatus BIT NOT NULL
)

CREATE TABLE tblFate (
	fateID VARCHAR(100) PRIMARY KEY,
	fateName NVARCHAR(100) NOT NULL,
	fateDescription NVARCHAR(500),
	shootPlace NVARCHAR(100) NOT NULL,
	startTime DATE NOT NULL,
	endTime DATE NOT NULL,
	shootCount INT NOT NULL,
	requirementFile NVARCHAR(2083),
	fateStatus BIT NOT NULL,
	directorID VARCHAR(100) FOREIGN KEY REFERENCES dbo.tblUser(userID),
	CONSTRAINT constFateDirector CHECK (dbo.getRole(directorID) = 'DIR') 
)

CREATE TABLE tblFateEquipmentDetail (
	fateID VARCHAR(100) FOREIGN KEY REFERENCES dbo.tblFate(fateID),
	equipmentID VARCHAR(100) FOREIGN KEY REFERENCES dbo.tblEquipment(equipmentID),
	equipmentAmount INT NOT NULL CHECK(equipmentAmount >= 0),
	addDate DATE NOT NULL
)
ALTER TABLE dbo.tblFateEquipmentDetail
ADD commiterID VARCHAR(100) FOREIGN KEY REFERENCES dbo.tblUser(userID) CHECK (dbo.getRole(commiterID) = 'DIR')

SELECT * FROM dbo.tblFateEquipmentDetail

CREATE TABLE tblFateActorDetail (
	fateID VARCHAR(100) FOREIGN KEY REFERENCES dbo.tblFate(fateID),
	actorID VARCHAR(100) FOREIGN KEY REFERENCES dbo.tblUser(userID),
	roleName NVARCHAR(100) NOT NULL,
	roleDescription NVARCHAR(500) NOT NULL,
	addDate DATE NOT NULL,
	CONSTRAINT constFateActor CHECK (dbo.getRole(actorID) = 'ACT')
)

ALTER TABLE dbo.tblFateActorDetail
ADD commiterID VARCHAR(100) FOREIGN KEY REFERENCES dbo.tblUser(userID) CHECK (dbo.getRole(commiterID) = 'DIR')

CREATE TABLE tblNotification (
	notificationID INT IDENTITY(1,1) PRIMARY KEY,
	userID VARCHAR(100) FOREIGN KEY REFERENCES dbo.tblUser(userID),
	notificationDesc nvarchar(1000) NOT NULL,
	notificationStatus BIT NOT NULL,
	commiterID VARCHAR(100) FOREIGN KEY REFERENCES dbo.tblUser(userID),
)
ALTER TABLE dbo.tblNotification
ADD createdTime DATETIME

INSERT INTO dbo.tblRole(roleID, roleName)
VALUES  ('ADM', 'Admin'), ('ACT', 'Actor'), ('DIR', 'Director')

ALTER TABLE dbo.tblFateActorDetail
ADD CONSTRAINT const_checkActorInFate CHECK(dbo.checkActorInFate(actorID,fateID)=1)

ALTER TABLE dbo.tblFateActorDetail
ADD CONSTRAINT const_checkRoleNameInFate CHECK(dbo.checkRoleNameInFate(roleName,fateID)=1)

GO
create function getRole (@userID as varchar(100))
returns VARCHAR(10)
AS
BEGIN
	return (SELECT roleID FROM tblUser WHERE userID = @userID)
END
GO

GO
create function checkActorInFate (@actorID as varchar(100), @fateID AS VARCHAR(100))
returns int
AS
BEGIN
	return (SELECT COUNT(actorID) FROM tblFateActorDetail WHERE actorID=@actorID AND fateID=@fateID)
END
GO

GO
create function checkRoleNameInFate (@roleName as varchar(100), @fateID AS VARCHAR(100))
returns int
AS
BEGIN
	return (SELECT COUNT(roleName) FROM tblFateActorDetail WHERE roleName=@roleName AND fateID=@fateID)
END
GO

INSERT dbo.tblUser(userID ,
          roleID ,
          userFullname ,
          userSex ,
          userPassword ,
          userPhone ,
          userEmail ,
          userDescription ,
          userImg ,
          userStatus
        )
VALUES  ( 'admin' , -- userID - varchar(100)
          'ADM' , -- roleID - varchar(10)
          N'Ngô Tấn Đức' , -- userFullname - nvarchar(100)
          'M' , -- userSex - char(1)
          'admin' , -- userPassword - varchar(100)
          '0862345765' , -- userPhone - varchar(20)
          N'admin@gmail.com' , -- userEmail - nvarchar(200)
          N'Đẹp trai' , -- userDescription - nvarchar(500)
          N'admin.jpg' , -- userImg - nvarchar(2083)
          1  -- userStatus - bit
        )

SELECT userID, roleID, userFullname, userSex, userPhone, userEmail, userDescription, userImg, userStatus
FROM dbo.tblUser
WHERE userID != 'admin' AND userStatus=1
ORDER BY userFullname
OFFSET 5 ROWS
FETCH NEXT 5 ROWS ONLY

INSERT INTO dbo.tblFate(fateID, fateName, fateDescription, shootPlace, startTime, endTime, shootCount, fateStatus, directorID)
VALUES  (?, ?, ?, ?, ?, ?, ?, ?, ?)

UPDATE dbo.tblUser
SET userStatus = 1

INSERT INTO dbo.tblFateActorDetail( fateID ,actorID ,roleName , roleDescription , addDate , directorID)
VALUES  ( ?, ?, ?, ?, GETDATE(), ?)

SELECT tblEquipment.equipmentID, fateID, addDate, equipmentStatus, commiterID
FROM dbo.tblEquipment JOIN dbo.tblFateEquipmentDetail ON tblFateEquipmentDetail.equipmentID = tblEquipment.equipmentID
WHERE equipmentStatus=1 AND (addDate BETWEEN '1/1/2000' AND '12/12/2020')
ORDER BY tblEquipment.equipmentID
OFFSET 2 ROWS
FETCH NEXT 2 ROWS ONLY

SELECT tblFate.fateID, roleName, roleDescription, startTime, endTime, requirementFile
FROM dbo.tblFate JOIN dbo.tblFateActorDetail ON tblFateActorDetail.fateID = tblFate.fateID
WHERE actorID='' AND endTime < GETDATE() AND fateStatus=1

SELECT tblFate.fateID, roleName, roleDescription, startTime, endTime, requirementFile
FROM dbo.tblFate JOIN dbo.tblFateActorDetail ON tblFateActorDetail.fateID = tblFate.fateID
WHERE actorID='tamtang'
GROUP BY tblFate.fateID

CREATE TRIGGER tr_insertUserNotification ON tblUser 
AFTER INSERT AS
BEGIN
	DECLARE @adminID varchar(100) = (SELECT userID FROM dbo.tblUser WHERE roleID='ADM')
	DECLARE @userID VARCHAR(100) = (SELECT userID FROM inserted)

	INSERT INTO dbo.tblNotification(userID,notificationDesc,notificationStatus,commiterID,createdTime)
	VALUES(@userID, CONCAT('You have been created by ', @adminID), 1, @adminID, GETDATE())
END

CREATE TRIGGER tr_updateUserNotification ON tblUser 
AFTER UPDATE AS
BEGIN
	DECLARE @adminID varchar(100) = (SELECT userID FROM dbo.tblUser WHERE roleID='ADM')
	DECLARE @userID VARCHAR(100) = (SELECT userID FROM inserted)

	INSERT INTO dbo.tblNotification(userID,notificationDesc,notificationStatus,commiterID,createdTime)
	VALUES(@userID, CONCAT('You have been updated by ', @adminID), 1, @adminID, GETDATE())
END

CREATE TRIGGER tr_insertEquipmentNotification ON tblEquipment 
AFTER INSERT AS
BEGIN
	DECLARE @adminID varchar(100) = (SELECT userID FROM dbo.tblUser WHERE roleID='ADM')
	DECLARE @equipmentID VARCHAR(100) = (SELECT equipmentID FROM inserted)
	DECLARE @directorID VARCHAR(100)
	DECLARE @recordToSkip INT = 0
	DECLARE @directorSize INT = (SELECT COUNT(userID) FROM dbo.tblUser WHERE roleID='DIR')

	WHILE @recordToSkip < @directorSize
	BEGIN
		SET @directorID = (SELECT userID 
						FROM dbo.tblUser
						WHERE roleID='DIR'
						ORDER BY userID
						OFFSET @recordToSkip ROWS 
						FETCH NEXT 1 ROWS ONLY)
		INSERT INTO dbo.tblNotification(userID,notificationDesc,notificationStatus,commiterID,createdTime)
		VALUES(@directorID, CONCAT(@equipmentID, ' has been added by ', @adminID), 1, @adminID, GETDATE())
		SET @recordToSkip = @recordToSkip + 1
	END
END

CREATE TRIGGER tr_updateEquipmentNotification ON tblEquipment 
AFTER update AS
BEGIN
	DECLARE @adminID varchar(100) = (SELECT userID FROM dbo.tblUser WHERE roleID='ADM')
	DECLARE @equipmentID VARCHAR(100) = (SELECT equipmentID FROM inserted)
	DECLARE @directorID VARCHAR(100)
	DECLARE @recordToSkip INT = 0
	DECLARE @directorSize INT = (SELECT COUNT(userID) FROM dbo.tblUser WHERE roleID='DIR')

	WHILE @recordToSkip < @directorSize
	BEGIN
		SET @directorID = (SELECT userID 
						FROM dbo.tblUser
						WHERE roleID='DIR'
						ORDER BY userID
						OFFSET @recordToSkip ROWS 
						FETCH NEXT 1 ROWS ONLY)
		INSERT INTO dbo.tblNotification(userID,notificationDesc,notificationStatus,commiterID,createdTime)
		VALUES(@directorID, CONCAT(@equipmentID, ' has been updated by ', @adminID), 1, @adminID, GETDATE())
		SET @recordToSkip = @recordToSkip + 1
	END
END

CREATE TRIGGER tr_insertFateNotification ON tblFate
AFTER insert AS
BEGIN
	DECLARE @adminID varchar(100) = (SELECT userID FROM dbo.tblUser WHERE roleID='ADM')
	DECLARE @fateID VARCHAR(100) = (SELECT fateID FROM inserted)
	DECLARE @directorID VARCHAR(100) = (SELECT directorID FROM inserted)

	INSERT INTO dbo.tblNotification(userID,notificationDesc,notificationStatus,commiterID,createdTime)
	VALUES(@directorID, CONCAT('You have been added to ', @fateID, ' by ', @adminID), 1, @adminID, GETDATE())
END

CREATE TRIGGER tr_updateFateNotification ON tblFate
AFTER update AS
BEGIN
	DECLARE @adminID varchar(100) = (SELECT userID FROM dbo.tblUser WHERE roleID='ADM')
	DECLARE @fateID VARCHAR(100) = (SELECT fateID FROM inserted)
	DECLARE @directorID VARCHAR(100) = (SELECT directorID FROM inserted)
	DECLARE @recordToSkip INT = 0
	DECLARE @actorID VARCHAR(100)
	DECLARE @actorSize INT = (SELECT COUNT(actorID) FROM dbo.tblFateActorDetail WHERE fateID=@fateID)

	INSERT INTO dbo.tblNotification(userID,notificationDesc,notificationStatus,commiterID,createdTime)
	VALUES(@directorID, CONCAT(@fateID, ' has been updated by ', @adminID), 1, @adminID, GETDATE())

	WHILE @recordToSkip < @actorSize
	BEGIN
		SET @actorID = (SELECT DISTINCT actorID 
						FROM dbo.tblFateActorDetail 
						WHERE fateID=@fateID 
						ORDER BY actorID 
						OFFSET @recordToSkip ROWS 
						FETCH NEXT 1 ROWS ONLY)
		INSERT INTO dbo.tblNotification(userID,notificationDesc,notificationStatus,commiterID,createdTime)
		VALUES(@actorID, CONCAT(@fateID, ' has been updated by ', @adminID), 1, @adminID, GETDATE())
		SET @recordToSkip = @recordToSkip + 1
	END
END

DBCC CHECKIDENT ('tblNotification', RESEED, 9)

CREATE TRIGGER tr_insertFateActorNotification ON tblFateActorDetail
AFTER INSERT AS
BEGIN
	DECLARE @fateID VARCHAR(100) = (SELECT fateID FROM inserted)
	DECLARE @directorID VARCHAR(100) = (SELECT commiterID FROM inserted)
	DECLARE @recordToSkip INT = 0
	DECLARE @actorID VARCHAR(100)
	DECLARE @cartSize INT = (SELECT COUNT(actorID) FROM inserted)
	DECLARE @actorSize INT = (SELECT COUNT(actorID) FROM dbo.tblFateActorDetail WHERE fateID=@fateID)

	WHILE @recordToSkip < @cartSize
	BEGIN
		SET @actorID = (SELECT DISTINCT actorID 
						FROM inserted
						ORDER BY actorID 
						OFFSET @recordToSkip ROWS 
						FETCH NEXT 1 ROWS ONLY)
		INSERT INTO dbo.tblNotification(userID,notificationDesc,notificationStatus,commiterID,createdTime)
		VALUES(@actorID, CONCAT('You have been added to ', @fateID, ' by ', @directorID), 1, @directorID, GETDATE())
		SET @recordToSkip = @recordToSkip + 1
	END
END

SELECT TOP 10 notificationDesc, notificationStatus, commiterID, createdTime
FROM dbo.tblNotification
WHERE userID = 'tamtang'
ORDER BY createdTime DESC