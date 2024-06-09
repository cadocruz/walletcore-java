Create table transactions (
    id varchar(255),
    account_id_from varchar(255),
    account_id_to varchar(255),
    amount DECIMAL(10,5),
    created_at date
)