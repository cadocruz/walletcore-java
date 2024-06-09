ALTER TABLE wallet.clients MODIFY COLUMN created_at DATETIME NULL;
ALTER TABLE wallet.clients MODIFY COLUMN updated_at DATETIME NULL;
ALTER TABLE wallet.accounts MODIFY COLUMN created_at DATETIME NULL;
ALTER TABLE wallet.accounts MODIFY COLUMN updated_at DATETIME NULL;
ALTER TABLE wallet.transactions MODIFY COLUMN created_at DATETIME NULL;
