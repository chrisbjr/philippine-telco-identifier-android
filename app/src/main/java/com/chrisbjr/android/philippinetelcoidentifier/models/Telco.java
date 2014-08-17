package com.chrisbjr.android.philippinetelcoidentifier.models;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table(name = "telco_prefix")
public class Telco extends Model {

    @Column(name = "name", unique = true, onUniqueConflict = Column.ConflictAction.REPLACE)
    public String name;
}
