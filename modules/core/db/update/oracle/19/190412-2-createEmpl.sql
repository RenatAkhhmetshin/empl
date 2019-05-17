alter table EMPL_EMPL add constraint FK_EMPL_EMPL_ON_USER foreign key (USER_ID) references SEC_USER(ID)^
alter table EMPL_EMPL add constraint FK_EMPL_EMPL_ON_BRANCH foreign key (BRANCH_ID) references EMPL_BRANCH(ID)^
alter table EMPL_EMPL add constraint FK_EMPL_EMPL_ON_CHIEF foreign key (CHIEF_ID) references EMPL_EMPL(ID)^
alter table EMPL_EMPL add constraint FK_EMPL_EMPL_ON_TECH foreign key (TECH_ID) references EMPL_TEH_EMPL(ID)^
alter table EMPL_EMPL add constraint FK_EMPL_EMPL_ON_PHOTO foreign key (PHOTO_ID) references SYS_FILE(ID)^
create index IDX_EMPL_EMPL_ON_USER on EMPL_EMPL (USER_ID)^
create index IDX_EMPL_EMPL_ON_BRANCH on EMPL_EMPL (BRANCH_ID)^
create index IDX_EMPL_EMPL_ON_CHIEF on EMPL_EMPL (CHIEF_ID)^
create index IDX_EMPL_EMPL_ON_TECH on EMPL_EMPL (TECH_ID)^
create index IDX_EMPL_EMPL_ON_PHOTO on EMPL_EMPL (PHOTO_ID)^
