/**********************************/
/* Table Name: [Description] */
/**********************************/
-- DROP TABLE [TABLE_NAME];

CREATE TABLE [TABLE_NAME](
[DDL_COLUMN]
	REG_DT		DATETIME			DEFAULT	SYSDATETIME 	NOT NULL,
	RGTR_ID		VARCHAR(20)             					NOT NULL,
	MDFCN_DT	DATETIME			DEFAULT	SYSDATETIME  	NOT NULL,
	MDFR_ID		VARCHAR(20)									NOT NULL,
	DEL_YN	 	CHAR(1)				DEFAULT	'N'				NOT NULL
);
ALTER TABLE [TABLE_NAME] ADD CONSTRAINT IDX_[TABLE_NAME]_PK PRIMARY KEY ([FirstColumn]);

-- ID GENERATE TYPE
-- DELETE FROM JNITSEQ WHERE TABLE_NAME = '[IDGEN_TABLE_NAME]';
-- INSERT INTO JNITSEQ(TABLE_NAME, NEXT_ID) VALUES ('[IDGEN_TABLE_NAME]', '1');

-- SEQUENCE TYPE
-- DROP SERIAL SEQ_[TABLE_NAME];
CREATE SERIAL SEQ_[TABLE_NAME] START WITH 1 INCREMENT BY 1;