DROP TABLE IF EXISTS property_table;

CREATE TABLE IF NOT EXISTS property_table(
                            sl_num serial PRIMARY KEY,
                            alert_service_status BOOLEAN,
                            data_insertion_status BOOLEAN,
                            moisture_alert_limit INTEGER,
                            moisture_measure_frequency INTEGER);

INSERT INTO property_table(sl_num,alert_service_status,data_insertion_status,moisture_alert_limit,moisture_measure_frequency) VALUES
                           (1,true,true,20,15);

CREATE TABLE IF NOT EXISTS user_notification_service_table(
                            user_id serial PRIMARY KEY,
                            phone_num VARCHAR,
                            telegram_id VARCHAR);

INSERT INTO user_notification_service_table(user_id,phone_num,telegram_id) VALUES (1,'9493425174','659118748') ON CONFLICT DO NOTHING;