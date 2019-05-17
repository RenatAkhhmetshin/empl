-- begin EMPL_POST
create table EMPL_POST (
    ID varchar2(32),
    VERSION number(10) not null,
    CREATE_TS timestamp,
    CREATED_BY varchar2(50 char),
    UPDATE_TS timestamp,
    UPDATED_BY varchar2(50 char),
    DELETE_TS timestamp,
    DELETED_BY varchar2(50 char),
    --
    CODE varchar2(255 char),
    NAME varchar2(130 char),
    EXTID varchar2(30 char),
    UNID varchar2(40 char),
    --
    primary key (ID)
)^
-- end EMPL_POST
-- begin EMPL_BRANCH
create table EMPL_BRANCH (
    ID varchar2(32),
    VERSION number(10) not null,
    CREATE_TS timestamp,
    CREATED_BY varchar2(50 char),
    UPDATE_TS timestamp,
    UPDATED_BY varchar2(50 char),
    DELETE_TS timestamp,
    DELETED_BY varchar2(50 char),
    --
    ORGNAME varchar2(255 char),
    NAME varchar2(255 char),
    COUNTRY varchar2(255 char),
    MAINOFFICE varchar2(255 char),
    NUMBER_ varchar2(255 char),
    REGNUMBER varchar2(255 char),
    CODE varchar2(255 char),
    BIK varchar2(255 char),
    REGCODE varchar2(255 char),
    --
    primary key (ID)
)^
-- end EMPL_BRANCH
-- begin EMPL_EMPL
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
-- end EMPL_EMPL
-- begin EMPL_ORG_UNIT
create table EMPL_ORG_UNIT (
    ID varchar2(32),
    VERSION number(10) not null,
    CREATE_TS timestamp,
    CREATED_BY varchar2(50 char),
    UPDATE_TS timestamp,
    UPDATED_BY varchar2(50 char),
    DELETE_TS timestamp,
    DELETED_BY varchar2(50 char),
    --
    EXTID varchar2(100 char),
    FULLNAME varchar2(255 char),
    SHORTNAME varchar2(255 char),
    PARENT_ID varchar2(32),
    TYPEID integer,
    --
    primary key (ID)
)^
-- end EMPL_ORG_UNIT
-- begin EMPL_TEH_EMPL
create table EMPL_TEH_EMPL (
    ID varchar2(32),
    VERSION number(10) not null,
    CREATE_TS timestamp,
    CREATED_BY varchar2(50 char),
    UPDATE_TS timestamp,
    UPDATED_BY varchar2(50 char),
    DELETE_TS timestamp,
    DELETED_BY varchar2(50 char),
    --
    EXTID varchar2(100 char),
    TABNUMBER varchar2(100 char),
    WORKDATE date,
    DECRSTDATE date,
    DECRENDDT date,
    DISMISS char(1),
    DECREE char(1),
    DISMISSDT date,
    --
    primary key (ID)
)^
-- end EMPL_TEH_EMPL
-- begin EMPL_TEH_EMPL_POST_LINK
create table EMPL_TEH_EMPL_POST_LINK (
    TEH_EMPL_ID varchar2(32),
    POST_ID varchar2(32),
    primary key (TEH_EMPL_ID, POST_ID)
)^
-- end EMPL_TEH_EMPL_POST_LINK
-- begin EMPL_EMPL_ORG_UNIT_LINK
create table EMPL_EMPL_ORG_UNIT_LINK (
    EMPL_ID varchar2(32),
    ORG_UNIT_ID varchar2(32),
    primary key (EMPL_ID, ORG_UNIT_ID)
)^
-- end EMPL_EMPL_ORG_UNIT_LINK
