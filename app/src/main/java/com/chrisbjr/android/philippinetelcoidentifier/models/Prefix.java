package com.chrisbjr.android.philippinetelcoidentifier.models;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

@Table(name = "telco_prefix")
public class Prefix extends Model {
    @Column(name = "prefix", unique = true, onUniqueConflict = Column.ConflictAction.REPLACE)
    public String prefix;
    @Column(name = "telco")
    public Telco telco;

    public Prefix() {
        super();
    }

    public int getCount() {
        return new Select().from(Prefix.class).count();
    }
}