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