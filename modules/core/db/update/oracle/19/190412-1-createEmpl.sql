create table EMPL_EMPL (
    ID varchar2(32),
    VERSION number(10) not null,
    CREATE_TS timestamp,
    CREATED_BY varchar2(50 char),
    UPDATE_TS timestamp,
    UPDATED_BY varchar2(50 char),
    DELETE_TS timestamp,
    DELETED_BY varchar2(50 char),
    --
    NOTESNAME varchar2(255 char),
    USER_ID varchar2(32),
    CITY varchar2(100 char),
    BRANCH_ID varchar2(32),
    OFFICE varchar2(150 char),
    PHONE varchar2(100 char),
    EXTPHONE varchar2(100 char),
    MOBILEPHONE varchar2(255 char),
    ROOM varchar2(255 char),
    BIRTHDATE date,
    CHIEF_ID varchar2(32),
    TECH_ID varchar2(32),
    SEX varchar2(50 char),
    PHOTO_ID varchar2(32),
    POSTPATH varchar2(255 char),
    --
    primary key (ID)
)^