USE [BOOKSTORE]
GO
/****** Object:  User [BDAdmin]    Script Date: 12/20/2022 11:30:20 PM ******/
CREATE USER [BDAdmin] FOR LOGIN [BDAdmin] WITH DEFAULT_SCHEMA=[dbo]
GO
/****** Object:  User [BDU01]    Script Date: 12/20/2022 11:30:20 PM ******/
CREATE USER [BDU01] FOR LOGIN [BDU01] WITH DEFAULT_SCHEMA=[dbo]
GO
ALTER ROLE [db_owner] ADD MEMBER [BDAdmin]
GO
ALTER ROLE [db_datareader] ADD MEMBER [BDU01]
GO
/****** Object:  Table [dbo].[author]    Script Date: 12/20/2022 11:30:20 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[author](
	[id_author] [bigint] IDENTITY(1,1) NOT NULL,
	[author_name] [nvarchar](50) NULL,
 CONSTRAINT [PK_Author] PRIMARY KEY CLUSTERED 
(
	[id_author] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[book]    Script Date: 12/20/2022 11:30:20 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[book](
	[id_book] [bigint] IDENTITY(1,1) NOT NULL,
	[book_name] [nvarchar](45) NULL,
	[describe_book] [nvarchar](2000) NULL,
	[picture] [nvarchar](255) NULL,
	[price] [bigint] NULL,
	[publish_day] [date] NULL,
	[total_quantity] [int] NULL,
	[id_author] [bigint] NULL,
	[category_id] [bigint] NULL,
	[id_company] [bigint] NULL,
 CONSTRAINT [PK_Book] PRIMARY KEY CLUSTERED 
(
	[id_book] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[category]    Script Date: 12/20/2022 11:30:20 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[category](
	[category_id] [bigint] IDENTITY(1,1) NOT NULL,
	[category_name] [nvarchar](255) NULL,
 CONSTRAINT [PK_Category] PRIMARY KEY CLUSTERED 
(
	[category_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[company]    Script Date: 12/20/2022 11:30:20 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[company](
	[id_company] [bigint] IDENTITY(1,1) NOT NULL,
	[company_name] [nvarchar](255) NULL,
 CONSTRAINT [PK_Company] PRIMARY KEY CLUSTERED 
(
	[id_company] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[items]    Script Date: 12/20/2022 11:30:20 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[items](
	[item_id] [bigint] IDENTITY(1,1) NOT NULL,
	[quantity_books] [int] NULL,
	[id_book] [bigint] NULL,
	[user_id] [int] NULL,
 CONSTRAINT [PK] PRIMARY KEY CLUSTERED 
(
	[item_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[order_detail]    Script Date: 12/20/2022 11:30:20 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[order_detail](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[price] [int] NULL,
	[quantity] [int] NOT NULL,
	[id_book] [bigint] NULL,
	[order_id] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[orders]    Script Date: 12/20/2022 11:30:20 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[orders](
	[order_id] [int] IDENTITY(1,1) NOT NULL,
	[customer_name] [nvarchar](255) NULL,
	[order_day] [date] NULL,
	[order_status] [int] NULL,
	[customer_phone] [varchar](10) NULL,
	[total_price] [bigint] NULL,
	[address] [nvarchar](255) NULL,
	[user_id] [int] NULL,
 CONSTRAINT [PK_Orders] PRIMARY KEY CLUSTERED 
(
	[order_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[review]    Script Date: 12/20/2022 11:30:20 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[review](
	[id_book] [bigint] NOT NULL,
	[user_id] [int] NOT NULL,
	[comments] [nvarchar](200) NULL,
	[star] [float] NOT NULL,
	[time] [date] NULL,
 CONSTRAINT [PK_Review] PRIMARY KEY CLUSTERED 
(
	[id_book] ASC,
	[user_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[users]    Script Date: 12/20/2022 11:30:20 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[users](
	[user_id] [int] IDENTITY(1,1) NOT NULL,
	[age] [int] NOT NULL,
	[email] [varchar](255) NULL,
	[gender] [bit] NOT NULL,
	[password] [varchar](250) NULL,
	[phone] [varchar](10) NULL,
	[username] [varchar](255) NULL,
	[address] [nvarchar](255) NULL,
	[role] [int] NULL,
 CONSTRAINT [PK_Users] PRIMARY KEY CLUSTERED 
(
	[user_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
ALTER TABLE [dbo].[author] ADD  DEFAULT (NULL) FOR [author_name]
GO
ALTER TABLE [dbo].[book] ADD  DEFAULT (NULL) FOR [describe_book]
GO
ALTER TABLE [dbo].[book] ADD  DEFAULT (NULL) FOR [picture]
GO
ALTER TABLE [dbo].[book] ADD  DEFAULT (NULL) FOR [price]
GO
ALTER TABLE [dbo].[book] ADD  DEFAULT (NULL) FOR [publish_day]
GO
ALTER TABLE [dbo].[book] ADD  DEFAULT (NULL) FOR [total_quantity]
GO
ALTER TABLE [dbo].[book] ADD  DEFAULT (NULL) FOR [id_author]
GO
ALTER TABLE [dbo].[book] ADD  DEFAULT (NULL) FOR [category_id]
GO
ALTER TABLE [dbo].[book] ADD  DEFAULT (NULL) FOR [id_company]
GO
ALTER TABLE [dbo].[category] ADD  DEFAULT (NULL) FOR [category_name]
GO
ALTER TABLE [dbo].[company] ADD  DEFAULT (NULL) FOR [company_name]
GO
ALTER TABLE [dbo].[items] ADD  DEFAULT (NULL) FOR [quantity_books]
GO
ALTER TABLE [dbo].[items] ADD  DEFAULT (NULL) FOR [id_book]
GO
ALTER TABLE [dbo].[items] ADD  DEFAULT (NULL) FOR [user_id]
GO
ALTER TABLE [dbo].[order_detail] ADD  DEFAULT (NULL) FOR [price]
GO
ALTER TABLE [dbo].[order_detail] ADD  DEFAULT (NULL) FOR [id_book]
GO
ALTER TABLE [dbo].[order_detail] ADD  DEFAULT (NULL) FOR [order_id]
GO
ALTER TABLE [dbo].[orders] ADD  DEFAULT (NULL) FOR [customer_name]
GO
ALTER TABLE [dbo].[orders] ADD  DEFAULT (NULL) FOR [order_day]
GO
ALTER TABLE [dbo].[orders] ADD  DEFAULT (NULL) FOR [order_status]
GO
ALTER TABLE [dbo].[orders] ADD  DEFAULT (NULL) FOR [customer_phone]
GO
ALTER TABLE [dbo].[orders] ADD  DEFAULT (NULL) FOR [total_price]
GO
ALTER TABLE [dbo].[orders] ADD  DEFAULT (NULL) FOR [address]
GO
ALTER TABLE [dbo].[orders] ADD  DEFAULT (NULL) FOR [user_id]
GO
ALTER TABLE [dbo].[review] ADD  DEFAULT (NULL) FOR [comments]
GO
ALTER TABLE [dbo].[review] ADD  DEFAULT (NULL) FOR [time]
GO
ALTER TABLE [dbo].[users] ADD  DEFAULT (NULL) FOR [email]
GO
ALTER TABLE [dbo].[users] ADD  DEFAULT (NULL) FOR [password]
GO
ALTER TABLE [dbo].[users] ADD  DEFAULT (NULL) FOR [phone]
GO
ALTER TABLE [dbo].[users] ADD  DEFAULT (NULL) FOR [username]
GO
ALTER TABLE [dbo].[users] ADD  DEFAULT (NULL) FOR [address]
GO
ALTER TABLE [dbo].[users] ADD  DEFAULT ((0)) FOR [role]
GO
ALTER TABLE [dbo].[book]  WITH CHECK ADD  CONSTRAINT [FK_Author_Book] FOREIGN KEY([id_author])
REFERENCES [dbo].[author] ([id_author])
GO
ALTER TABLE [dbo].[book] CHECK CONSTRAINT [FK_Author_Book]
GO
ALTER TABLE [dbo].[book]  WITH CHECK ADD  CONSTRAINT [FK_Category_Book] FOREIGN KEY([category_id])
REFERENCES [dbo].[category] ([category_id])
GO
ALTER TABLE [dbo].[book] CHECK CONSTRAINT [FK_Category_Book]
GO
ALTER TABLE [dbo].[book]  WITH CHECK ADD  CONSTRAINT [FK_Company_Book] FOREIGN KEY([id_company])
REFERENCES [dbo].[company] ([id_company])
GO
ALTER TABLE [dbo].[book] CHECK CONSTRAINT [FK_Company_Book]
GO
ALTER TABLE [dbo].[items]  WITH CHECK ADD  CONSTRAINT [FK_Items_Book] FOREIGN KEY([id_book])
REFERENCES [dbo].[book] ([id_book])
GO
ALTER TABLE [dbo].[items] CHECK CONSTRAINT [FK_Items_Book]
GO
ALTER TABLE [dbo].[items]  WITH CHECK ADD  CONSTRAINT [FK_Items_Users] FOREIGN KEY([user_id])
REFERENCES [dbo].[users] ([user_id])
GO
ALTER TABLE [dbo].[items] CHECK CONSTRAINT [FK_Items_Users]
GO
ALTER TABLE [dbo].[order_detail]  WITH CHECK ADD  CONSTRAINT [FK_Detail_Book] FOREIGN KEY([id_book])
REFERENCES [dbo].[book] ([id_book])
GO
ALTER TABLE [dbo].[order_detail] CHECK CONSTRAINT [FK_Detail_Book]
GO
ALTER TABLE [dbo].[order_detail]  WITH CHECK ADD  CONSTRAINT [FK_Detail_Order] FOREIGN KEY([order_id])
REFERENCES [dbo].[orders] ([order_id])
GO
ALTER TABLE [dbo].[order_detail] CHECK CONSTRAINT [FK_Detail_Order]
GO
ALTER TABLE [dbo].[orders]  WITH CHECK ADD  CONSTRAINT [FK_Order_User] FOREIGN KEY([user_id])
REFERENCES [dbo].[users] ([user_id])
GO
ALTER TABLE [dbo].[orders] CHECK CONSTRAINT [FK_Order_User]
GO
ALTER TABLE [dbo].[review]  WITH CHECK ADD  CONSTRAINT [FK_Review_IDBook] FOREIGN KEY([id_book])
REFERENCES [dbo].[book] ([id_book])
GO
ALTER TABLE [dbo].[review] CHECK CONSTRAINT [FK_Review_IDBook]
GO
ALTER TABLE [dbo].[review]  WITH CHECK ADD  CONSTRAINT [FK_Review_Users] FOREIGN KEY([user_id])
REFERENCES [dbo].[users] ([user_id])
GO
ALTER TABLE [dbo].[review] CHECK CONSTRAINT [FK_Review_Users]
GO
