/**********************************/
/* Table Name: 메뉴테이블 */
/**********************************/
CREATE TABLE JNITCMSMENUTREE(
		MENU_CODE_ID                       	VARCHAR2(100)		 DEFAULT 0		 NOT NULL,
		MENU_ID                       		VARCHAR2(100)		 DEFAULT 0		 NOT NULL,
		MENU_NAME                     		VARCHAR2(200)		 NULL ,
		MENU_LINK                     		VARCHAR2(255)		 NULL ,
		MENU_DEPTH                    		NUMBER(1)		 DEFAULT 1		 NULL ,
		MENU_ICON                     		VARCHAR2(100)		 NULL ,
		MENU_ACTIVE                   		NUMBER(1)		 NULL ,
		MENU_PARENT                   		VARCHAR2(100)		 NULL ,
		MENU_CHILD                    		NUMBER(1)		 NULL ,
		CREATED                       		DATE		 NULL ,
		CREATED_MBR_ID                		VARCHAR2(50)		 NULL ,
		MODIFIED                      		DATE		 NULL ,
		MODIFIED_MBR_ID               		VARCHAR2(50)		 NULL ,
		ISDEL                         		NUMBER(1)		 DEFAULT 0		 NULL
);

COMMENT ON TABLE JNITCMSMENUTREE is '메뉴테이블';
COMMENT ON COLUMN JNITCMSMENUTREE.MENU_CODE_ID is '메뉴 코드 ID';
COMMENT ON COLUMN JNITCMSMENUTREE.MENU_ID is '메뉴ID';
COMMENT ON COLUMN JNITCMSMENUTREE.MENU_NAME is '메뉴명';
COMMENT ON COLUMN JNITCMSMENUTREE.MENU_LINK is '메뉴링크';
COMMENT ON COLUMN JNITCMSMENUTREE.MENU_DEPTH is '메뉴뎁스';
COMMENT ON COLUMN JNITCMSMENUTREE.MENU_ICON is '메뉴아이콘';
COMMENT ON COLUMN JNITCMSMENUTREE.MENU_ACTIVE is '활성화';
COMMENT ON COLUMN JNITCMSMENUTREE.MENU_PARENT is '부모노드';
COMMENT ON COLUMN JNITCMSMENUTREE.MENU_CHILD is '자식노드유무';
COMMENT ON COLUMN JNITCMSMENUTREE.CREATED is '생성일';
COMMENT ON COLUMN JNITCMSMENUTREE.CREATED_MBR_ID is '작성자';
COMMENT ON COLUMN JNITCMSMENUTREE.MODIFIED is '수정일';
COMMENT ON COLUMN JNITCMSMENUTREE.MODIFIED_MBR_ID is '수정자';
COMMENT ON COLUMN JNITCMSMENUTREE.ISDEL is '삭제여부';



ALTER TABLE JNITCMSMENUTREE ADD CONSTRAINT IDX_JNITCMSMENUTREE_PK PRIMARY KEY (MENU_CODE_ID);

