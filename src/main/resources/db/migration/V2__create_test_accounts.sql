
-- bank
INSERT INTO accounts (client_id, ams_account_id, currency, actual_balance, reserved_balance, status) VALUES
    ('1feab664-ffe8-4231-86af-bb6e706cd933', '8f81e1fc-682c-4f52-8142-e0ec2f819e80', 'UZS', 0, 0, 'ACTIVE');


-- sender
INSERT INTO accounts (client_id, ams_account_id, currency, actual_balance, reserved_balance, status) VALUES
    ('68c4129f-575c-4de6-bf3e-f2a899ebdcbd', 'f9b18ce1-61de-43bc-afcd-28ec6381c774', 'UZS', 100000000, 0, 'ACTIVE');


-- receiver
INSERT INTO accounts (client_id, ams_account_id, currency, actual_balance, reserved_balance, status) VALUES
    ('eaf146ef-3864-4c9e-a632-387a11ff99f6', 'fdd70588-ce42-4afd-93d8-dccb5138f988', 'UZS', 0, 0, 'ACTIVE');