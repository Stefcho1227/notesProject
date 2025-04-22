create table users
(
    id            int auto_increment
        primary key,
    email         text                                  not null,
    username      text                                  not null,
    password_hash text                                  not null,
    created_at    timestamp default current_timestamp() not null,
    last_login_at timestamp                             null,
    constraint email
        unique (email) using hash
);

create table notes
(
    id          int auto_increment
        primary key,
    owner_id    int                                    not null,
    title       text                                   null,
    content     text                                   null,
    is_public   tinyint(1) default 0                   not null,
    public_slug text                                   null,
    created_at  timestamp  default current_timestamp() not null,
    updated_at  timestamp  default current_timestamp() not null,
    deleted_at  timestamp                              null,
    constraint public_slug
        unique (public_slug) using hash,
    constraint notes_ibfk_1
        foreign key (owner_id) references users (id)
            on delete cascade
);

create table note_shares
(
    id             int auto_increment
        primary key,
    note_id        int                                   not null,
    shared_with_id int                                   not null,
    perm           text                                  not null
        check (`perm` in ('read', 'edit')),
    created_at     timestamp default current_timestamp() not null,
    constraint note_id
        unique (note_id, shared_with_id),
    constraint note_shares_ibfk_1
        foreign key (note_id) references notes (id)
            on delete cascade,
    constraint note_shares_ibfk_2
        foreign key (shared_with_id) references users (id)
            on delete cascade
);

create index idx_note_shares_shared_with_id
    on note_shares (shared_with_id);

create index idx_notes_deleted_at
    on notes (deleted_at);

create index idx_notes_owner_id
    on notes (owner_id);

create index idx_notes_public_slug
    on notes (public_slug(768));

create table reminders
(
    id         int auto_increment
        primary key,
    note_id    int                  not null,
    creator_id int                  not null,
    remind_at  timestamp            not null,
    is_sent    tinyint(1) default 0 not null,
    constraint reminders_ibfk_1
        foreign key (note_id) references notes (id)
            on delete cascade,
    constraint reminders_ibfk_2
        foreign key (creator_id) references users (id)
            on delete cascade
);

create table notifications
(
    id          int auto_increment
        primary key,
    user_id     int       not null,
    reminder_id int       not null,
    channel     text      not null
        check (`channel` in ('email', 'in-app')),
    sent_at     timestamp not null,
    constraint notifications_ibfk_1
        foreign key (user_id) references users (id)
            on delete cascade,
    constraint notifications_ibfk_2
        foreign key (reminder_id) references reminders (id)
            on delete cascade
);

create index idx_notifications_sent_at
    on notifications (sent_at);

create index idx_notifications_user_id
    on notifications (user_id);

create index reminder_id
    on notifications (reminder_id);

create index creator_id
    on reminders (creator_id);

create index idx_reminders_is_sent
    on reminders (is_sent);

create index idx_reminders_remind_at
    on reminders (remind_at);

create index note_id
    on reminders (note_id);

create table todo_items
(
    id       int auto_increment
        primary key,
    note_id  int                  not null,
    text     text                 not null,
    is_done  tinyint(1) default 0 not null,
    due_date timestamp            null,
    constraint todo_items_ibfk_1
        foreign key (note_id) references notes (id)
            on delete cascade
);

create index idx_todo_due_date
    on todo_items (due_date);

create index idx_todo_note_id
    on todo_items (note_id);

create index idx_users_username
    on users (username(768));

