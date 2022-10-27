DROP TABLES IF EXISTS project;
DROP TABLES IF EXISTS material;
DROP TABLES IF EXISTS step;
DROP TABLES IF EXISTS category;
DROP TABLES IF EXISTS project_category;

CREATE TABLE project_category (
	project_id INT NOT NULL,
	category_id INT NOT NULL
);

CREATE TABLE category (
	category_id INT NOT NULL,
	category_name VARCHAR(128) NOT NULL
);

CREATE TABLE step (
	step_id INT NOT NULL,
	project_id INT NOT NULL,
	step_text TEXT NOT NULL,
	step_order INT NOT NULL
);

CREATE TABLE material (
	material_id INT NOT NULL,
	project_id INT NOT NULL,
	material_name VARCHAR(128) NOT NULL,
	num_required INT,
	cost DECIMAL(7,2)
);

CREATE TABLE project (
	project_id INT NOT NULL,
	project_name VARCHAR(128) NOT NULL,
	estimated_hours DECIMAL(7,2),
	actual_hours DECIMAL(7,2),
	difficulty INT,
	notes TEXT
);