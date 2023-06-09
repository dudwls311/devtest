/**********************************/
/* Table Name: 부서관리 */
/**********************************/
-- DROP TABLE TB_DEPT;

CREATE TABLE TB_DEPT(
	DEPT_ID		INTEGER(20)		NOT NULL		,
	DEPT_NM		VARCHAR(40)		NOT NULL		,

	REG_DT		DATETIME			DEFAULT	SYSDATETIME 	NOT NULL,
	RGTR_ID		VARCHAR(20)             					NOT NULL,
	MDFCN_DT	DATETIME			DEFAULT	SYSDATETIME  	NOT NULL,
	MDFR_ID		VARCHAR(20)									NOT NULL,
	DEL_YN	 	CHAR(1)				DEFAULT	'N'				NOT NULL
);
ALTER TABLE TB_DEPT ADD CONSTRAINT IDX_TB_DEPT_PK PRIMARY KEY (DEPT_ID);

-- ID GENERATE TYPE
-- DELETE FROM JNITSEQ WHERE TABLE_NAME = 'EXTS_CPF_DPT';
-- INSERT INTO JNITSEQ(TABLE_NAME, NEXT_ID) VALUES ('EXTS_CPF_DPT', '1');

-- SEQUENCE TYPE
-- DROP SERIAL SEQ_TB_DEPT;
CREATE SERIAL SEQ_TB_DEPT START WITH 1 INCREMENT BY 1;
