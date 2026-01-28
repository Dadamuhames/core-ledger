CREATE TYPE currency AS ENUM (
    'UZS',
    'USD',
    'RUB'
);

CREATE TYPE account_status AS ENUM (
    'ACTIVE',
    'INACTIVE',
    'FROZEN'
);

CREATE TABLE accounts (
    id bigint PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    client_id bigint NOT NULL,
    currency currency NOT NULL,
    actual_balance bigint NOT NULL CHECK (actual_balance >= 0),
    reserved_balance bigint NOT NULL DEFAULT 0 CHECK (reserved_balance >= 0),
    status account_status NOT NULL DEFAULT 'ACTIVE',
    created_at timestamptz NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at timestamptz NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE INDEX idx_accounts_client_id ON accounts (client_id);

CREATE TYPE hold_status AS ENUM (
    'ACTIVE',
    'RELEASED',
    'CONFIRMED'
);

CREATE TABLE holds (
    id bigint PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    transaction_id bigint NOT NULL,
    account_id bigint NOT NULL,
    status hold_status NOT NULL DEFAULT 'ACTIVE',
    amount bigint NOT NULL CHECK (amount > 0),
    expires_at timestamptz not null,
    created_at timestamptz NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at timestamptz NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_holds_account FOREIGN KEY (account_id) REFERENCES accounts (id) ON DELETE RESTRICT ON UPDATE RESTRICT
);

CREATE INDEX idx_holds_account_id ON holds (account_id);

CREATE INDEX idx_holds_transaction_id ON holds (transaction_id);

CREATE TYPE entry_type AS ENUM (
    'DEBIT',
    'CREDIT'
);

CREATE TYPE transaction_type AS ENUM (
    'P2P',
    'PAYMENT',
    'MERCHANT'
);

CREATE TABLE ledger_entries (
    id bigint PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    transaction_id bigint NOT NULL,
    account_id bigint NOT NULL,
    amount bigint NOT NULL CHECK (amount > 0),
    actual_balance bigint NOT NULL CHECK (actual_balance >= 0),
    currency currency NOT NULL,
    entry_type entry_type NOT NULL,
    transaction_type transaction_type NOT NULL,
    created_at timestamptz NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_ledger_account FOREIGN KEY (account_id) REFERENCES accounts (id) ON DELETE RESTRICT ON UPDATE RESTRICT
);

CREATE INDEX idx_ledger_transaction_id ON ledger_entries (transaction_id);

CREATE INDEX idx_ledger_account_id ON ledger_entries (account_id);

CREATE INDEX idx_ledger_entry_type ON ledger_entries (entry_type);

CREATE INDEX idx_ledger_transaction_type ON ledger_entries (transaction_type);