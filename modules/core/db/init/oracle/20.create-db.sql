-- begin EMPL_EMPL
alter table EMPL_EMPL add constraint FK_EMPL_EMPL_ON_USER foreign key (USER_ID) references SEC_USER(ID)^
alter table EMPL_EMPL add constraint FK_EMPL_EMPL_ON_BRANCH foreign key (BRANCH_ID) references EMPL_BRANCH(ID)^
alter table EMPL_EMPL add constraint FK_EMPL_EMPL_ON_CHIEF foreign key (CHIEF_ID) references EMPL_EMPL(ID)^
alter table EMPL_EMPL add constraint FK_EMPL_EMPL_ON_PHOTO foreign key (PHOTO_ID) references SYS_FILE(ID)^
create index IDX_EMPL_EMPL_ON_USER on EMPL_EMPL (USER_ID)^
create index IDX_EMPL_EMPL_ON_BRANCH on EMPL_EMPL (BRANCH_ID)^
create index IDX_EMPL_EMPL_ON_CHIEF on EMPL_EMPL (CHIEF_ID)^
create index IDX_EMPL_EMPL_ON_PHOTO on EMPL_EMPL (PHOTO_ID)^
-- end EMPL_EMPL

-- begin EMPL_TEH_EMPL_POST_LINK
alter table EMPL_TEH_EMPL_POST_LINK add constraint FK_TEHEMPPOS_ON_TEH_EMPL foreign key (TEH_EMPL_ID) references EMPL_TEH_EMPL(ID)^
alter table EMPL_TEH_EMPL_POST_LINK add constraint FK_TEHEMPPOS_ON_POST foreign key (POST_ID) references EMPL_POST(ID)^
-- end EMPL_TEH_EMPL_POST_LINK
-- begin EMPL_EMPL_ORG_UNIT_LINK
alter table EMPL_EMPL_ORG_UNIT_LINK add constraint FK_EMPORGUNI_ON_EMPL foreign key (EMPL_ID) references EMPL_EMPL(ID)^
alter table EMPL_EMPL_ORG_UNIT_LINK add constraint FK_EMPORGUNI_ON_ORG_UNIT foreign key (ORG_UNIT_ID) references EMPL_ORG_UNIT(ID)^
-- end EMPL_EMPL_ORG_UNIT_LINK

-- begin EMPL_ORG_UNIT
alter table EMPL_ORG_UNIT add constraint FK_EMPL_ORG_UNIT_ON_PARENT foreign key (PARENT_ID) references EMPL_ORG_UNIT(ID)^
create index IDX_EMPL_ORG_UNIT_ON_PARENT on EMPL_ORG_UNIT (PARENT_ID)^
-- end EMPL_ORG_UNIT
