-- DELETE FROM TB_CMMN_INDIV_CD;	-- 공통개별코드
-- DELETE FROM TB_CMMN_GROUP_CD;	-- 공통그룹코드

-- 성별
/*
DELETE FROM TB_CMMN_INDIV_CD WHERE GROUP_CD = 100001;
DELETE FROM TB_CMMN_GROUP_CD WHERE GROUP_CD = 100001;

insert into TB_CMMN_GROUP_CD(GROUP_CD, UP_GROUP_CD, GROUP_CD_NM, GROUP_CD_LVL_VL, GROUP_CD_SORT_SN, GROUP_CD_CHG_YN, GROUP_CD_EXPLN, REG_DT, RGTR_ID, MDFCN_DT, MDFR_ID, DEL_YN) VALUES
(100001, 100001, '성별', NULL, NULL, 'Y', NULL, SYSDATE, 'SYSTEM', SYSDATE, 'SYSTEM', 'N');

	insert into TB_CMMN_INDIV_CD (GROUP_CD, INDIV_CD, UP_INDIV_CD, INDIV_CD_SORT_SN, INDIV_CD_NM, INDIV_CD_CHG_YN, INDIV_CD_VL_FRST, INDIV_CD_VL_SCNDRY, INDIV_CD_VL_THIRD, INDIV_CD_EXPLN, REG_DT, RGTR_ID, MDFCN_DT, MDFR_ID, DEL_YN) VALUES
	(100001, 1001, 1001, 1, '남', 'Y', NULL,NULL,NULL,NULL,SYSDATE, 'SYSTEM', SYSDATE, 'SYSTEM', 'N'),
	(100001, 1002, 1002, 2, '여', 'Y', NULL,NULL,NULL,NULL,SYSDATE, 'SYSTEM', SYSDATE, 'SYSTEM', 'N');
*/