DROP TABLE BBS_FILE;
CREATE TABLE BBS_FILE(
	S_BBS_ID VARCHAR2(50),
	I_BBS_IDX INTEGER,
	I_FILE_IDX INTEGER,
	S_LOCAL_FILEPAHT VARCHAR2(2000),
	S_SERVER_FILENAME VARCHAR2(2000),
	S_SERVER_PATH VARCHAR2(2000),
	S_FILE_TYPE VARCHAR2(50),
	C_THUMB_YN CHAR(1),
	C_MAJOR_YN CHAR(1),
	PRIMARY KEY(S_BBS_ID,I_BBS_IDX,I_FILE_IDX)
);

INSERT INTO "PUBLIC"."BBS_FILE" ("S_BBS_ID", "I_BBS_IDX", "I_FILE_IDX", "S_LOCAL_FILEPAHT", "S_SERVER_FILENAME", "S_SERVER_PATH", "S_FILE_TYPE", "C_THUMB_YN", "C_MAJOR_YN") 
VALUES ('1', 1, 1, 'filename', 'filename', '/', 'image', 'N', 'N');
