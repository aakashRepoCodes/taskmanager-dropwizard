-- Create database if it does not exist
CREATE DATABASE IF NOT EXISTS task_wizard_db;
USE task_wizard_db;

-- Create tasks table
CREATE TABLE IF NOT EXISTS tasks (
                                     id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                     title VARCHAR(255) NOT NULL,
    description TEXT,
    due_date DATETIME
    );