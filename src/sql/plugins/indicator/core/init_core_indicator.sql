
--
-- Data for table core_admin_right
--
DELETE FROM core_admin_right WHERE id_right = 'INDICATOR_MANAGEMENT';
INSERT INTO core_admin_right (id_right,name,level_right,admin_url,description,is_updatable,plugin_name,id_feature_group,icon_url,documentation_url, id_order ) VALUES 
('INDICATOR_MANAGEMENT','indicator.adminFeature.ManageIndicator.name',1,'jsp/admin/plugins/indicator/ManageIndicators.jsp','indicator.adminFeature.ManageIndicator.description',0,'indicator',NULL,NULL,NULL,4);


--
-- Data for table core_user_right
--
DELETE FROM core_user_right WHERE id_right = 'INDICATOR_MANAGEMENT';
INSERT INTO core_user_right (id_right,id_user) VALUES ('INDICATOR_MANAGEMENT',1);

