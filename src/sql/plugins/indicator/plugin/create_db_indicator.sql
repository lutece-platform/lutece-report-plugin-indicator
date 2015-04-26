
--
-- Structure for table indicator_key
--

DROP TABLE IF EXISTS indicator_key;
CREATE TABLE indicator_key (
id_indicator int(6) NOT NULL,
ind_key varchar(50) NOT NULL default '',
label varchar(50) NOT NULL default '',
description varchar(255) NOT NULL default '',
ind_value int(11) NOT NULL default '0',
ind_target int(11) NOT NULL default '0',
history_period varchar(50) NOT NULL default '',
PRIMARY KEY (id_indicator)
);

--
-- Structure for table indicator_history
--

DROP TABLE IF EXISTS indicator_history;
CREATE TABLE indicator_history (
id_history int(6) NOT NULL,
ind_key varchar(50) NOT NULL default '',
time_code int(11) NOT NULL default '0',
ind_value int(11) NOT NULL default '0',
ind_target int(11) NOT NULL default '0',
PRIMARY KEY (id_history)
);
