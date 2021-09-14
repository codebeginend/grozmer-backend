alter table plot
ADD created_at TIMESTAMPTZ DEFAULT (now() AT TIME ZONE 'utc'),
ADD updated_at TIMESTAMPTZ;
