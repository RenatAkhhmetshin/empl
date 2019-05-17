alter table EMPL_ORG_UNIT add constraint FK_EMPL_ORG_UNIT_ON_PARENT foreign key (PARENT_ID) references EMPL_ORG_UNIT(ID)^
create index IDX_EMPL_ORG_UNIT_ON_PARENT on EMPL_ORG_UNIT (PARENT_ID)^
