alter table CALIBER_BATCH add "RESOURCE_ID" VARCHAR2(63) unique;
alter table CALIBER_TRAINEE add "RESOURCE_ID" VARCHAR2(63) unique;
update CALIBER_BATCH set resource_id = 'Id1' where Batch_id = 2200;