USE Trello;

CREATE TABLE `User` (
                        `Id` BIGINT AUTO_INCREMENT PRIMARY KEY,
                        `FullName` VARCHAR(500) NOT NULL,
                        `Email` VARCHAR(320) NOT NULL UNIQUE,
                        `TimeZoneId` INT NOT NULL,
                        `Password` VARCHAR(500) NOT NULL,
                        `IsActive` BIT NOT NULL,
                        `CreatedOn` DATETIME NOT NULL
);

CREATE TABLE `Board` (
                         `Id` BIGINT AUTO_INCREMENT PRIMARY KEY,
                         `Name` VARCHAR(50) NOT NULL,
                         `IsActive` BIT NOT NULL,
                         `CreatedOn` DATETIME NOT NULL,
                         `CreatedBy` BIGINT NOT NULL,
                         `LastChangeOn` DATETIME NOT NULL,
                         `LastChangeBy` BIGINT NOT NULL
);

ALTER TABLE `Board` ADD FOREIGN KEY `FK_Board_User_CreatedBy` (`CreatedBy`) REFERENCES `User` (`Id`);
ALTER TABLE `Board` ADD FOREIGN KEY `FK_Board_User_LastChangeBy` (`LastChangeBy`) REFERENCES `User` (`Id`);

CREATE TABLE `TaskStatus` (
                              `Id` BIGINT AUTO_INCREMENT PRIMARY KEY,
                              `BoardId` BIGINT NOT NULL,
                              `Name` VARCHAR(50) NOT NULL,
                              `IsActive` BIT NOT NULL
);

ALTER TABLE `TaskStatus` ADD FOREIGN KEY `FK_TaskStatus_Board` (`BoardId`) REFERENCES `Board` (`Id`);

CREATE TABLE `Task` (
                        `Id` BIGINT AUTO_INCREMENT PRIMARY KEY,
                        `BoardId` BIGINT NOT NULL,
                        `Name` VARCHAR(100) NOT NULL,
                        `Description` VARCHAR(5000) NOT NULL,
                        `TaskStatusId` BIGINT NOT NULL,
                        `IsActive` BIT NOT NULL,
                        `CreatedOn` DATETIME NOT NULL,
                        `CreatedBy` BIGINT NOT NULL,
                        `LastChangeOn` DATETIME NOT NULL,
                        `LastChangeBy` BIGINT NOT NULL
);

ALTER TABLE `Task` ADD FOREIGN KEY `FK_Task_Board` (`BoardId`) REFERENCES `Board` (`Id`);
ALTER TABLE `Task` ADD FOREIGN KEY `FK_Task_User_CreatedBy` (`CreatedBy`) REFERENCES `User` (`Id`);
ALTER TABLE `Task` ADD FOREIGN KEY `FK_Task_User_LastChangeBy` (`LastChangeBy`) REFERENCES `User` (`Id`);
ALTER TABLE `Task` ADD FOREIGN KEY `FK_Task_TaskStatus` (`TaskStatusId`) REFERENCES `TaskStatus` (`Id`);

CREATE TABLE `TaskAssignee` (
                                `Id` BIGINT AUTO_INCREMENT PRIMARY KEY,
                                `TaskId` BIGINT NOT NULL,
                                `UserId` BIGINT NOT NULL
);

ALTER TABLE `TaskAssignee` ADD FOREIGN KEY `FK_TaskAssignee_Task` (`TaskId`) REFERENCES `Task` (`Id`);
ALTER TABLE `TaskAssignee` ADD FOREIGN KEY `FK_TaskAssignee_User` (`UserId`) REFERENCES `User` (`Id`);
CREATE UNIQUE INDEX `UIX_TaskAssignee_TaskId_UserId` ON `TaskAssignee`(`TaskId`, `UserId`);

CREATE TABLE `TaskComment` (
                               `Id` BIGINT AUTO_INCREMENT PRIMARY KEY,
                               `Comment` VARCHAR(5000) NOT NULL,
                               `TaskId` BIGINT NOT NULL,
                               `UserId` BIGINT NOT NULL,
                               `CreatedOn` DATETIME NOT NULL
);

ALTER TABLE `TaskComment` ADD FOREIGN KEY `FK_TaskComment_Task` (`TaskId`) REFERENCES `Task` (`Id`);
ALTER TABLE `TaskComment` ADD FOREIGN KEY `FK_TaskComment_User` (`UserId`) REFERENCES `User` (`Id`);

CREATE TABLE `UserPermissionBoard`(
                                        `Id` BIGINT AUTO_INCREMENT PRIMARY KEY,
                                        `BoardId` BIGINT NOT NULL,
                                        `UserId` BIGINT NOT NULL,
                                        `CanView` BIT NOT NULL,
                                        `CanModify` BIT NOT NULL,
                                        `CanDelete` BIT NOT NULL
);

ALTER TABLE `UserPermissionBoard` ADD FOREIGN KEY `FK_UserPermissionBoard_User` (`UserId`) REFERENCES `User` (`Id`);
ALTER TABLE `UserPermissionBoard` ADD FOREIGN KEY `FK_UserPermissionBoard_Board` (`BoardId`) REFERENCES `Board` (`Id`);
CREATE UNIQUE INDEX `UIX_UserPermissionBoard_BoardId_UserId` ON `UserPermissionBoard`(`BoardId`, `UserId`);
