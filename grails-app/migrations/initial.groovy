databaseChangeLog = {

    changeSet(author: "andy (generated)", id: "1462174591009-1") {
        createTable(tableName: "address") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "addressPK")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "country", type: "VARCHAR(255)")

            column(name: "line1", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "line2", type: "VARCHAR(255)")

            column(name: "line3", type: "VARCHAR(255)")

            column(name: "line4", type: "VARCHAR(255)")

            column(name: "line5", type: "VARCHAR(255)")

            column(name: "postal_code", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "andy (generated)", id: "1462174591009-2") {
        createTable(tableName: "guest") {
            column(name: "id", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "attending", type: "BOOLEAN")

            column(name: "dietary_choice", type: "VARCHAR(255)")

            column(name: "first_name", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "invitation_id", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "last_name", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "guests_idx", type: "INT")
        }
    }

    changeSet(author: "andy (generated)", id: "1462174591009-3") {
        createTable(tableName: "invitation") {
            column(name: "id", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "address_country", type: "VARCHAR(255)")

            column(name: "address_line1", type: "VARCHAR(255)")

            column(name: "address_line2", type: "VARCHAR(255)")

            column(name: "address_line3", type: "VARCHAR(255)")

            column(name: "address_line4", type: "VARCHAR(255)")

            column(name: "address_line5", type: "VARCHAR(255)")

            column(name: "address_postal_code", type: "VARCHAR(255)")

            column(name: "email_address", type: "VARCHAR(255)")

            column(name: "status", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "andy (generated)", id: "1462174591009-4") {
        createTable(tableName: "role") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "rolePK")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "authority", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "andy (generated)", id: "1462174591009-5") {
        createTable(tableName: "user") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "userPK")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "account_expired", type: "BOOLEAN") {
                constraints(nullable: "false")
            }

            column(name: "account_locked", type: "BOOLEAN") {
                constraints(nullable: "false")
            }

            column(name: "enabled", type: "BOOLEAN") {
                constraints(nullable: "false")
            }

            column(name: "password", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "password_expired", type: "BOOLEAN") {
                constraints(nullable: "false")
            }

            column(name: "username", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "andy (generated)", id: "1462174591009-6") {
        createTable(tableName: "user_role") {
            column(name: "user_id", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "role_id", type: "BIGINT") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "andy (generated)", id: "1462174591009-7") {
        addPrimaryKey(columnNames: "id", constraintName: "guestPK", tableName: "guest")
    }

    changeSet(author: "andy (generated)", id: "1462174591009-8") {
        addPrimaryKey(columnNames: "id", constraintName: "invitationPK", tableName: "invitation")
    }

    changeSet(author: "andy (generated)", id: "1462174591009-9") {
        addPrimaryKey(columnNames: "user_id, role_id", constraintName: "user_rolePK", tableName: "user_role")
    }

    changeSet(author: "andy (generated)", id: "1462174591009-10") {
        addUniqueConstraint(columnNames: "authority", tableName: "role")
    }

    changeSet(author: "andy (generated)", id: "1462174591009-11") {
        addUniqueConstraint(columnNames: "username", tableName: "user")
    }

    changeSet(author: "andy (generated)", id: "1462174591009-12") {
        addForeignKeyConstraint(baseColumnNames: "invitation_id", baseTableName: "guest", constraintName: "FK_7lwm0078jicc0luv1sviifkdx", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "invitation")
    }

    changeSet(author: "andy (generated)", id: "1462174591009-13") {
        addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "user_role", constraintName: "FK_apcc8lxk2xnug8377fatvbn04", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user")
    }

    changeSet(author: "andy (generated)", id: "1462174591009-14") {
        addForeignKeyConstraint(baseColumnNames: "role_id", baseTableName: "user_role", constraintName: "FK_it77eq964jhfqtu54081ebtio", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "role")
    }
}
