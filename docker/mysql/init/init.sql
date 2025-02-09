-- Ensure database exists
CREATE DATABASE IF NOT EXISTS task_wizard_db;

-- Use the database
USE task_wizard_db;

-- Create table if not exists
CREATE TABLE IF NOT EXISTS tasks (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    due_date DATETIME
 );
