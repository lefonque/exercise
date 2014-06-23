drop table com_epis_eai_job;
drop table com_epis_eai_agent;

create sequence SEQ_AGENT;
create sequence SEQ_JOB;
create table com_epis_eai_agent (
	agent_id varchar(50) primary key
	,org_code varchar(50)
	,operating_system varchar(10)
	,charset varchar(10)
	,websvc_user varchar(50)
	,websvc_pass varchar(50)
	,officer_name varchar(50)
	,officer_contact varchar(30)
	,sms_cell_no varchar(30)
	,sms_use_yn char(1)
	,schedule_type varchar(10)
	,created_date timestamp
	,modified_date timestamp
);

create table com_epis_eai_job (
	job_id varchar(50) primary key
	,agent_id varchar(50)
	,job_name varchar(100)
	,job_type varchar(10)
	,exec_time varchar(20)
	,sql_main varchar(2000)
	,sql_pre varchar(2000)
	,sql_post varchar(2000)
	,jdbc_driver_class_name varchar(100)
	,jdbc_url varchar(300)
	,jdbc_username varchar(100)
	,jdbc_password varchar(100)
	,batch_select_count integer
	,server_sql varchar(2000)
	,created_date timestamp
	,modified_date timestamp
)