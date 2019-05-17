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