DROP TABLE IF EXISTS city;
 
CREATE TABLE city (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  name VARCHAR(250) NOT NULL,
  start_date DATE,
  end_date DATE,
  price INT(8),
  status VARCHAR(250),
  color VARCHAR(250) DEFAULT NULL
);