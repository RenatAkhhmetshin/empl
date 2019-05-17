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