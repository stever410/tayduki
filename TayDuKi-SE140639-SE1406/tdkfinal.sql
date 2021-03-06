USE [master]
GO
/****** Object:  Database [TAYDUKI]    Script Date: 16-Jul-20 1:06:10 AM ******/
CREATE DATABASE [TAYDUKI]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'TAYDUKI', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL12.MSSQLSERVER\MSSQL\DATA\TAYDUKI.mdf' , SIZE = 3264KB , MAXSIZE = UNLIMITED, FILEGROWTH = 1024KB )
 LOG ON 
( NAME = N'TAYDUKI_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL12.MSSQLSERVER\MSSQL\DATA\TAYDUKI_log.ldf' , SIZE = 832KB , MAXSIZE = 2048GB , FILEGROWTH = 10%)
GO
ALTER DATABASE [TAYDUKI] SET COMPATIBILITY_LEVEL = 120
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [TAYDUKI].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [TAYDUKI] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [TAYDUKI] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [TAYDUKI] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [TAYDUKI] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [TAYDUKI] SET ARITHABORT OFF 
GO
ALTER DATABASE [TAYDUKI] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [TAYDUKI] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [TAYDUKI] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [TAYDUKI] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [TAYDUKI] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [TAYDUKI] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [TAYDUKI] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [TAYDUKI] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [TAYDUKI] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [TAYDUKI] SET  ENABLE_BROKER 
GO
ALTER DATABASE [TAYDUKI] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [TAYDUKI] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [TAYDUKI] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [TAYDUKI] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [TAYDUKI] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [TAYDUKI] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [TAYDUKI] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [TAYDUKI] SET RECOVERY FULL 
GO
ALTER DATABASE [TAYDUKI] SET  MULTI_USER 
GO
ALTER DATABASE [TAYDUKI] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [TAYDUKI] SET DB_CHAINING OFF 
GO
ALTER DATABASE [TAYDUKI] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [TAYDUKI] SET TARGET_RECOVERY_TIME = 0 SECONDS 
GO
ALTER DATABASE [TAYDUKI] SET DELAYED_DURABILITY = DISABLED 
GO
EXEC sys.sp_db_vardecimal_storage_format N'TAYDUKI', N'ON'
GO
USE [TAYDUKI]
GO
/****** Object:  UserDefinedFunction [dbo].[checkActorInFate]    Script Date: 16-Jul-20 1:06:10 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create function [dbo].[checkActorInFate] (@actorID as varchar(100), @fateID AS VARCHAR(100))
returns int
AS
BEGIN
	return (SELECT COUNT(actorID) FROM tblFateActorDetail WHERE actorID=@actorID AND fateID=@fateID)
END



GO
/****** Object:  UserDefinedFunction [dbo].[checkRoleNameInFate]    Script Date: 16-Jul-20 1:06:10 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create function [dbo].[checkRoleNameInFate] (@roleName as varchar(100), @fateID AS VARCHAR(100))
returns int
AS
BEGIN
	return (SELECT COUNT(roleName) FROM tblFateActorDetail WHERE roleName=@roleName AND fateID=@fateID)
END



GO
/****** Object:  UserDefinedFunction [dbo].[getRole]    Script Date: 16-Jul-20 1:06:10 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create function [dbo].[getRole] (@userID as varchar(100))
returns VARCHAR(10)
AS
BEGIN
	return (SELECT roleID FROM tblUser WHERE userID = @userID)
END



GO
/****** Object:  Table [dbo].[tblEquipment]    Script Date: 16-Jul-20 1:06:10 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[tblEquipment](
	[equipmentID] [varchar](100) NOT NULL,
	[equipmentName] [nvarchar](100) NOT NULL,
	[equipmentDescription] [nvarchar](100) NOT NULL,
	[equipmentImg] [nvarchar](2083) NULL,
	[equipmentAmount] [int] NOT NULL,
	[equipmentStatus] [bit] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[equipmentID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[tblFate]    Script Date: 16-Jul-20 1:06:10 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[tblFate](
	[fateID] [varchar](100) NOT NULL,
	[fateName] [nvarchar](100) NOT NULL,
	[fateDescription] [nvarchar](500) NULL,
	[shootPlace] [nvarchar](100) NOT NULL,
	[startTime] [date] NOT NULL,
	[endTime] [date] NOT NULL,
	[shootCount] [int] NOT NULL,
	[requirementFile] [nvarchar](2083) NULL,
	[fateStatus] [bit] NOT NULL,
	[directorID] [varchar](100) NULL,
PRIMARY KEY CLUSTERED 
(
	[fateID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[tblFateActorDetail]    Script Date: 16-Jul-20 1:06:10 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[tblFateActorDetail](
	[fateID] [varchar](100) NULL,
	[actorID] [varchar](100) NULL,
	[roleName] [nvarchar](100) NOT NULL,
	[roleDescription] [nvarchar](500) NOT NULL,
	[addDate] [date] NOT NULL,
	[commiterID] [varchar](100) NULL
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[tblFateEquipmentDetail]    Script Date: 16-Jul-20 1:06:10 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[tblFateEquipmentDetail](
	[fateID] [varchar](100) NULL,
	[equipmentID] [varchar](100) NULL,
	[equipmentAmount] [int] NOT NULL,
	[addDate] [date] NOT NULL,
	[commiterID] [varchar](100) NULL
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[tblNotification]    Script Date: 16-Jul-20 1:06:10 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[tblNotification](
	[notificationID] [int] IDENTITY(1,1) NOT NULL,
	[userID] [varchar](100) NULL,
	[notificationDesc] [nvarchar](1000) NOT NULL,
	[notificationStatus] [bit] NOT NULL,
	[commiterID] [varchar](100) NULL,
	[createdTime] [datetime] NULL,
PRIMARY KEY CLUSTERED 
(
	[notificationID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[tblRole]    Script Date: 16-Jul-20 1:06:10 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[tblRole](
	[roleID] [varchar](10) NOT NULL,
	[roleName] [nvarchar](100) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[roleID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[tblUser]    Script Date: 16-Jul-20 1:06:10 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[tblUser](
	[userID] [varchar](100) NOT NULL,
	[roleID] [varchar](10) NULL,
	[userFullname] [nvarchar](100) NOT NULL,
	[userSex] [char](1) NOT NULL,
	[userPassword] [varchar](100) NOT NULL,
	[userPhone] [varchar](10) NOT NULL,
	[userEmail] [varchar](200) NOT NULL,
	[userDescription] [nvarchar](500) NULL,
	[userImg] [nvarchar](2083) NULL,
	[userStatus] [bit] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[userID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY],
UNIQUE NONCLUSTERED 
(
	[userEmail] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY],
UNIQUE NONCLUSTERED 
(
	[userPhone] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
ALTER TABLE [dbo].[tblFate]  WITH CHECK ADD FOREIGN KEY([directorID])
REFERENCES [dbo].[tblUser] ([userID])
GO
ALTER TABLE [dbo].[tblFateActorDetail]  WITH CHECK ADD FOREIGN KEY([actorID])
REFERENCES [dbo].[tblUser] ([userID])
GO
ALTER TABLE [dbo].[tblFateActorDetail]  WITH CHECK ADD FOREIGN KEY([commiterID])
REFERENCES [dbo].[tblUser] ([userID])
GO
ALTER TABLE [dbo].[tblFateActorDetail]  WITH CHECK ADD FOREIGN KEY([fateID])
REFERENCES [dbo].[tblFate] ([fateID])
GO
ALTER TABLE [dbo].[tblFateEquipmentDetail]  WITH CHECK ADD FOREIGN KEY([commiterID])
REFERENCES [dbo].[tblUser] ([userID])
GO
ALTER TABLE [dbo].[tblFateEquipmentDetail]  WITH CHECK ADD FOREIGN KEY([equipmentID])
REFERENCES [dbo].[tblEquipment] ([equipmentID])
GO
ALTER TABLE [dbo].[tblFateEquipmentDetail]  WITH CHECK ADD FOREIGN KEY([fateID])
REFERENCES [dbo].[tblFate] ([fateID])
GO
ALTER TABLE [dbo].[tblNotification]  WITH CHECK ADD FOREIGN KEY([commiterID])
REFERENCES [dbo].[tblUser] ([userID])
GO
ALTER TABLE [dbo].[tblNotification]  WITH CHECK ADD FOREIGN KEY([userID])
REFERENCES [dbo].[tblUser] ([userID])
GO
ALTER TABLE [dbo].[tblUser]  WITH CHECK ADD FOREIGN KEY([roleID])
REFERENCES [dbo].[tblRole] ([roleID])
GO
ALTER TABLE [dbo].[tblEquipment]  WITH CHECK ADD CHECK  (([equipmentAmount]>=(0)))
GO
ALTER TABLE [dbo].[tblFate]  WITH CHECK ADD  CONSTRAINT [constFateDirector] CHECK  (([dbo].[getRole]([directorID])='DIR'))
GO
ALTER TABLE [dbo].[tblFate] CHECK CONSTRAINT [constFateDirector]
GO
ALTER TABLE [dbo].[tblFateActorDetail]  WITH CHECK ADD CHECK  (([dbo].[getRole]([commiterID])='DIR'))
GO
ALTER TABLE [dbo].[tblFateActorDetail]  WITH CHECK ADD  CONSTRAINT [const_checkRoleNameInFate] CHECK  (([dbo].[checkRoleNameInFate]([roleName],[fateID])=(1)))
GO
ALTER TABLE [dbo].[tblFateActorDetail] CHECK CONSTRAINT [const_checkRoleNameInFate]
GO
ALTER TABLE [dbo].[tblFateActorDetail]  WITH CHECK ADD  CONSTRAINT [constActorFateActor] CHECK  (([dbo].[getRole]([actorID])='ACT'))
GO
ALTER TABLE [dbo].[tblFateActorDetail] CHECK CONSTRAINT [constActorFateActor]
GO
ALTER TABLE [dbo].[tblFateEquipmentDetail]  WITH CHECK ADD CHECK  (([dbo].[getRole]([commiterID])='DIR'))
GO
ALTER TABLE [dbo].[tblFateEquipmentDetail]  WITH CHECK ADD CHECK  (([equipmentAmount]>=(0)))
GO
ALTER TABLE [dbo].[tblUser]  WITH CHECK ADD CHECK  (([userSex]='M' OR [userSex]='F' OR [userSex]='O'))
GO
USE [master]
GO
ALTER DATABASE [TAYDUKI] SET  READ_WRITE 
GO
