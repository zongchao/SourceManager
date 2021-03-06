USE [master]
GO
/****** Object:  Database [SourceManager]    Script Date: 07/31/2012 14:48:28 ******/
CREATE DATABASE [SourceManager] ON  PRIMARY 
( NAME = N'SourceManager', FILENAME = N'd:\Program Files\Microsoft SQL Server\MSSQL10_50.SQLEXPRESS\MSSQL\DATA\SourceManager.mdf' , SIZE = 3072KB , MAXSIZE = UNLIMITED, FILEGROWTH = 1024KB )
 LOG ON 
( NAME = N'SourceManager_log', FILENAME = N'd:\Program Files\Microsoft SQL Server\MSSQL10_50.SQLEXPRESS\MSSQL\DATA\SourceManager_log.ldf' , SIZE = 1024KB , MAXSIZE = 2048GB , FILEGROWTH = 10%)
GO
ALTER DATABASE [SourceManager] SET COMPATIBILITY_LEVEL = 100
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [SourceManager].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [SourceManager] SET ANSI_NULL_DEFAULT OFF
GO
ALTER DATABASE [SourceManager] SET ANSI_NULLS OFF
GO
ALTER DATABASE [SourceManager] SET ANSI_PADDING OFF
GO
ALTER DATABASE [SourceManager] SET ANSI_WARNINGS OFF
GO
ALTER DATABASE [SourceManager] SET ARITHABORT OFF
GO
ALTER DATABASE [SourceManager] SET AUTO_CLOSE OFF
GO
ALTER DATABASE [SourceManager] SET AUTO_CREATE_STATISTICS ON
GO
ALTER DATABASE [SourceManager] SET AUTO_SHRINK OFF
GO
ALTER DATABASE [SourceManager] SET AUTO_UPDATE_STATISTICS ON
GO
ALTER DATABASE [SourceManager] SET CURSOR_CLOSE_ON_COMMIT OFF
GO
ALTER DATABASE [SourceManager] SET CURSOR_DEFAULT  GLOBAL
GO
ALTER DATABASE [SourceManager] SET CONCAT_NULL_YIELDS_NULL OFF
GO
ALTER DATABASE [SourceManager] SET NUMERIC_ROUNDABORT OFF
GO
ALTER DATABASE [SourceManager] SET QUOTED_IDENTIFIER OFF
GO
ALTER DATABASE [SourceManager] SET RECURSIVE_TRIGGERS OFF
GO
ALTER DATABASE [SourceManager] SET  DISABLE_BROKER
GO
ALTER DATABASE [SourceManager] SET AUTO_UPDATE_STATISTICS_ASYNC OFF
GO
ALTER DATABASE [SourceManager] SET DATE_CORRELATION_OPTIMIZATION OFF
GO
ALTER DATABASE [SourceManager] SET TRUSTWORTHY OFF
GO
ALTER DATABASE [SourceManager] SET ALLOW_SNAPSHOT_ISOLATION OFF
GO
ALTER DATABASE [SourceManager] SET PARAMETERIZATION SIMPLE
GO
ALTER DATABASE [SourceManager] SET READ_COMMITTED_SNAPSHOT OFF
GO
ALTER DATABASE [SourceManager] SET HONOR_BROKER_PRIORITY OFF
GO
ALTER DATABASE [SourceManager] SET  READ_WRITE
GO
ALTER DATABASE [SourceManager] SET RECOVERY SIMPLE
GO
ALTER DATABASE [SourceManager] SET  MULTI_USER
GO
ALTER DATABASE [SourceManager] SET PAGE_VERIFY CHECKSUM
GO
ALTER DATABASE [SourceManager] SET DB_CHAINING OFF
GO
USE [SourceManager]
GO
/****** Object:  Table [dbo].[Users]    Script Date: 07/31/2012 14:48:31 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Users](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[username] [nvarchar](256) NULL,
	[password] [nvarchar](256) NULL,
	[userType] [int] NULL,
	[createTime] [datetime] NULL,
	[lastLoginTime] [datetime] NULL,
	[isDelete] [int] NULL,
 CONSTRAINT [PK_Users] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[SourceTypes]    Script Date: 07/31/2012 14:48:31 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[SourceTypes](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [nvarchar](256) NOT NULL,
 CONSTRAINT [PK_SourceTypes] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Sources]    Script Date: 07/31/2012 14:48:31 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Sources](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [nvarchar](256) NULL,
	[description] [nvarchar](max) NULL,
	[sourceImagePath] [nvarchar](256) NULL,
	[otherInfo] [nvarchar](256) NULL,
	[parentId] [int] NULL,
	[sourceTypeId] [int] NULL,
	[createTime] [datetime] NULL,
	[lastModifyTime] [datetime] NULL,
 CONSTRAINT [PK_Sources] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  ForeignKey [FK_Sources_Sources]    Script Date: 07/31/2012 14:48:31 ******/
ALTER TABLE [dbo].[Sources]  WITH CHECK ADD  CONSTRAINT [FK_Sources_Sources] FOREIGN KEY([parentId])
REFERENCES [dbo].[Sources] ([id])
GO
ALTER TABLE [dbo].[Sources] CHECK CONSTRAINT [FK_Sources_Sources]
GO
/****** Object:  ForeignKey [FK_Sources_SourceTypes]    Script Date: 07/31/2012 14:48:31 ******/
ALTER TABLE [dbo].[Sources]  WITH CHECK ADD  CONSTRAINT [FK_Sources_SourceTypes] FOREIGN KEY([sourceTypeId])
REFERENCES [dbo].[SourceTypes] ([id])
GO
ALTER TABLE [dbo].[Sources] CHECK CONSTRAINT [FK_Sources_SourceTypes]
GO
