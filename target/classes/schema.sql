USE Trello;

CREATE TABLE `User` (
                        `Id` BIGINT PRIMARY KEY,
                        `FullName` VARCHAR(500),
                        `Email` VARCHAR(320),
                        `TimeZoneId` INT,
                        `Password` VARCHAR(500),
                        `IsActive` BIT,
                        `CreatedOn` DATETIME
);

CREATE TABLE `Board` (
                         `Id` BIGINT PRIMARY KEY,
                         `Name` VARCHAR(50),
                         `IsActive` BIT,
                         `CreatedOn` DATETIME,
                         `CreatedBy` BIGINT,
                         `LastChangeOn` DATETIME,
                         `LastChangeBy` BIGINT
);

CREATE TABLE `TaskStatus` (
                              `Id` BIGINT PRIMARY KEY,
                              `BoardId` BIGINT,
                              `Name` VARCHAR(50),
                              `IsActive` BIT
);

CREATE TABLE `Task` (
                        `Id` BIGINT PRIMARY KEY,
                        `Name` VARCHAR(50),
                        `Description` VARCHAR(5000),
                        `TaskStatusId` BIGINT,
                        `IsActive` BIT,
                        `CreatedOn` DATETIME,
                        `CreatedBy` BIGINT,
                        `LastChangeOn` DATETIME,
                        `LastChangeBy` BIGINT
);

CREATE TABLE `TaskAssignee` (
                                `Id` BIGINT PRIMARY KEY,
                                `TaskId` BIGINT,
                                `UserId` BIGINT
);

CREATE TABLE `TaskComment` (
                               `Id` BIGINT PRIMARY KEY,
                               `Comment` VARCHAR(5000),
                               `TaskId` BIGINT,
                               `UserId` BIGINT,
                               `CreatedOn` DATETIME
);

ALTER TABLE `Board` ADD FOREIGN KEY (`CreatedBy`) REFERENCES `User` (`Id`);

ALTER TABLE `Board` ADD FOREIGN KEY (`LastChangeBy`) REFERENCES `User` (`Id`);

ALTER TABLE `TaskStatus` ADD FOREIGN KEY (`BoardId`) REFERENCES `Board` (`Id`);

ALTER TABLE `Task` ADD FOREIGN KEY (`CreatedBy`) REFERENCES `User` (`Id`);

ALTER TABLE `Task` ADD FOREIGN KEY (`LastChangeBy`) REFERENCES `User` (`Id`);

ALTER TABLE `Task` ADD FOREIGN KEY (`TaskStatusId`) REFERENCES `TaskStatus` (`Id`);

ALTER TABLE `TaskAssignee` ADD FOREIGN KEY (`TaskId`) REFERENCES `Task` (`Id`);

ALTER TABLE `TaskAssignee` ADD FOREIGN KEY (`UserId`) REFERENCES `User` (`Id`);

ALTER TABLE `TaskComment` ADD FOREIGN KEY (`TaskId`) REFERENCES `Task` (`Id`);

ALTER TABLE `TaskComment` ADD FOREIGN KEY (`UserId`) REFERENCES `User` (`Id`);
